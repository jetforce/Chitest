package model.result;

import java.text.DecimalFormat;

public class ZResult extends Result {
	
	public static final String HEADER = "FEATURE,SAMPLE,RESPONSE,POPULATION SIZE,POPULATION PROPORTION(P),"
			+ "SAMPLE SIZE(n),SAMPLE PROPORTION(p),STANDARD ERROR(SE),LOWER LIMIT,UPPER LIMIT,"
			+ "PROPORTION DIFFERENCE,STANDARD PROPORTION DIFFERENCE";
	

	private final String sample;
	private final String response;
	private final int populationSize;
	private final double populationProportion;
	private final int sampleSize;
	private final double sampleProportion;
	private final double standardError;
	private final double[] confidenceInterval;
	private final double proportionDifference;
	private final double standardProportionDifference;
	
	public ZResult(String feature,
			String sample,
			String response,
			int populationSize,
			double populationProportion,
			int sampleSize,
			double sampleProportion,
			double standardError,
			double[] confidenceInterval,
			double proportionDifference,
			double standardProportionDifference) {
		
		super(feature);
		
		this.sample = sample;
		this.response = response;
		this.populationSize = populationSize;
		this.populationProportion = populationProportion;
		this.sampleSize = sampleSize;
		this.sampleProportion = sampleProportion;
		this.standardError = standardError;
		this.confidenceInterval = confidenceInterval;
		this.proportionDifference = proportionDifference;
		this.standardProportionDifference = standardProportionDifference;
		
	}

	public String getSample() {
		return sample;
	}
	
	public String getResponse() {
		return response;
	}

	public int getPopulationSize() {
		return populationSize;
	}

	public double getPopulationProportion() {
		return populationProportion;
	}

	public int getSampleSize() {
		return sampleSize;
	}

	public double getSampleProportion() {
		return sampleProportion;
	}
	
	public double getStandardError() {
		return standardError;
	}

	public double[] getConfidenceInterval() {
		return confidenceInterval;
	}

	public double getProportionDifference() {
		return proportionDifference;
	}

	public double getStandardProportionDifference() {
		return standardProportionDifference;
	}

	@Override
	public String toString() {
		
		DecimalFormat formatter = new DecimalFormat("###.#%");
		
		return feature + "," + sample + "," + response + "," + populationSize + 
				"," + formatter.format(populationProportion) + "," + sampleSize + 
				"," + formatter.format(sampleProportion) + "," + standardError + 
				"," + formatter.format(confidenceInterval[0]) + 
				"," + formatter.format(confidenceInterval[1]) + "," + formatter.format(proportionDifference) + 
				"," + standardProportionDifference;
	}
	
}
