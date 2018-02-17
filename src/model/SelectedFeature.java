package model;

import java.util.ArrayList;

/**
*
* @author EG
*/
public class SelectedFeature {
	private String feature;
	private ArrayList<String> attributes;
	
	public SelectedFeature(String feature, ArrayList<String> attributes){
		this.feature = feature;
		this.attributes = attributes;
	}
	
	public String getFeature() {
		return feature;
	}
	
	public ArrayList<String> getAttributes() {
		return attributes;
	}
}
