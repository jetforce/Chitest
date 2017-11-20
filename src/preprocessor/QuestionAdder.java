/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preprocessor;

import model.Column;
import model.Feature;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Arces
 */
public class QuestionAdder {
    /*
        Returns features that are in the dataset but not in the var desc file
    */
    public ArrayList<String> addQuestions(ArrayList<Feature> questions, ArrayList<Column> columns) {
        boolean isFound = false;
        Scanner qSc, cSc;
        int cNum;
        Feature checkQ, newQ;
        String qCode, cCode;
        ArrayList<Feature> tempQuestions = new ArrayList<>();
        ArrayList<String> notFoundList = new ArrayList<>();
        tempQuestions.addAll(questions);

        System.out.println("********************\n" 
                + "ADDING MISSING QUESTIONS\n*********************");
        
        //find column in questions
        for (Column col : columns) {
            isFound = false;
            cSc = new Scanner(col.getName()).useDelimiter("[^0-9]+");
            for (int i = 0; i < questions.size() && !isFound; i++) {
                if (col.getName().equals(questions.get(i).getCode())) {
                    isFound = true;
                }
            }

            //find if starts with
            if (!isFound) {
                cNum = cSc.nextInt();
                cCode = col.getName().charAt(0) + "" + cNum;
                for (int i = 0; i < tempQuestions.size() && !isFound; i++) {
                    checkQ = tempQuestions.get(i);
                    qSc = new Scanner(checkQ.getCode()).useDelimiter("[^0-9]+");
                    qCode = checkQ.getCode().charAt(0) + "" + qSc.nextInt();
                    if (cCode.equals(qCode)) {
                        //System.out.println(varQ.getCode() + " vs. " + valQ.getCode());
                        //create new question then add
                        System.out.println("ADDING " + col.getName());
                        newQ = new Feature();
                        questions.add(newQ);
                        newQ.setCode(col.getName());
                        newQ.setResponseList(checkQ.getResponseList());
                        isFound = true;
                    }
                    qSc.close();
                }
            }

            if (!isFound) {
                notFoundList.add(col.getName());
            }
            cSc.close();
        }
        
        return notFoundList;
    }
}