package model.instance;

import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class Child extends Respondent {
	
	public static final int ANSWER_INDEX_START = 3;
	
	private int gender;
	private int age;
	
	public Child(String name,
			int gender,
			int age,
			LinkedHashMap<String, Integer> answerMap) {
		
		super(name, answerMap);
		
		this.gender = gender;
		this.age = age;
		
	}
	
	public String getName() {
		return name;
	}

	public int getGender() {
		return gender;
	}

	public int getAge() {
		return age;
	}

	public LinkedHashMap<String, Integer> getAnswerMap() {
		return answerMap;
	}
	
	@Override
	public String getTag() {
		return tag;
	}
	
	@Override
	public Child clone() {
		
		LinkedHashMap<String, Integer> tempAnswerMap = new LinkedHashMap<String, Integer>();
		for(String question : this.answerMap.keySet()) {
			tempAnswerMap.put(question, this.answerMap.get(question));
		}
		
		return new Child(this.name, this.gender, this.age, tempAnswerMap);
		
	}
	
	@Override
	public String toString() {
		return name + "," + gender + "," + age + "," + weights.stream().map(weight -> weight.toString()).collect(Collectors.joining(","));
	}

}
