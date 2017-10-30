package util.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import model.ComparisonGroup;
import model.Question;
import model.result.ChiSquareResult;

public class ChiSquareTest {
	
	private final LinkedHashMap<Double, Double> UPPER_TAIL = new LinkedHashMap<Double, Double>();
	private final LinkedHashMap<Double, Double> LOWER_TAIL = new LinkedHashMap<Double, Double>();
	private double confidenceLevel = 0.99;
	
	public ChiSquareTest(double confidenceLevel) {
		
		this.confidenceLevel = confidenceLevel;
		loadTable();
		
	}
	
	private void loadTable() {
		UPPER_TAIL.put(0.90, 2.706);
		UPPER_TAIL.put(0.95, 3.841);
		UPPER_TAIL.put(0.99, 6.635);
		
		LOWER_TAIL.put(0.10, 0.016);
		LOWER_TAIL.put(0.05, 0.004);
		LOWER_TAIL.put(0.01, 0.000);
		
	}
	
	public ArrayList<ChiSquareResult> evaluate(ArrayList<ComparisonGroup> comparisonGroups,
			ArrayList<Question> questions) {
		
		ArrayList<ChiSquareResult> results = new ArrayList<ChiSquareResult>();
		double upperCV = UPPER_TAIL.get(confidenceLevel);
		double lowerCV = LOWER_TAIL.get(new BigDecimal(1 - confidenceLevel).setScale(2, RoundingMode.HALF_UP).doubleValue());
		
		
		for(Question question : questions) {
			
			double[][] observed = getObserved(question, comparisonGroups);
			double[][] expected = getExpected(observed);
			double chiSquareValue = computeChiSquareValue(observed, expected);
			
			if(chiSquareValue > upperCV || chiSquareValue < lowerCV)
				results.add(new ChiSquareResult(question.getQuestion(), chiSquareValue));
			
		}
		
		return results;
		
	}
	
	private double[][] getObserved(Question question,
			ArrayList<ComparisonGroup> comparisonGroups) {
		
		int groupCount = comparisonGroups.size();
		int answerCount = question.getAnswerMap().size();
		double[][] observed = new double[groupCount + 1][answerCount + 1];
		
		for(int i = 0; i < observed.length; i++)
			for(int j = 0; j < observed[0].length; j++)
				observed[i][j] = 0;
		
		for(int i = 0; i < groupCount; i++) {
			
			Question groupQuestion = comparisonGroups.get(i).getQuestionMap().get(question.getQuestion());
			
			for(int j = 0; j < answerCount; j++) {
				int count = groupQuestion.getAnswerMap().get(groupQuestion.getSortedAnswers().get(j)).getMemberCount();
				observed[i][j] = count;
				observed[groupCount][j] += count;
				observed[i][answerCount] += count;
				observed[groupCount][answerCount] += count;
			}
			
		}
		
		return observed;
		
	}
	
	private double[][] getExpected(double[][] observed) {
		
		double[][] expected = new double[observed.length - 1][observed[0].length - 1];
		
		int rowCount = expected.length;
		int colCount = expected[0].length;
		
		for(int i = 0; i < rowCount; i++) {
			
			for(int j = 0; j < colCount; j++) {
				expected[i][j] = observed[i][colCount] * observed[rowCount][j] / observed[rowCount][colCount];
			}
			
		}
		
		return expected;
		
	}
	
	private double computeChiSquareValue(double[][] observed,
			double[][] expected) {
		
		double chiSquareValue = 0.0;
		
		for(int i = 0; i < expected.length; i++)
			for(int j = 0; j < expected[0].length; j++)
				chiSquareValue += Math.pow(observed[i][j] - expected[i][j], 2)/expected[i][j];
		
		return chiSquareValue;
		
	}
	
}
