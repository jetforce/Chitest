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
    private ArrayList<Response> responseList;
    private boolean notSay, unmatchedVal;

    public Feature(){
        this.responseList = new ArrayList<>();
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
}
