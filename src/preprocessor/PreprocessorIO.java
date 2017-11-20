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
import util.worker.Preprocessor;

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
public class PreprocessorIO {
	private Preprocessor worker;
	
	public PreprocessorIO(Preprocessor worker){
		this.worker = worker;
	}
	
    public ArrayList<Feature> readQuestions(String fileName) {
        BufferedReader br;
        String line;
        String[] lineSplit;
        ArrayList<Feature> questionList = new ArrayList<>();
        Feature question = null;
        Response response;
        boolean isParent = false;
        try {
            br = new BufferedReader(new FileReader(new File(fileName)));

            //remove parent questions
            while ((line = br.readLine()) != null) {
                lineSplit = line.split(",");
                if (line.startsWith("V")) {
                    if (!lineSplit[1].startsWith("l")) {
                        question = new Feature();
                        questionList.add(question);
                        question.setCode(lineSplit[1]);
                        question.setDescription(lineSplit[2]);

                        isParent = false;
                    } else {
                        isParent = true;
                    }
                } else {
                    if (!isParent) {
                        response = new Response();
                        question.addResponse(response);
                        response.setKey(lineSplit[0]);
                        response.setDescription(lineSplit[1]);
                    }
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questionList;
    }

    public ArrayList<Entry> readCSV(ArrayList<String> header, ArrayList<Column> columns, String fileName, int colSkip) {
        ArrayList<Entry> entryList = new ArrayList<>();
        BufferedReader br;
        String line;
        String[] data;
        Entry entry;
        Column column;

        try {
            br = new BufferedReader(new FileReader(new File(fileName)));

            //get header
            line = br.readLine();
            data = line.split(",");
            for (int i = colSkip; i < data.length; i++) {
                header.add(data[i]);
            }

            while ((line = br.readLine()) != null) {
                data = line.split(",", -1);
                entry = new Entry();
                entry.setLabel(data[0]);
                for (int i = colSkip; i < data.length; i++) {
                    entry.addFeature(data[i]);
                }

                entryList.add(entry);
            }

            //store also columns
            for (int i = 0; i < entryList.get(0).getFeatures().size(); i++) {
                column = new Column();
                column.setName(header.get(i));
                for (Entry e : entryList) {
                    column.addResponse(e.getFeatures().get(i));
                }
                columns.add(column);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entryList;
    }

    public void exportQuestions(ArrayList<Feature> questions, String name) {
        //System.out.println("\n***********************\nEXPORTING QUESTION GROUPINGS. . ."
          //      + "\n****************\n");
        String exportString = "";
        PrintWriter pw;

        for (Feature q : questions) {
            exportString += "v," + q.getCode() + "," + q.getDescription() + "\n";
            for (Response r : q.getResponseList()) {
                exportString += r.getKey() + "," + r.getDescription() + "\n";
            }
        }

        try {
            pw = new PrintWriter(new File(name));
            pw.write(exportString);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportEntries(ArrayList<Entry> entries, ArrayList<String> header, String name) {
     //   System.out.println("\n***********************\nEXPORTING PROCESSED DATASET. . ."
       //         + "\n****************\n");
        String exportString = "";
        PrintWriter pw;
        Entry entry;

       // System.out.println("Adding Header...");

        exportString += "Respondent,";
        for (String s : header) {
            exportString += s + ",";
        }
        exportString += "\n";

        //System.out.println("Adding Features...");
        for (int i = 0; i < entries.size(); i++) {
            entry = entries.get(i);
            worker.publishExport(i);
            exportString += entry.getLabel() + ",";

            for (String resp : entry.getFeatures()) {
                exportString += resp + ",";
            }

            exportString += "\n";
        }

        try {
            pw = new PrintWriter(new File(name));
            pw.write(exportString);

            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}