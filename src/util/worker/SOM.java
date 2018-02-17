package util.worker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.swing.SwingWorker;

import model.Answer;
import model.instance.Instance;
import model.instance.Node;
import util.CSVIO;
import util.SwingUpdater;
import view.MainFrame;

public class SOM extends SwingWorker<Node[], Integer>{
	
	private final double initialRate = 0.99;
	private double finalRate = 0.1;
	private final double N = 1;
	
	private double g;
	private int maxRows;
	private int maxCols;
	private int featureCount;
	private int globalOrderingCycles; // sample size * 10
	private int fineAdjustmentCyles; // sample size * 10
	private ArrayList<? extends Instance> instances;
	private String weightSaveFilePath;
	private MainFrame mainFrame;
	private Node[] nodes;
	
	public SOM(int maxRows,
			int maxCols,
			Double finalRate,
			ArrayList<? extends Instance> instances,
			String weighSaveFilePath,
			MainFrame mainFrame) {
		
		this.g = 1;
		this.finalRate = finalRate;
		this.maxRows = maxRows;
		this.maxCols = maxCols;
		this.featureCount = instances.get(0).getWeights().size();
		this.globalOrderingCycles = instances.size() * 10;
		this.fineAdjustmentCyles = instances.size() * 10;
		this.instances = instances;
		this.weightSaveFilePath = weighSaveFilePath;
		this.mainFrame = mainFrame;
		
		nodes = new Node[maxRows * maxCols];
		initNodes();
		
	}
	
	public Node[] getNodes() {
		return nodes;
	}
	
	public void train() {
		
		int maxDim = Math.max(maxRows, maxCols);
		int chunkNum  = 0;
		
		final int iterations = globalOrderingCycles + fineAdjustmentCyles;
		
		Random random = new Random();
		final int instanceCount = instances.size();
		for(int i = 0; i < iterations; i++) {
			
			int index = random.nextInt(instanceCount);
			Instance instance = instances.get(index);
			
			if(i >= ((globalOrderingCycles/maxDim) * (chunkNum + 1)) &&
					i < globalOrderingCycles)
				chunkNum++;
			
			double[] distances = getDistanceMap(instance.getWeights());
			Node winningNode = getWinningNode(distances);
			ArrayList<Node> winningNeighborhood = getWinningNeighborhood(maxDim, winningNode, chunkNum);
			adjustWeights(instance.getWeights(), winningNeighborhood);
			
			if(i < globalOrderingCycles)
				g = computeLearningRate(i);
			
			//System.out.println("Iteration: " + i);
			//System.out.println("g = " + g);
			//System.out.println("Winning Node " + winningNode.getNodeRowCol() + "\n");
			
		}
		
		uploadToMap();
		labelMap();
		
	}

	@Override
	protected Node[] doInBackground() {
		
		int maxDim = Math.max(maxRows, maxCols);
		int chunkNum  = 0;
		
		final int iterations = globalOrderingCycles + fineAdjustmentCyles;
		
		Random random = new Random();
		final int instanceCount = instances.size();
		for(int i = 0; i < iterations; i++) {
			
			int index = random.nextInt(instanceCount);
			Instance instance = instances.get(index);
			
			if(i >= ((globalOrderingCycles/maxDim) * (chunkNum + 1)) &&
					i < globalOrderingCycles)
				chunkNum++;
			
			double[] distances = getDistanceMap(instance.getWeights());
			Node winningNode = getWinningNode(distances);
			ArrayList<Node> winningNeighborhood = getWinningNeighborhood(maxDim, winningNode, chunkNum);
			adjustWeights(instance.getWeights(), winningNeighborhood);
			
			if(i < globalOrderingCycles)
				g = computeLearningRate(i);
			
			publish(new Integer((int)(((float)i) / iterations * 100)));
			
		}
		
		uploadToMap();
		labelMap();
		
		return nodes;
		
	}
	
	@Override
	protected void process(List<Integer> values) {
		
		for(Integer value : values) {
			mainFrame.getProgressBarSOMStatus().setValue(value);
		}
		
	}
	
	@Override
	protected void done() {
		
		try {
			nodes = get();
			for(Node node : nodes) {
				CSVIO.write(node.toString(), weightSaveFilePath);
			}
			
			SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaSOMStatus(), "Weights saved in " + weightSaveFilePath + "\n");
			
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
	}
	
	private void initNodes() {
		for(int i = 0; i < nodes.length; i++) {
			int row = i / maxCols;
			int col = i % maxCols;
			
			ArrayList<Double> weights = new ArrayList<Double>();
			Random random = new Random();
			for(int j = 0; j < featureCount; j++)
				weights.add(random.nextDouble());
			
			nodes[i] = new Node(row, col, weights);
		}
	}
	
	private double[] getDistanceMap(ArrayList<Double> instanceWeights) {
		
		double[] distances = new double[nodes.length];
		
		for(int i = 0; i < nodes.length; i++) {
			distances[i] = computeDistance(instanceWeights, nodes[i].getWeights());
		}
		
		return distances;
		
	}
	
	private double computeDistance(ArrayList<Double> instanceWeights,
			ArrayList<Double> nodeWeights) {
		
		Double distance = 0.0;
		int validFeatureCount = 0;
		
		for(int i = 0; i < featureCount; i++) {
			if(instanceWeights.get(i) != Answer.INVALID) {
				distance += Math.pow(instanceWeights.get(i) - nodeWeights.get(i), 2.0);
				validFeatureCount++;
			}
		}
		if(validFeatureCount > 0)
			distance /= validFeatureCount;
		else
			distance = Double.MAX_VALUE;
		
		return distance;
		
	}
	
