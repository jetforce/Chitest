package factory;

import java.util.LinkedHashMap;

import model.Answer;
import model.instance.Child;
import model.instance.Respondent;

public class ChildRespondentFactory implements InstanceAbstractFactory {
	
	private String name;
	private int gender;
	private int age;
	private LinkedHashMap<String, Integer> answerMap;
	
	public ChildRespondentFactory(String content, 
			String[] questions) {
		
		String[] splitContent = content.split(",", -1);
		this.name = splitContent[0];
		try {
			this.gender = Integer.parseInt(splitContent[1]);
		} catch (NumberFormatException e) {
			this.gender = Answer.INVALID;
		}
		try {
			this.age = Integer.parseInt(splitContent[2]);
		} catch (NumberFormatException e) {
			this.age = Answer.INVALID;
		}
		
		answerMap = new LinkedHashMap<String, Integer>();
		for(int i = Child.ANSWER_INDEX_START; i < questions.length; i++) {
			try {
				if(Integer.parseInt(splitContent[i]) != 99)
					answerMap.put(questions[i], Integer.parseInt(splitContent[i]));
				else
					answerMap.put(questions[i], Answer.INVALID);
			} catch (NumberFormatException e) {
				answerMap.put(questions[i], Answer.INVALID);
			}
		}
		
	}
	
	@Override
	public Respondent createInstance() {
		return new Child(name, gender, age, answerMap);
	}

}
