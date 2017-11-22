package controller;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PythonExecutor {

	ArrayList<String> filenames;
	
	
	public PythonExecutor(ArrayList<String> files){
		this.filenames = files;
	}
	
	
	
	public void Execute(){
		
		String cmd = "python readChi.py save.csv";
		
		for(String s: this.filenames){
			cmd = cmd + " "+  "\"" + s +"\"";	
		}
		
		System.out.print("The command is "+cmd);
		
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
