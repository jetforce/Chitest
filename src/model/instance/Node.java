package model.instance;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Node extends Instance {
	
	private int row;
	private int col;
	
	private Instance labelInstance;
	private ArrayList<Instance> uploadedInstances;
	
	public Node(int row,
			int col,
			ArrayList<Double> weights) {
		
		super(weights);
		
		this.row = row;
		this.col = col;
		this.labelInstance = null;
		this.uploadedInstances = new ArrayList<Instance>();
		
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	@Override
	public ArrayList<Double> getWeights() {
		return weights;
	}
	
	public Instance getLabelInstance() {
		return labelInstance;
	}
	
	public void setLabelInstance(Instance labelInstance) {
		this.labelInstance = labelInstance;
	}
	
	public ArrayList<Instance> getUploadedInstances() {
		return uploadedInstances;
	}
	
	public String getNodeRowCol() {
		return "(" + row + "," + col + ")";
	}
	
	@Override
	public String getTag() {
		return tag;
	}
	
	@Override
	public String toString() {
		return row + ";" + col + "," + weights.stream().map(weight -> weight.toString()).collect(Collectors.joining(","));
	}
	
}
