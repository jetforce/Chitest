package model.instance;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public abstract class Respondent extends Instance {
	
	protected final String name;
	protected final LinkedHashMap<String, Integer> answerMap;
	
	protected Respondent(String name,
			LinkedHashMap<String, Integer> answerMap) {
		
		super(new ArrayList<>(answerMap.values().stream().map(answer -> answer.doubleValue()).collect(Collectors.toList())));
		
		this.name = name;
		this.answerMap = answerMap;
	}
	
	public String getName() {
		return name;
	}
	
	public LinkedHashMap<String, Integer> getAnswerMap() {
		return answerMap;
	}
	
	public abstract Respondent clone();
}
	