package controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import results.TestOfIndependenceResults;
import util.SwingUpdater;
import view.MainFrame;

public class PythonExecutor {

	ArrayList<String> filenames;
	String testType; //Type of test to be conducted (Chi Test, Standard Error of Proportion etc.)
	String addtlParams;//Additional parameters if any
	String saveFile;//Filename of the results file that will be saved
	private final MainFrame mainFrame = MainFrame.getInstance();
	
	
	public PythonExecutor(ArrayList<String> files, String testType, String addtlParams){
		this.filenames = files;
		this.testType = testType; 
		this.addtlParams = addtlParams;
		saveFile = testType;
		
		for(String s : files)
		{
			saveFile = saveFile + "_" + getShortenedFileName(s);
			
		}
		saveFile = saveFile + ".csv";
	}
	
	public PythonExecutor(ArrayList<String> files, String testType, String addtlParams, String selectedFeature){
		this.filenames = files;
		this.testType = testType; 
		this.addtlParams = addtlParams;
		saveFile = testType + "_" + selectedFeature;
		
		for(String s : files)
		{
			saveFile = saveFile + "_" + getShortenedFileName(s);
			
		}
		saveFile = saveFile + ".csv";
		
	}
	
	public String getShortenedFileName(String file)
	{
		return(file.substring(file.lastIndexOf("\\") + 1, file.lastIndexOf(".")));
	}

	
	
	public void Execute(){
		
		
		
		
		String cmd = "python " + testType +" "+saveFile;
		//String cmd = "python readChi.py save.csv \"C:\\Users\\Rgee\\git\\Chitest\\dataset\\e.csv\" \"C:\\Users\\Rgee\\git\\Chitest\\dataset\\f.csv\"";
		
		
		for(String s: this.filenames){
			cmd = cmd + " "+  "\"" + s +"\"";	
		}
		
		if(!addtlParams.equalsIgnoreCase(""))
			cmd = cmd + addtlParams;
		
		
		
		
		System.out.print("The command is "+cmd + "\n");
		
		Runtime rt = Runtime.getRuntime();
		try{
			
			Process pr = rt.exec(cmd);
		     BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		     String line = null; 
		   
		        while ((line = input.readLine()) != null)
		            System.out.println(line);
		        
		    	pr.waitFor();
		}catch(Exception e){
			System.out.println(e);
		}
		
		
		readResults(saveFile);
		
		
		
		
		
	}
	
	private void readResults(String fileName)
	{
		BufferedReader br; 
		String line; 
		mainFrame.getTextAreaChiStatus().setText("");
		try {
			br = new BufferedReader(new FileReader(new File(fileName)));
			
			while ((line = br.readLine()) != null) 
			{
				String[] lineArray = line.split(",");
				
				if(testType.equalsIgnoreCase("readIndependence.py"))
				{
					if(lineArray[0].equalsIgnoreCase("v"))
					{
						readIndependenceResult(lineArray);
						break;
					}
				}
				
			
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void readIndependenceResult(String[] la)
	{
		for(int i = 0; i < la.length; i++)
			System.out.println(" " + la[i]);
		TestOfIndependenceResults toi = new TestOfIndependenceResults(Float.parseFloat(la[1]),Float.parseFloat(la[2]),Float.parseFloat(la[5]),Float.parseFloat(la[6]),Float.parseFloat(la[8]),Float.parseFloat(la[10]),Float.parseFloat(la[12]),Float.parseFloat(la[13]),la[3],la[4],Float.parseFloat(la[14]),Float.parseFloat(la[15]));
		
		
		for(int i = 0; i < filenames.size(); i++)
		{
			String file = filenames.get(i);
			String fileName = file.substring(file.lastIndexOf("\\") + 1, file.lastIndexOf("."));
			int num = i+1;
			SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaChiStatus(), "Dataset " + num + ": " + fileName);
			
			
		}
		
		if(toi.getPass99().equalsIgnoreCase("Reject"))
			SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaChiStatus(), "Significant at 99% confidence.");
		else
			SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaChiStatus(), "Not significant at 99% confidence.");
		
		if(toi.getPass95().equalsIgnoreCase("Reject"))
			SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaChiStatus(), "Significant at 95% confidence.");
		else
			SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaChiStatus(), "Not significant at 95% confidence.");
		SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaChiStatus(), "Z-score: "+toi.getZ());
		SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaChiStatus(), "Z^2: "+toi.getZ2());
		SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaChiStatus(), "n1: "+toi.getN1());
		SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaChiStatus(), "n2: "+toi.getN2());
		SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaChiStatus(), "p1: "+toi.getFreq_p1());
		SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaChiStatus(), "p1 (%): "+toi.getP1());
		SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaChiStatus(), "p2: "+toi.getFreq_p2());
		SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaChiStatus(), "p2 (%): "+toi.getP2());
		SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaChiStatus(), "p^: "+toi.getpPrime());
		SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaChiStatus(), "SEp: "+toi.getSEp());
		

	
	
	
	}
	
	
	
	
	
}
