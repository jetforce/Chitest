package model;

import java.util.ArrayList;
import java.util.Random;

import model.instance.Instance;

public class Centroid extends Group<Instance> {
	
	private ArrayList<Double> weights;
	
	public Centroid(String tag,
			int weightCount) {
		
		super(tag, new ArrayList<Instance>());
		
		this.weights = new ArrayList<Double>();
		Random random = new Random();
		for(int i = 0; i < weightCount; i++) {
			weights.add(random.nextDouble());
		}
		
	}
	
	public ArrayList<Double> getWeights() {
		return weights;
	}
	
	public ArrayList<Instance> getMembers() {
		return members;
	}
	
	public void reInitMembers() {
		this.members.clear();
	}
	
	public void addMember(Instance member) {
		this.members.add(member);
	}
	
	@Override
	public String toString() {
		return weights.toString();
	}
	
}
