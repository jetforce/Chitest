package model;

import java.util.ArrayList;

import model.instance.Instance;

public abstract class Group<T extends Instance> {
	
	protected String tag;
	protected ArrayList<T> members;
	
	protected Group(String tag,
			ArrayList<T> members) {
		
		this.tag = tag;
		this.members = members;
		
	}
	
	public String getTag() {
		return tag;
	}
	
	public ArrayList<T> getMembers() {
		return members;
	}
	
}
