package controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;

import res.AppString;
import util.FileGetter;
import view.MainFrame;
import model.Feature;
import model.Response;
import model.SelectedFeature;

public class ChiTestController {
	
	private static final ChiTestController CHI_TEST_CONTROLLER = new ChiTestController();
	
	private final MainFrame mainFrame = MainFrame.getInstance();
	
	private LinkedHashMap<String, String> clusterFilePaths;
	private ArrayList<String> filesStrings;
	
	//NEW ATTRIBUTES FOR FEATURE AND ATTRIBUTES
	
	private ArrayList<String> attributes; //Array of attributes from the feature
	
	private String attributesString=""; //All attributes from the feature put into string separated by delimiter
	private String selectedAttributesString=""; //Selected attributes from the feature put into string separated by delimiter
	
	
	private String featureCode; //Input feature code
	private ArrayList<Feature> questionList; //All the features in the file
	
	private SelectedFeature sf;

	protected String initVarDiscPath;
	
	//END OF NEW ATTRIBUTES
	
	
	private ChiTestController() {
		
		clusterFilePaths = new LinkedHashMap<String, String>();
		this.filesStrings = new ArrayList<>();
		initListeners();
		
	}
	
	public static final ChiTestController getInstance() {
		return ChiTestController.CHI_TEST_CONTROLLER;
	}
	
	private void showZTestIndependenceViews(boolean toShow)
	{
		mainFrame.getButtonEnterFeature().setVisible(toShow);
		mainFrame.getButtonStore().setVisible(toShow);
		mainFrame.getTextAreaFeature().setVisible(toShow);
		mainFrame.getListAttributes().setVisible(toShow);
		mainFrame.getLblNewLabel().setVisible(toShow);
		mainFrame.getTextFieldFeature().setVisible(toShow);
		mainFrame.getButtonChiStart().setVisible(!toShow);
	}
	
	private void startTest()
	{
		//Get selected test type
		int testType = mainFrame.getCmbTestType().getSelectedIndex(); 
		PythonExecutor pe;
		
		switch(testType)
		{
		case 0: //Chi test
			System.out.println("\nChi Test Start");
			pe = new PythonExecutor(filesStrings, "readChi.py","");
			pe.Execute();
			break;
		case 1: //Z-test of Independence of Pooled Proportions
			System.out.println("\n Z-Test of Independence of Pooled Proportions Start");
			pe = new PythonExecutor(filesStrings, "readIndependence.py", " "+initVarDiscPath+" "+sf.getFeature()+" "+attributesString+" "+selectedAttributesString,sf.getFeature() );
			pe.Execute();
			break;
		case 2: //Standard Error of Population
			System.out.println("\n Standard Error of Population Start");
			break;
		}
	}

	private void clearFiles()
	{
		clusterFilePaths.clear();
		filesStrings.clear();
		mainFrame.getTextFieldFile1().setText("");
		mainFrame.getTextFieldFile2().setText("");
		mainFrame.getButtonChiStart().setEnabled(false);
		mainFrame.getButtonStore().setEnabled(false);
	}
	
	private void renameFileChoosers(String fileChooser1, String fileChooser2)
	{
		mainFrame.getButtonChooseFile1().setText(fileChooser1);
		mainFrame.getButtonChooseFile2().setText(fileChooser2);
	}
	