	private int getSmallestDistanceIndex(double[] distances) {
		
		int smallestDistanceIndex = 0;
		
		for(int i = 0; i < distances.length; i++) {
			if(distances[i] < distances[smallestDistanceIndex]) {
				smallestDistanceIndex = i;
			}
		}
		return smallestDistanceIndex;
		
	}
	
	private Node getWinningNode(double[] distances) {
		
		return nodes[getSmallestDistanceIndex(distances)];
		
	}
	
	private ArrayList<Node> getWinningNeighborhood(int maxDim,
			Node winningNode,
			int chunkNum) {
		
		ArrayList<Node> winningNeighborhood = new ArrayList<Node>();
		int radius = maxDim - chunkNum;
		
		int firstRow = winningNode.getRow() - radius;
		if(firstRow < 0)
			firstRow = 0;
		
		int firstCol = winningNode.getCol() - radius;
		if(firstCol < 0)
			firstCol = 0;
		
		int lastRow = winningNode.getRow() + radius;
		if(lastRow >= maxRows)
			lastRow = maxRows - 1;
		
		int lastCol = winningNode.getCol() + radius;
		if(lastCol >= maxCols)
			lastCol = maxCols - 1;
		
		Node[] candidateNodes = Arrays.copyOfRange(nodes, firstRow * maxCols + firstCol, lastRow * maxCols + lastCol + 1);
		
		for(Node node : candidateNodes) {
			if(Math.abs(node.getRow() - winningNode.getRow()) <= radius &&
					Math.abs(node.getCol() - winningNode.getCol()) <= radius)
				winningNeighborhood.add(node);
		}
		
		return winningNeighborhood;
		
	}
	
	private void adjustWeights(ArrayList<Double> instanceWeights,
			ArrayList<Node> winningNeighborhood) {
		
		for(Node node : winningNeighborhood) {
			for(int i = 0; i < node.getWeights().size(); i++) {
				if(instanceWeights.get(i) != Answer.INVALID) {
					double newWeight = node.getWeights().get(i) + (g * N * (instanceWeights.get(i) - node.getWeights().get(i)));
					node.getWeights().set(i, newWeight);
				}
			}
		}
		
	}
	
	private double computeLearningRate(int currentIteration) {
		return (initialRate - (initialRate - finalRate) * (((float)currentIteration) / globalOrderingCycles));
		//return ((finalRate * currentIteration) - (initialRate * currentIteration) + (initialRate * globalOrderingCycles) + finalRate) / (globalOrderingCycles + 1);
	}
	
	private void uploadToMap() {
		
		for(Instance instance : instances) {
			double[] distances = getDistanceMap(instance.getWeights());
			Node node = getWinningNode(distances);
			node.getUploadedInstances().add(instance);
		}
		
	}
	
	private void labelMap() {
		
		int instanceCount = instances.size();
		
		for(Node node : nodes) {
			double[] distances = new double[instanceCount];
			for(int i = 0; i < instanceCount; i++)
				distances[i] = computeDistance(instances.get(i).getWeights(), node.getWeights());
			int smallestDistanceIndex = getSmallestDistanceIndex(distances);
			node.setLabelInstance(instances.get(smallestDistanceIndex));
			
		}
		
	}
	
	public void printUploadMap() {
		
		ArrayList<String> output = new ArrayList<String>();
		
		for(int i = 0; i < maxRows; i++) {
			String temp = "";
			for(int j = 0; j < maxCols - 1; j++) {
				temp += nodes[i * maxCols + j].getUploadedInstances().stream().map(instance -> instance.getTag()).collect(Collectors.joining(" & ")) + ",";
			}
			temp += nodes[i * maxCols + (maxCols - 1)].getUploadedInstances().stream().map(instance -> instance.getTag()).collect(Collectors.joining(" & "));
			output.add(temp);
		}
		
		CSVIO.write(output, "UploadMap.csv");
		
	}
	
	public void printConsolidatedUploadMap() {
		
		ArrayList<String> output = new ArrayList<String>();
		
		for(int i = 0; i < maxRows; i++) {
			String temp = "";
			for(int j = 0; j < maxCols - 1; j++) {
				if(!nodes[i * maxCols + j].getUploadedInstances().isEmpty())
					temp += nodes[i * maxCols + j].getUploadedInstances().get(0).getTag() + ",";
			}
			if(!nodes[i * maxCols + (maxCols - 1)].getUploadedInstances().isEmpty())
				temp += nodes[i * maxCols + (maxCols - 1)].getUploadedInstances().get(0).getTag();
			output.add(temp);
		}
		
		CSVIO.write(output, "CUploadMap.csv");
		
	}
	
	public void printLabeledMap() {
		
		ArrayList<String> output = new ArrayList<String>();
		
		for(int i = 0; i < maxRows; i++) {
			String temp = "";
			for(int j = 0; j < maxCols - 1; j++) {
				temp += nodes[i * maxCols + j].getLabelInstance().getTag() + ",";
			}
			temp += nodes[i * maxCols + (maxCols - 1)].getLabelInstance().getTag();
			output.add(temp);
		}
		
		CSVIO.write(output, "LabelMap.csv");
		
	}
	
}
