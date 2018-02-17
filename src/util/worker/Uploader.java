package util.worker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import model.Answer;
import model.instance.Instance;
import model.instance.Node;
import util.CSVIO;
import util.SwingUpdater;
import view.MainFrame;

public class Uploader extends SwingWorker<HashMap<String, ArrayList<Instance>>, Void> {
	
	private HashMap<String, ArrayList<Instance>> hashMap;
	private ArrayList<Node> nodes;
	private ArrayList<Instance> instances;
	private String saveFilePath;
	private MainFrame mainFrame;
	
	public Uploader(HashMap<String, ArrayList<Instance>> hashMap,
			ArrayList<Node> nodes, 
			ArrayList<Instance> instances,
			String saveFilePath,
			MainFrame mainFrame) {
		
		this.hashMap = hashMap;
		this.nodes = nodes;
		this.instances = instances;
		this.saveFilePath = saveFilePath;
		this.mainFrame = mainFrame;
		
	}
	
	@Override
	protected HashMap<String, ArrayList<Instance>> doInBackground() {
		
		SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaUploaderStatus(), "- STARTING -");
		
		for(Instance instance : instances) {
			double[] distances = getDistanceMap(nodes, instance.getWeights());
			Node node = getWinningNode(nodes, distances);
			ArrayList<Instance> cluster = hashMap.get(node.getTag());
			cluster.add(instance);
		}
		
		return hashMap;
		
	}
	
	@Override
	protected void done() {
		
		try {
			hashMap = get();
			
			ArrayList<String> output = new ArrayList<String>();
			long timestamp = System.currentTimeMillis();
			for(String s : hashMap.keySet()) {
				output.clear();
				for(Instance instance : hashMap.get(s)) {
					output.add(instance.toString());
				}
				String clusterSavePath = saveFilePath + "\\" + s + "-" + timestamp + ".csv";
				CSVIO.write(output, clusterSavePath);
				SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaUploaderStatus(), s + " saved in " + clusterSavePath + "\n");
			}
			
			printConsolidatedUploadMap(nodes, saveFilePath, timestamp);
			
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
	}
	
	public void uploadToMap() {
		
		for(Instance instance : instances) {
			double[] distances = getDistanceMap(nodes, instance.getWeights());
			Node node = getWinningNode(nodes, distances);
			ArrayList<Instance> cluster = hashMap.get(node.getTag());
			cluster.add(instance);
		}
		
	}
	
	private double[] getDistanceMap(ArrayList<Node> nodes,
			ArrayList<Double> instanceWeights) {
		
		double[] distances = new double[nodes.size()];
		for(int i = 0; i < nodes.size(); i++) {
			distances[i] = computeDistance(instanceWeights, nodes.get(i).getWeights());
		}
		
		return distances;
		
	}
	
	private double computeDistance(ArrayList<Double> instanceWeights,
			ArrayList<Double> nodeWeights) {
		
		Double distance = 0.0;
		int validFeatureCount = 0;
		
		for(int i = 0; i < instanceWeights.size(); i++) {
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
	
	private Node getWinningNode(ArrayList<Node> nodes,
			double[] distances) {
		
		return nodes.get(getSmallestDistanceIndex(distances));
		
	}
	
	public void printConsolidatedUploadMap(ArrayList<Node> nodes, 
			String dir, 
			long timestamp) {
		
		ArrayList<String> output = new ArrayList<String>();
		nodes.sort(Comparator.comparing(node -> node.getRow()));
		int maxRows = nodes.get(nodes.size() - 1).getRow() + 1;
		//System.out.println(maxRows);
		nodes.sort(Comparator.comparing(node -> node.getCol()));
		int maxCols = nodes.get(nodes.size() - 1).getCol() + 1;
		//System.out.println(maxCols);
		nodes.sort(Comparator.comparing(Node::getRow).thenComparing(Node::getCol));
		
		for(int i = 0; i < maxRows; i++) {
			String temp = "";
			for(int j = 0; j < maxCols - 1; j++) {
				temp += nodes.get(i * maxCols + j).getTag() + ",";
			}
			temp += nodes.get(i * maxCols + (maxCols - 1)).getTag();
			output.add(temp);
		}
		String mapSaveFilePath = dir + "\\UploadMap-" + timestamp + ".csv";
		CSVIO.write(output, mapSaveFilePath);
		SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaUploaderStatus(), "Map saved in " + mapSaveFilePath + "\n");
		
	}
	
}
