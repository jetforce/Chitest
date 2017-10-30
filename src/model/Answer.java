package model;

import java.util.ArrayList;

import model.instance.Respondent;

public class Answer {
	
	public static final int INVALID = -1;
	
	private int answer;
	private ArrayList<Respondent> members;
	
	public Answer(int answer) {
		
		this.answer = answer;
		this.members = new ArrayList<Respondent>();
		
	}
	
	public int getAnswer() {
		return answer;
	}
	
	public ArrayList<Respondent> getMembers() {
		return members;
	}
	
	public void addMember(Respondent member) {
		members.add(member);
	}
	
	public int getMemberCount() {
		return members.size();
	}
	
	public double getProportion(int total) {
		
		double proportion;
		
		try {
			proportion = members.size() / (total * 1.0);
		} catch (ArithmeticException e) {
			proportion = Answer.INVALID;
		}
		
		return proportion;
	}
	
	@Override
	public Answer clone() {
		
		Answer temp = new Answer(this.answer);
		for(Respondent member : this.members) {
			temp.addMember(member.clone());
		}
		return temp;
		
	}
	
	@Override
	public String toString() {
		return String.valueOf(answer);
	}
	
}
