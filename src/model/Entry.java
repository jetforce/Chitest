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
public class Entry {
    private ArrayList<String> features;
    private String label;
    
    public Entry(){
        this.features = new ArrayList<>();
    }

    /**
     * @return the features
     */
    public ArrayList<String> getFeatures() {
        return features;
    }

    /**
     * @param features the features to set
     */
    public void setFeatures(ArrayList<String> features) {
        this.features = features;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }
    
    public void addFeature(String feature){
        features.add(feature);
    }
    
}