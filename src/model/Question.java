package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

public class Question {
	
	private String question;
	private LinkedHashMap<Integer, Answer> answerMap;
	
	public Question(String question) {
		
		this.question = question;
		this.answerMap = new LinkedHashMap<Integer, Answer>();
		
	}
	
	public String getQuestion() {
		return question;
	}
	
	public LinkedHashMap<Integer, Answer> getAnswerMap() {
		return answerMap;
	}
	
	public void addAnswer(int answer) {
		answerMap.put(answer, new Answer(answer));
	}
	
	public ArrayList<Integer> getSortedAnswers() {
		ArrayList<Integer> sortedAnswers = new ArrayList<>();
		sortedAnswers.addAll(answerMap.keySet());
		Collections.sort(sortedAnswers);
		return sortedAnswers;
		
	}
	
	public int getValidAnswerCount() {
		
		int count = 0;
		
		for(Answer answer : answerMap.values())
			count += answer.getMemberCount();
		
		return count;
		
	}
	
	@Override
	public Question clone() {
		
		Question temp = new Question(this.question);
		for(Integer answer : answerMap.keySet()) {
			temp.getAnswerMap().put(answer, this.answerMap.get(answer).clone());
		}
		return temp;
		
	}
	
	@Override
	public String toString() {
		return "Question: " + question;
	}
	
}
