package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import model.instance.Respondent;

public class ComparisonGroup extends Group<Respondent> {
	
	protected final String groupType;
	protected LinkedHashMap<String, Question> questionMap;
	
	public ComparisonGroup(String tag,
			String groupType) {
		
		super(tag, new ArrayList<Respondent>());
		
		this.groupType = groupType;
		this.questionMap = new LinkedHashMap<String, Question>();
		
	}
	
	public String getGroupType() {
		return groupType;
	}
	
	public Map<String, Question> getQuestionMap() {
		return questionMap;
	}
	
	public void setQuestionMap(Map<String, Question> questionMap) {
		
		for(String question : questionMap.keySet()) {
			this.questionMap.put(question, questionMap.get(question).clone());
		}
		
	}
	
	public void addMember(Respondent member) {
		members.add(member);
	}
	
	public int getMemberCount() {
		return members.size();
	}
	
	public void groupMembersByAnswer() {
		
		for(Question question : questionMap.values()) {
			for(Respondent member : members) {
				Integer respondentAnswer = member.getAnswerMap().get(question.getQuestion());
				if(question.getAnswerMap().containsKey(respondentAnswer)) {
					question.getAnswerMap().get(respondentAnswer).addMember(member);
				}	
			}
		}
		
	}
	
	@Override
	public String toString() {
		return "Group: " + tag;
	}
	
}
