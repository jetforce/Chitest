package controller;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PythonExecutor {

	ArrayList<String> filenames;
	String testType; //Type of test to be conducted (Chi Test, Standard Error of Proportion etc.)
	
	
	public PythonExecutor(ArrayList<String> files, String testType){
		this.filenames = files;
		this.testType = testType; 
	}
	
	
	
	public void Execute(){
		
		String cmd = "python " + testType +" save.csv";
		//String cmd = "python readChi.py save.csv \"C:\\Users\\Rgee\\git\\Chitest\\dataset\\e.csv\" \"C:\\Users\\Rgee\\git\\Chitest\\dataset\\f.csv\"";
		
		
		for(String s: this.filenames){
			cmd = cmd + " "+  "\"" + s +"\"";	
		}
		
		
		
		
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
		
		
		
		
	}
	
	
	
	
	
}
