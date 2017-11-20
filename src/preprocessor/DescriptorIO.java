package preprocessor;

import model.Feature;
import model.Response;
import util.SwingUpdater;
import view.MainFrame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Arces
 */
public class DescriptorIO {

    public void readQuestions(ArrayList<Feature> varQuestions, ArrayList<Feature> valQuestions, String variableLabel, String valuesLabel) {
        BufferedReader varBr, valBr;
        String line;
        String[] lineSplit;
        Feature question = null;
        Response response;
        boolean readResponses = false;

        try {
            varBr = new BufferedReader(new FileReader(new File(variableLabel)));
            valBr = new BufferedReader(new FileReader(new File(valuesLabel)));

            //Get Questions from value
            while ((line = varBr.readLine()) != null) {
                if (line.contains("=")) {
                    lineSplit = line.split("=");
                    question = new Feature();
                    question.setCode(lineSplit[0].trim());
                    question.setDescription(lineSplit[1].trim().replace("\"", "").replace(",", ";"));
                    
                    varQuestions.add(question);                    
                }
            }
            
            //Get Questions from value
            while ((line = valBr.readLine()) != null) {
                if (line.contains("value")) {
                    question = new Feature(); //create a new question
                    valQuestions.add(question);
                    question.setCode(line.split(" ")[1]);
                    readResponses = true;
                } else if (readResponses) {
                    response = new Response();
                    line = line.replace("\t", "");
                    lineSplit = line.split("=");
                    response.setKey(lineSplit[0].trim());
                    response.setDescription(lineSplit[1].replace("\"", "").replace(";", "").replace(",", ";"));
                    question.addResponse(response);
                    if (line.contains(";")) {
                        readResponses = false;
                    }
                }
            }
            
            varBr.close();
            valBr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportVariables(ArrayList<Feature> questionList, String filePath) {
     //   System.out.println("\n********\nEXPORTING VARIABLES\n*******");
        String export = "";
        PrintWriter pw;
        for (Feature q : questionList) {
       //     System.out.println("Exporting " + q.getCode() + ". . .");        	
            export += "V," + q.getCode() + "," + q.getDescription() + "\n";
            for (Response r : q.getResponseList()) {
                export += r.getKey() + "," + r.getDescription() + "\n";
            }
        }
        
        try {
            pw = new PrintWriter(new File(filePath));
            pw.write(export);

            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
