/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preprocessor;

import model.Column;
import model.Entry;
import model.Feature;
import model.Response;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Arces
 */
public class Converter {

    private ArrayList<String> codeList;

    public Converter() {
        this.codeList = new ArrayList<>();

        //these are the special cases needed to be considered
        codeList.add("g1a");
        codeList.add("g1b");
        codeList.add("g1c");
        codeList.add("g2a");
        codeList.add("g2b");
        codeList.add("g2c");
        codeList.add("g2d");
        codeList.add("g3a");
        codeList.add("g3b");
        codeList.add("g3c");
        codeList.add("g3d");
    }

    public ArrayList<Entry> convertEntries(ArrayList<Entry> oldEntries, ArrayList<Column> columns, ArrayList<Feature> questions) {
        ArrayList<Entry> entryList = new ArrayList<>();
        Entry newEntry;
        ArrayList<String> responses;
        int count = 0;
        System.out.println("\n***************\nCONVERTING ENTRIES" + "\n************************");

        for (Entry old : oldEntries) {
            newEntry = new Entry();
            newEntry.setLabel(old.getLabel());
            entryList.add(newEntry);
        }

        for (Column col : columns) {
            System.out.println("Processing " + count++ + "/" + columns.size() + ". . .");

            responses = col.getResponses();
            for (int i = 0; i < responses.size(); i++) {
                entryList.get(i).addFeature(changeResponse(responses.get(i), responses, findQuestion(questions, col.getName())));
            }
        }
        return entryList;
    }

    //converts questions to groups
    public ArrayList<Feature> convertQuestions(ArrayList<Column> columns, ArrayList<Feature> questions) {
        ArrayList<Feature> questionList = new ArrayList<>();
        Feature newQ, findQ;
        Set<String> responses;
        Response r;

        for (Column col : columns) {
            newQ = new Feature();
            questionList.add(newQ);
            newQ.setCode(col.getName());
            responses = new HashSet<>(col.getResponses());
            responses.remove("99");
            responses.remove("");

            for (String response : responses) {
                r = new Response();
                r.setKey(response);
                r.setDescription(response);
                newQ.addResponse(r);
            }

            if ((findQ = findQuestion(questions, col.getName())) != null) {
                newQ.setNotSay(findQ.isNotSay());
                newQ.setDescription(findQ.getDescription());

                if (findQ.getResponseList().size() > 0) {
                    for (Response resp : findQ.getResponseList()) {
                        newQ.addResponse(resp);
                    }

                }

                //for checking
                if (findQ.getResponseList().size() == newQ.getResponseList().size()) {
                    newQ.setUnmatchedVal(false);
                } else {
//                    System.out.println("UNBALANCED!");
                    newQ.setUnmatchedVal(true);
                }
            }
        }

        //group questions
        groupQuestions(questionList);

        return questionList;
    }

    private void groupQuestions(ArrayList<Feature> questions) {
        Response r0, r1;
        for (Feature q : questions) {
            if (q.isNotSay()) {
                q.removeLastResp();
            }

            if (!q.isUnmatched()) {
                if (q.getResponseList().size() > 2) {
                    q.getResponseList().clear();

                    r0 = new Response();
                    r0.setKey("0");
                    r0.setDescription("Group 1");
                    r1 = new Response();
                    r1.setKey("1");
                    r1.setDescription("Group 2");
                    q.addResponse(r0);
                    q.addResponse(r1);
                }
            }
        }
    }

    private Feature findQuestion(ArrayList<Feature> questions, String code) {
        Feature question = null;
        boolean isFound = false;

        for (int i = 0; i < questions.size() && !isFound; i++) {
            if (questions.get(i).getCode().equals(code)) {
                question = questions.get(i);
                isFound = true;
            }
        }

        return question;
    }

    private String changeResponse(String response, ArrayList<String> responses, Feature question) {
        String updatedResp = "";
        String notSay = "";
        boolean checkPrefer = false;
        Set<String> responseSet = question == null ? new HashSet<>(responses) : new HashSet<>();

        if (question != null) {
            generateResponseSet(responseSet, responses, question);
        }
        //remove 99 and blank from set
        responseSet.remove("");
        responseSet.remove("99");

        //change the response according to its group
        //System.out.println(response);
        if (responseSet.size() > 2 && (question != null ? question.isNotSay() : false)) {
            notSay = (String) responseSet.toArray()[responseSet.toArray().length - 1];
            responseSet.remove(notSay);
            checkPrefer = true;
        }

        Iterator i = responseSet.iterator();

        //check blank for special cases
        if (question != null && codeList.contains(question.getCode()) && response.equals("")) {
            updatedResp = (String) i.next();
        } else if (response.equals("99") || response.equals("")) {
            updatedResp = "-1";
        } else if (responseSet.size() > 2) {
            //last = (String) responseSet.toArray()[responseSet.toArray().length - 1];
            if (i.next().equals(response) || i.next().equals(response)) {
                updatedResp = "0";
            } else if (notSay.equals(response) && (question != null ? question.isNotSay() : false)) {
                updatedResp = "-1";
            } else {
                updatedResp = "1";
            }
        } else {
            updatedResp = response;
            if (checkPrefer) {
                if (notSay.equals(response)) {
                    updatedResp = "-1";
                }
            }
        }

        responseSet.clear();
        return updatedResp;
    }

    public void generateResponseSet(Set<String> responseSet, ArrayList<String> responses, Feature question) {
        boolean hasPrefer = false;
        for (Response r : question.getResponseList()) {
            responseSet.add(r.getKey());
            if (r.getDescription().toLowerCase().contains("prefer not to say")) {
                hasPrefer = true;
            }
        }

        question.setNotSay(hasPrefer);
    }
}