	private void initListeners() {
		
		mainFrame.getButtonChooseFile1().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				File dataset1 = FileGetter.getInstance().getFile(AppString.CSV_TYPE_NAME, AppString.CSV_TYPES);
				
				if(dataset1 != null)
				{
					String filePath = FileGetter.getInstance().getCanonicalPath(dataset1);
					String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.lastIndexOf("."));
					
					if(clusterFilePaths.size() >= 2)
						clearFiles();
					
					filesStrings.add(filePath);
					clusterFilePaths.put(fileName, filePath);

					mainFrame.getTextFieldFile1().setText(fileName);
					//mainFrame.getTextFieldFile1().setText(clusterFilePaths.values().stream().map(path -> path).collect(Collectors.joining(",")));
					//mainFrame.getTextFieldUploaderFileWeights().setText(clusterFilePaths.values().stream().map(path -> path).collect(Collectors.joining(",")));
					if(clusterFilePaths.size() >= 2)
					{
						mainFrame.getButtonChiStart().setEnabled(true);
						mainFrame.getButtonStore().setEnabled(true);
					}
						
				}
				
					
				
				
			}
		});
		
		mainFrame.getButtonChooseFile2().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				File dataset2 = FileGetter.getInstance().getFile(AppString.CSV_TYPE_NAME, AppString.CSV_TYPES);
								
				if(dataset2 != null)
				{
					String filePath = FileGetter.getInstance().getCanonicalPath(dataset2);
					String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.lastIndexOf("."));
					
					if(clusterFilePaths.size() >= 2)
						clearFiles();
					
					
					filesStrings.add(filePath);
					clusterFilePaths.put(fileName, filePath);

					mainFrame.getTextFieldFile2().setText(fileName);
					//mainFrame.getTextFieldFile1().setText(clusterFilePaths.values().stream().map(path -> path).collect(Collectors.joining(",")));
					//mainFrame.getTextFieldUploaderFileWeights().setText(clusterFilePaths.values().stream().map(path -> path).collect(Collectors.joining(",")));
					if(clusterFilePaths.size() >= 2)
					{
						mainFrame.getButtonChiStart().setEnabled(true);
						mainFrame.getButtonStore().setEnabled(true);
					}
				}
				
				
			}
		});
		
		mainFrame.getButtonChiStart().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				startTest();
			}
		});
		
		mainFrame.getCmbTestType().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				int testType = mainFrame.getCmbTestType().getSelectedIndex();
				switch(testType)
				{
				case 0: //Chi test
					renameFileChoosers("Choose Dataset 1...", "Choose Dataset 2...");
					clearFiles();
					showZTestIndependenceViews(false);
					System.out.println("\nTest Type: Chi Test\n");
					break;
				case 1: //Z-test of Independence of Pooled Proportions
					renameFileChoosers("Choose Subgroup 1...", "Choose Subgroup 2...");
					clearFiles();
					showZTestIndependenceViews(true);
					System.out.println("\nTest Type: Z-Test of Independence of Pooled Proportions\n");
					break;
				case 2: //Standard Error of Population
					renameFileChoosers("Choose Population...", "Choose Subgroup...");
					clearFiles();
					showZTestIndependenceViews(false);
					System.out.println("\n Test Type: Standard Error of Population\n");
					break;
				}
			}
		});
		
		//Enter Feature Button
		mainFrame.getButtonEnterFeature().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Store the Selected Feature and Attributes to print in textAreaAttributes and textAreaFeature
				attributes = new ArrayList<>();
				featureCode = mainFrame.getTextFieldFeature().getText();

				
				//****************HARD CODED DIRECTORY OF THE INITIAL VARIABLE DESCRIPTION FILE**********************
				initVarDiscPath = "src\\..\\InitialVarDesc.csv";
				
				questionList = preprocessor.PreprocessorIO.staticReadQuestions(initVarDiscPath);
				
				for (Feature q:questionList) {
					//TEST
					//System.out.println(q.getCode() + " : " + q.getDescription());
					//END OF TEST
					if (q.getCode().equalsIgnoreCase(featureCode.trim())) {
						mainFrame.getTextAreaFeature().setText(q.getCode() + " : " + q.getDescription());
						DefaultListModel dlm = new DefaultListModel();
						for(Response r: q.getResponseList()) {
							dlm.addElement(r.getKey() + " - " + r.getDescription());
							attributes.add(r.getKey());
						}
						mainFrame.getListAttributes().setModel(dlm);
						break;
					}
					else if (featureCode.isEmpty())
						mainFrame.getTextAreaFeature().setText("No input");
					else
						mainFrame.getTextAreaFeature().setText("Feature Code does not exist in Initial Variable Description file.");
					
					DefaultListModel empty = new DefaultListModel();
					mainFrame.getListAttributes().setModel(empty);
				}
				
			}
		});
		
		
		//Start Button for independence test of pooled proportions
		mainFrame.getButtonStore().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				attributesString=""; 
				selectedAttributesString="";
				ArrayList<String> storedAttributes = new ArrayList<>();
				int[] selectedAttributes = mainFrame.getListAttributes().getSelectedIndices();
				
				for(int i = 0; i < selectedAttributes.length; i++)
					System.out.println(selectedAttributes);
				
				
				for(int i = 0; i < selectedAttributes.length; i++) {
					storedAttributes.add(attributes.get(selectedAttributes[i]));
				}
				sf = new SelectedFeature(featureCode, storedAttributes);
				
				if(sf.getAttributes().isEmpty() || sf.getFeature() == null)
					System.out.println("ERROR: Feature or selected values are missing.");
				else
				{
					for(int i = 0; i < sf.getAttributes().size(); i++)
					{
						selectedAttributesString += sf.getAttributes().get(i);
						if(i != sf.getAttributes().size()-1)
							selectedAttributesString+=":";
					}
					
					for(int i = 0; i < attributes.size(); i++)
					{
						attributesString += attributes.get(i);
						if(i != attributes.size()-1)
							attributesString+=":";
					}

					
					System.out.println(sf.getFeature());
					System.out.println(sf.getAttributes());
					
					
					
					startTest(); 
				}
				
				
				
			}
			
		});
		
		showZTestIndependenceViews(false);
		
	}
	
}
