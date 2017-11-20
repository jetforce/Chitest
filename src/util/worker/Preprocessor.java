package util.worker;

import java.util.ArrayList;

import javax.swing.SwingWorker;

import model.Column;
import model.Feature;
import model.Entry;
import preprocessor.Converter;
import preprocessor.PreprocessorIO;
import preprocessor.QuestionAdder;
import util.SwingUpdater;
import view.MainFrame;

public class Preprocessor extends SwingWorker<Void, Void>{
	private String varDesFilePath, rawFilePath;
	private ArrayList<Entry> newEntries;
	private PreprocessorIO cp;
	private ArrayList<String> header;
	private ArrayList<Feature> newQuestions;
	private MainFrame mainFrame;
	
	public Preprocessor(String varDesFilePath, String rawFilePath, MainFrame mainFrame){
		this.varDesFilePath = varDesFilePath;
		this.rawFilePath = rawFilePath;
		this.mainFrame = mainFrame;
	}
	
	@Override
	protected Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
        PreprocessorIO cp = new PreprocessorIO();
        Converter conv = new Converter();
        ArrayList<Feature> questionList;
        
        header = new ArrayList<>();
        ArrayList<Column> columns = new ArrayList<>();
		
        SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaPreprocessorStatus(), "Reading input files. . .\n");
        questionList = cp.readQuestions(varDesFilePath);
        ArrayList<Entry> oldEntries = cp.readCSV(header, columns, rawFilePath, 1);
        
        SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaPreprocessorStatus(), "Converting values in dataset. . .\n");
        //convert entries
        newEntries = conv.convertEntries(oldEntries, columns, questionList);
		newQuestions = conv.convertQuestions(columns, questionList);
        
		SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaPreprocessorStatus(), "Updating Variable Description. . .\n");
        //question adder
        QuestionAdder qa = new QuestionAdder();
        qa.addQuestions(questionList, columns);
        
		return null;
	}
	
	protected void done(){
        //export csv's
		String exportEntry = "Preprocessed Dataset.csv";
		String exportVar = "Grouped Variables.csv";
		
		SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaPreprocessorStatus(), "Exporting Files. . .\n");
        cp.exportEntries(newEntries, header, exportEntry, mainFrame);
        SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaPreprocessorStatus(), "Updated Dataset saved in " + exportEntry + ".\n");
        
        cp.exportQuestions(newQuestions, exportVar, mainFrame);
        SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaPreprocessorStatus(), "Updated Variable Description saved in " + exportVar + ".\n");
    }
}
