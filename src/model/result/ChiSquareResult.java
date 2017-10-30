package model.result;

public class ChiSquareResult extends Result {
	
	public static final String HEADER = "FEATURE,CHI STATISTIC";
	
	private final double chiSquareValue;
	
	public ChiSquareResult(String feature,
			double chiSquareValue) {
		
		super(feature);
		this.chiSquareValue = chiSquareValue;
		
	}
	
	public double getChiSquareValue() {
		return chiSquareValue;
	}
	
	@Override
	public String toString() {
		return feature + "," + chiSquareValue;
	}
	
}
