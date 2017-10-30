package util.test;

import java.util.ArrayList;

import model.Answer;
import model.ComparisonGroup;
import model.Question;
import model.result.ZResult;

public class ZTest {
	
	private static final ZTest INSTANCE = new ZTest();
	
	private ZTest() {}
	
	public static ZTest getInstance() {
		return ZTest.INSTANCE;
	}
	
	public ArrayList<ZResult> evaluate(ArrayList<ComparisonGroup> sampleGroups,
			ComparisonGroup populationGroup,
			ArrayList<Question> questions,
			double z) {
		
		ArrayList<ZResult> results = new ArrayList<ZResult>();
		
		for(Question question : questions) {
			
			for(ComparisonGroup sampleGroup : sampleGroups) {
				
				Question sampleQuestion = sampleGroup.getQuestionMap().get(question.getQuestion());
				Question populationQuestion = populationGroup.getQuestionMap().get(question.getQuestion());
				
				ZResult result;
				for(Answer answer : sampleQuestion.getAnswerMap().values()) {
					if(answer.getAnswer() == 1)
						if((result = test(sampleGroup, sampleQuestion, populationQuestion, answer,
								populationQuestion.getAnswerMap().get(answer.getAnswer()), z)) != null)
							results.add(result);
				}
				
			}
		}
		
		return results;
		
	}
	
	private ZResult test(ComparisonGroup sampleGroup, 
			Question sampleQuestion, 
			Question populationQuestion, 
			Answer sampleAnswer,
			Answer populationAnswer,
			double z) {
		
		ZResult result = null;
		
		String feature = sampleQuestion.getQuestion();
		String sample = sampleGroup.getTag();
		String response = sampleAnswer.toString();
		int populationSize = populationQuestion.getValidAnswerCount();
		double populationProportion = populationAnswer.getProportion(populationQuestion.getValidAnswerCount());
		int sampleSize = sampleQuestion.getValidAnswerCount();
		double sampleProportion = sampleAnswer.getProportion(sampleQuestion.getValidAnswerCount());
		double standardError = getStandardError(sampleProportion, sampleSize);
		double[] confidenceInterval = getConfidenceInterval(sampleProportion, z, standardError);
		double proportionDifference = Math.abs(populationProportion - sampleProportion);
		double standardProportionDifference = Math.abs(populationProportion - sampleProportion) / standardError;
		
		if(populationProportion < confidenceInterval[0] || populationProportion > confidenceInterval[1]) {
			result = new ZResult(feature, sample, response, populationSize, populationProportion, sampleSize, sampleProportion, 
					standardError, confidenceInterval, proportionDifference, standardProportionDifference);
		}
				
		
		return result;
	}
	
	private double getStandardError(double proportion, int sampleSize) {
		
		double standardError = 0.0;
		
		try {
			standardError = Math.sqrt(proportion * (1 - proportion) / sampleSize);
		} catch (ArithmeticException e) {
			standardError = Answer.INVALID;
		}
		
		return standardError;
		
	}
	
	private double[] getConfidenceInterval(double proportion, double z, double standardError) {
		
		double[] confidenceInterval = new double[2];
		
		confidenceInterval[0] = proportion - z * standardError;
		confidenceInterval[1] = proportion + z * standardError;
		
		return confidenceInterval;
		
	}
}
