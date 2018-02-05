/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Arces
 */
public class Feature {
    private String code, description;
    private ArrayList<Response> responseList, groupedResponseList;
    private ArrayList<String>groups; 
    private boolean notSay, unmatchedVal;

    public Feature(){
        this.responseList = new ArrayList<>();
        this.setGroupedResponseList(new ArrayList<>());
        setGroups(new ArrayList<>());
    }
    
    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

	public ArrayList<Response> getGroupedResponseList() {
		return groupedResponseList;
	}

	public void setGroupedResponseList(ArrayList<Response> groupedResponseList) {
		this.groupedResponseList = groupedResponseList;
	}
	
	public void addGroupedResponse(Response response)
	{
		boolean isFound = false;
        for (int i = 0; i < groupedResponseList.size() && !isFound; i++) {
            if (groupedResponseList.get(i).getKey().equals(response.getKey())) {
            	groupedResponseList.get(i).setDescription(response.getDescription());
            	groupedResponseList.get(i).setGroup(response.getGroup());
                isFound = true;
            }
        }
        if (!isFound) {
        	getGroupedResponseList().add(response);
        }
	}

	/**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void addResponse(Response response) {
        //find if response is existing
        boolean isFound = false;
        for (int i = 0; i < responseList.size() && !isFound; i++) {
            if (responseList.get(i).getKey().equals(response.getKey())) {
                responseList.get(i).setDescription(response.getDescription());
                responseList.get(i).setGroup(response.getGroup());
                isFound = true;
            }
        }
        if (!isFound) {
            getResponseList().add(response);
        }
    }

    /**
     * @return the responseList
     */
    public ArrayList<Response> getResponseList() {
        return responseList;
    }

    /**
     * @param responseList the responseList to set
     */
    public void setResponseList(ArrayList<Response> responseList) {
        this.responseList = responseList;
    }

    /**
     * @return the notSay
     */
    public boolean isNotSay() {
        return notSay;
    }

    /**
     * @param notSay the notSay to set
     */
    public void setNotSay(boolean notSay) {
        this.notSay = notSay;
    }
    
    public void removeLastResp(){
        responseList.remove(responseList.size() - 1);
    }

    /**
     * @return the unmatchedVal
     */
    public boolean isUnmatched() {
        return unmatchedVal;
    }

    /**
     * @param noValues the unmatchedVal to set
     */
    public void setUnmatchedVal(boolean noValues) {
        this.unmatchedVal = noValues;
    }

	public ArrayList<String> getGroups() {
		return groups;
	}
	
	public void addToGroup(String s)
	{
		boolean isFound = false;
		for(String g : groups)
		{
			if(g.equals(s))
			{
				g = s;
				isFound = true;
			}
			
		}
		if(!isFound)
			groups.add(s);
	}

	public void setGroups(ArrayList<String> groups) {
		this.groups = groups;
	}
}
