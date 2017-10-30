package model.instance;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Instance {
	
	protected String tag;
	protected ArrayList<Double> weights;
	
	public Instance(ArrayList<Double> weights) {
		this.tag = "";
		this.weights = weights;
	}
	
	public Instance(String tag,
			ArrayList<Double> weights) {
		this.tag = tag;
		this.weights = weights;
	}
	
	public ArrayList<Double> getWeights() {
		return weights;
	}
	
	public void setWeights(ArrayList<Double> weights) {
		this.weights = weights;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	@Override
	public String toString() {
		return tag + "," + weights.stream().map(weight -> weight.toString()).collect(Collectors.joining(","));
	}
	
}
