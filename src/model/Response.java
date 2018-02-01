/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Arces
 */
public class Response {
    private String key, description, group;
    
    
    
    /**
     * 
     * @return the group the response is in
     */
    public String getGroup() {
		return group;
	}

    /**
     * 
     * @param group the group the response is in
     */
	public void setGroup(String group) {
		this.group = group;
	}

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
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
}
