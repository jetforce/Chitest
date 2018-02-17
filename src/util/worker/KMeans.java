package util.worker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import model.Centroid;
import model.instance.Instance;
import util.CSVIO;
import util.SwingUpdater;
import view.MainFrame;

public class KMeans extends SwingWorker<LinkedHashMap<String, Centroid>, Integer> {
	
	private final String CLUSTER = "Cluster";
	
	private int k;
	private int iterations;
	private ArrayList<Instance> instances;
	private String savePath;
	private MainFrame mainFrame;
	
	public KMeans(int k,
			int iterations,
			ArrayList<Instance> instances,
			String savePath,
			MainFrame mainFrame) {
		
		this.k = k;
		this.iterations = iterations;
		this.instances = instances;
		this.savePath = savePath;
		this.mainFrame = mainFrame;
		
	}
	
	public LinkedHashMap<String, Centroid> group() {
		
		LinkedHashMap<String, Centroid> centroids = initCentriods(instances.get(0).getWeights().size());
		
		for(int i = 0; i < iterations; i++) {
			
			System.out.println(i);
			
			reinitMembers(centroids);
			groupMembers(centroids, instances);
			adjustWeights(centroids.values());
			
		}
		
		return centroids;
		
	}
	
	@Override
	protected LinkedHashMap<String, Centroid> doInBackground() {
		
		LinkedHashMap<String, Centroid> centroids = initCentriods(instances.get(0).getWeights().size());
		
		for(int i = 0; i < iterations; i++) {
			
			publish(new Integer((int)(((float)i) / iterations * 100)));
			
			reinitMembers(centroids);
			groupMembers(centroids, instances);
			adjustWeights(centroids.values());
			
		}
		
		return centroids;
		
	}
	
	@Override
	protected void process(List<Integer> values) {
		
		for(Integer value : values) {
			mainFrame.getProgressBarKMeansStatus().setValue(value);
		}
		
	}
	
	@Override
	protected void done() {
		
		try {
			
			LinkedHashMap<String, Centroid> centroids = get();
			
			long timestamp = System.currentTimeMillis();
			
			ArrayList<String> output = new ArrayList<String>();
			for(Centroid centroid : centroids.values()) {
				//System.out.println(centroid.getWeights());
				output.clear();
				for(Instance instance : centroid.getMembers()) {
					output.add(instance.toString());
				}
				String outputSaveFilePath = savePath + centroid.getTag() + "-" + timestamp + ".csv";
				CSVIO.write(output, outputSaveFilePath);
				
				SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaKMeansStatus(), centroid.getTag() + " saved in " + outputSaveFilePath + "\n");
				
			}
			
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
	}
	
	private LinkedHashMap<String, Centroid> initCentriods(int weightCount) {
		
		LinkedHashMap<String, Centroid> centroids = new LinkedHashMap<String, Centroid>();
		
		for(int i = 0; i < k; i++) {
			centroids.put(CLUSTER + " " + i, new Centroid(CLUSTER + " " + i, weightCount));
		}
		
		return centroids;
		
	}
	
	private void reinitMembers(LinkedHashMap<String, Centroid> centroids) {
		
		for(Centroid centroid : centroids.values()) {
			centroid.reInitMembers();
		}
		
	}
	
	private void groupMembers(LinkedHashMap<String, Centroid> centroids,
			ArrayList<Instance> respondents) {
		
		for(Instance respondent : respondents) {
			
			double lowestDistance = Double.MAX_VALUE;
			Centroid nearestCentroid = centroids.get(centroids.keySet().toArray()[0]);
			for(Centroid centroid : centroids.values()) {
				double distance = getDistance(respondent.getWeights(), centroid.getWeights());
				if(distance < lowestDistance) {
					nearestCentroid = centroid;
					lowestDistance = distance;
				}
			}
			
			nearestCentroid.addMember(respondent);
			
		}
		
	}
	
	private double getDistance(ArrayList<Double> respondentWeights,
			ArrayList<Double> centroidWeights) {
		
		double distance = 0;
		int validFeatureCount = 0;
		
		int weightCount = respondentWeights.size();
		for(int i = 0; i < weightCount; i++) { 
			if(Double.isFinite(respondentWeights.get(i))) {
				distance += Math.pow(respondentWeights.get(i) - centroidWeights.get(i), 2);	
				validFeatureCount++;
			}
		}
		
		if(validFeatureCount == 0)
			distance = Double.MAX_VALUE;
		else
			distance /= validFeatureCount;
		
		return distance;
		
	}
	
	private void adjustWeights(Collection<Centroid> centroids) {
		
		for(Centroid centroid : centroids) {
			
			ArrayList<Double> centroidWeights = centroid.getWeights();
			
			int weightCount = centroidWeights.size();
			for(int i = 0; i < weightCount; i++) {
				
				double total = 0.0;
				int validResponseCount = 0;
				ArrayList<Instance> members = centroid.getMembers();
				
				for(Instance member : members) {
					if(Double.isFinite(member.getWeights().get(i))) {
						total += member.getWeights().get(i);
						validResponseCount++;
					}
				}
				
				if(total != 0 && validResponseCount != 0)
					centroidWeights.set(i, total/validResponseCount);
				
			}
			
		}
		
	}
	
}
