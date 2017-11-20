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
public class Column {
    private String name;
    private ArrayList<String> responses;
    
    public Column(){
        this.responses = new ArrayList<>();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the responses
     */
    public ArrayList<String> getResponses() {
        return responses;
    }

    /**
     * @param responses the responses to set
     */
    public void setResponses(ArrayList<String> responses) {
        this.responses = responses;
    }
    
    public void addResponse(String response){
        responses.add(response);
    }
}
