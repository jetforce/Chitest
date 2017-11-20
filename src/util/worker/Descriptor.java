package util.worker;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.SwingWorker;

import model.Centroid;
import model.Feature;
import preprocessor.DescriptorIO;
import preprocessor.QuestionMerger;
import util.SwingUpdater;
import view.MainFrame;

public class Descriptor extends SwingWorker<Void, Void>{
	private String varFilePath, valFilePath;
	private ArrayList<Feature> varQuestions;
    private ArrayList<Feature> valQuestions;
    private	MainFrame mainFrame;
    private DescriptorIO ip;
    
	public Descriptor(String varFilePath, String valFilePath, MainFrame mainFrame){
		this.valFilePath = valFilePath;
		this.varFilePath = varFilePath;
		this.mainFrame = mainFrame;
		
		this.varQuestions = new ArrayList<>();
        this.valQuestions = new ArrayList<>();
        this.ip = new DescriptorIO();
	}
	
	@Override
	protected Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
	    SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaDescriptorStatus(), "PROCESS: Reading features from files. . .\n");
		ip.readQuestions(varQuestions, valQuestions, varFilePath, valFilePath);
        
        QuestionMerger qm = new QuestionMerger();
	    SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaDescriptorStatus(), "PROCESS: Merging descriptions of variables and values. . .\n");
        qm.mergeQuestions(varQuestions, valQuestions);	
		return null;
	}
	
	protected void done(){
		String filePath = "InitialVarDesc.csv";
		SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaDescriptorStatus(), "PROCESS: Exporting variable descriptions. . .\n");
        ip.exportVariables(varQuestions, filePath);
	    
	    SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaDescriptorStatus(), "DONE! Variable Description saved as " + filePath + "\n");
	}
}