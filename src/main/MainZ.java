package main;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import factory.ChildRespondentFactory;
import factory.InstanceFactory;
import grouper.AgeGrouper;
import grouper.GenderGrouper;
import model.Answer;
import model.ComparisonGroup;
import model.Question;
import model.instance.Child;
import model.instance.Respondent;
import model.result.ChiSquareResult;
import model.result.ZResult;
import util.CSVIO;
import util.test.ChiSquareTest;
import util.test.ZTest;

public class MainZ {
	
	// 90 = 1.645
	// 95 = 1.960
	// 98 = 2.326
	// 99 = 2.576
	
	private final static double z = 2.576;
	private final static double x2 = 0.99;
	private final static String Z_AGE_RESULT_FILE = "Z-Results-Age-";
	private final static String Z_GENDER_RESULT_FILE = "Z-Results-Gender-";
	private final static String X2_AGE_RESULT_FILE = "X2-Results-Age-";
	private final static String X2_GENDER_RESULT_FILE = "X2-Results-Gender-";
	
	public static void main(String args[]) {
		
		System.out.println("- START -");
		
		long timestamp = System.currentTimeMillis();
		
		// GENERATE CHILDREN AND ADD TO GROUPS
		// GENERATE QUESTIONS AND ANSWERS
		ArrayList<String> content = CSVIO.read("CleanDataToMine.csv");
		ArrayList<Respondent> respondents = new ArrayList<Respondent>();
		String[] questionString = content.remove(0).split(",");
		LinkedHashMap<String, Question> questions = new LinkedHashMap<String, Question>();
		for(String childContent : content) {
			
			Child child = (Child) InstanceFactory.createInstance(new ChildRespondentFactory(childContent, questionString));
			respondents.add(child);
			
			for(int i = Child.ANSWER_INDEX_START; i < questionString.length; i++) {
				
				// GET QUESTION IF IN MAP
				Question currentQuestion;
				if(!questions.containsKey(questionString[i])) {
					currentQuestion = new Question(questionString[i]);
					questions.put(questionString[i], currentQuestion);
				} else {
					currentQuestion = questions.get(questionString[i]);
				}
				
				// ADD ANSWER TO QUESTION
				int childAnswer = child.getAnswerMap().get(questionString[i]);
				if(!currentQuestion.getAnswerMap().containsKey(childAnswer) && 
						childAnswer != Answer.INVALID)
					currentQuestion.addAnswer(childAnswer);
				
			}
			
		}
		
		LinkedHashMap<String, ComparisonGroup> ageGroups = new AgeGrouper().group(respondents);
		LinkedHashMap<String, ComparisonGroup> genderGroups = new GenderGrouper().group(respondents);
		
		for(ComparisonGroup group : ageGroups.values()) {
			group.setQuestionMap(questions);
			group.groupMembersByAnswer();
		}
		
		for(ComparisonGroup group : genderGroups.values()) {
			group.setQuestionMap(questions);
			group.groupMembersByAnswer();
		}
		
		CSVIO.write(ZResult.HEADER, Z_AGE_RESULT_FILE + timestamp + ".csv");
		CSVIO.write(ZResult.HEADER, Z_GENDER_RESULT_FILE + timestamp + ".csv");
		
		CSVIO.write(ChiSquareResult.HEADER, X2_AGE_RESULT_FILE + timestamp + ".csv");
		CSVIO.write(ChiSquareResult.HEADER, X2_GENDER_RESULT_FILE + timestamp + ".csv");
		
		ArrayList<ComparisonGroup> tempGroup = new ArrayList<ComparisonGroup>(ageGroups.values());
		tempGroup = new ArrayList<>(tempGroup.subList(0, ageGroups.values().size() - 1));
		ArrayList<ZResult> zResults = ZTest.getInstance().evaluate(tempGroup, ageGroups.get(AgeGrouper.AGE_GROUPS[3]), 
				new ArrayList<Question>(questions.values()), z);
		CSVIO.write((ArrayList<String>)zResults.stream().map(result -> result.toString()).collect(Collectors.toList()), Z_AGE_RESULT_FILE + timestamp + ".csv");
		
		ArrayList<ChiSquareResult> cResults = new ChiSquareTest(x2).evaluate(tempGroup, new ArrayList<Question>(questions.values()));
		CSVIO.write((ArrayList<String>)cResults.stream().map(result -> result.toString()).collect(Collectors.toList()), X2_AGE_RESULT_FILE + timestamp + ".csv");
		
		tempGroup = new ArrayList<ComparisonGroup>();
		tempGroup.add(genderGroups.get(GenderGrouper.GENDER_GROUPS[1]));
		zResults = ZTest.getInstance().evaluate(tempGroup, genderGroups.get(GenderGrouper.GENDER_GROUPS[2]), 
				new ArrayList<Question>(questions.values()), z);
		CSVIO.write((ArrayList<String>)zResults.stream().map(result -> result.toString()).collect(Collectors.toList()), Z_GENDER_RESULT_FILE + timestamp + ".csv");
		
		tempGroup = new ArrayList<ComparisonGroup>(genderGroups.values());
		tempGroup = new ArrayList<>(tempGroup.subList(0, genderGroups.values().size() - 1));
		cResults = new ChiSquareTest(x2).evaluate(tempGroup, new ArrayList<Question>(questions.values()));
		CSVIO.write((ArrayList<String>)cResults.stream().map(result -> result.toString()).collect(Collectors.toList()), X2_GENDER_RESULT_FILE + timestamp + ".csv");
		
		System.out.println("- END -");
		
	}
	
}
