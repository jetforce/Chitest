package preprocessor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import model.Feature;
import model.Response;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Arces
 */
public class QuestionMerger {

    public void mergeQuestions(ArrayList<Feature> varQuestions, ArrayList<Feature> valQuestions) {
        //get questions from variable
    	//For each variable in variables list, set its list of responses from the values list
        for (Feature varQ : varQuestions) {
            varQ.setResponseList(setResponses(varQ, valQuestions, true));
        }

       // System.out.println("********\nADDING QUESTIONS FROM VALUES NOT IN VARIABLES\n*******");
        //add questions from value
        for (Feature valQ : valQuestions) {
            checkQuestion(valQ, varQuestions);
        }
    }

    @SuppressWarnings("resource")
	private ArrayList<Response> setResponses(Feature varQ, ArrayList<Feature> valQuestions) {
        ArrayList<Response> responses = new ArrayList<>();
        boolean isFound = false;
        Feature valQ;
        Scanner varSc = new Scanner(varQ.getCode()).useDelimiter("[^0-9]+");
        Scanner valSc;
        int varNum;
        String varCode, valCode;

        //search first if question has equal
        for (int i = 0; i < valQuestions.size() && !isFound; i++) {
            valQ = valQuestions.get(i);
            if (varQ.getCode().equals(valQ.getCode())) {
//                System.out.println(varQ.getCode() + " vs. " + valQ.getCode());
                isFound = true;
                responses = valQ.getResponseList();
            }
        }

        if (!isFound) {
            varNum = varSc.nextInt();
            varCode = varQ.getCode().charAt(0) + "" + varNum;
            for (int i = 0; i < valQuestions.size() && !isFound; i++) {
                valQ = valQuestions.get(i);
                valSc = new Scanner(valQ.getCode()).useDelimiter("[^0-9]+");
                valCode = valQ.getCode().charAt(0) + "" + valSc.nextInt();
                if (varCode.equals(valCode)) {
                    //System.out.println(varQ.getCode() + " vs. " + valQ.getCode());
                    isFound = true;
                    responses = valQ.getResponseList();
                }
                valSc.close();
            }
        }

        varSc.close();
        return responses;
    }
    
    @SuppressWarnings("resource")
	private ArrayList<Response> setResponses(Feature varQ, ArrayList<Feature> valQuestions, boolean test) {
        ArrayList<Response> responses = new ArrayList<>();
        boolean isFound = false;
        Feature valQ;
        Scanner varSc = new Scanner(varQ.getCode()).useDelimiter("[^0-9]+");
        Scanner valSc;
        int varNum;
        String varCode, valCode;

        //search first if question has equal
        for (int i = 0; i < valQuestions.size() && !isFound; i++) {
            valQ = valQuestions.get(i);
            
          //If the variable code from the variables list is the same as the variable code from the values list
            if (varQ.getCode().equals(valQ.getCode())) {
//                System.out.println(varQ.getCode() + " vs. " + valQ.getCode());
                isFound = true;
                responses = valQ.getResponseList();//Set list of responses to variable code from variables list
            }
        }

        //If variable code from variables list does not find any same variable code from the values list
        if (!isFound) {
        	
            varNum = varSc.nextInt();
            varCode = varQ.getCode().charAt(0) + "" + varNum;
            
            System.out.println("Variable code not found: " + varCode);
            
            for (int i = 0; i < valQuestions.size() && !isFound; i++) {
                valQ = valQuestions.get(i);
                valSc = new Scanner(valQ.getCode()).useDelimiter("[^0-9]+");
                valCode = valQ.getCode().charAt(0) + "" + valSc.nextInt();
                if (varCode.equals(valCode)) {
                    //System.out.println(varQ.getCode() + " vs. " + valQ.getCode());
                    isFound = true;
                    responses = valQ.getResponseList();
                }
                valSc.close();
            }
        }

        varSc.close();
        return responses;
    }

    @SuppressWarnings("resource")
	public void checkQuestion(Feature valQ, ArrayList<Feature> varQuestions) {
        boolean isFound = false;
        Feature varQ;
        Scanner valSc = new Scanner(valQ.getCode()).useDelimiter("[^0-9]+");
        Scanner varSc;
        String varCode, valCode;
        int valNum;

        //check exactness
        for (int i = 0; i < varQuestions.size() && !isFound; i++) {
            varQ = varQuestions.get(i);
//            System.out.println(valQ.getCode() + " vs " + varQ.getCode());
            if (valQ.getCode().equals(varQ.getCode())) {
                isFound = true;
            }
        }

        //check if contains
        if (!isFound && Character.isDigit(valQ.getCode().charAt(valQ.getCode().length() - 1))) {
            valNum = valSc.nextInt();
            valCode = valQ.getCode().charAt(0) + "" + valNum;
            for (int i = 0; i < varQuestions.size() && !isFound; i++) {
                varQ = varQuestions.get(i);
                varSc = new Scanner(varQ.getCode()).useDelimiter("[^0-9]+");
                varCode = varQ.getCode().charAt(0) + "" + varSc.nextInt();
                if (valCode.equals(varCode)) {
                    //System.out.println(varQ.getCode() + " vs. " + valQ.getCode());
                    isFound = true;
                }
                varSc.close();
            }
        }

        if (!isFound) {
          //  System.out.println("Adding " + valQ.getCode());
            varQuestions.add(valQ);
        }
        
        valSc.close();
    }
}
