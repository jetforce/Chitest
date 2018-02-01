package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import res.AppString;
import util.FileGetter;
import view.MainFrame;

public class ChiTestController {
	
	private static final ChiTestController CHI_TEST_CONTROLLER = new ChiTestController();
	
	private final MainFrame mainFrame = MainFrame.getInstance();
	
	private LinkedHashMap<String, String> clusterFilePaths;
	private ArrayList<String> filesStrings;
	
	
	
	
	private ChiTestController() {
		
		clusterFilePaths = new LinkedHashMap<String, String>();
		this.filesStrings = new ArrayList<>();
		initListeners();
		
	}
	
	public static final ChiTestController getInstance() {
		return ChiTestController.CHI_TEST_CONTROLLER;
	}
	
	private void clearFiles()
	{
		clusterFilePaths.clear();
		filesStrings.clear();
		mainFrame.getTextFieldFile1().setText("");
		mainFrame.getTextFieldFile2().setText("");
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
						mainFrame.getButtonChiStart().setEnabled(true);
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
						mainFrame.getButtonChiStart().setEnabled(true);
				}
				
				
			}
		});
		
		mainFrame.getButtonChiStart().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//Get selected test type
				int testType = mainFrame.getCmbTestType().getSelectedIndex(); 
				
				switch(testType)
				{
				case 0: //Chi test
					System.out.println("\nChi Test Start");
					PythonExecutor pe = new PythonExecutor(filesStrings, "readChi.py");
					pe.Execute();
					break;
				case 1: //Z-test of Independence of Pooled Proportions
					System.out.println("\n Z-Test of Independence of Pooled Proportions Start");
					break;
				case 2: //Standard Error of Population
					System.out.println("\n Standard Error of Population Start");
					break;
				}
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
					System.out.println("\nTest Type: Chi Test\n");
					break;
				case 1: //Z-test of Independence of Pooled Proportions
					renameFileChoosers("Choose Subgroup 1...", "Choose Subgroup 2...");
					clearFiles();
					System.out.println("\nTest Type: Z-Test of Independence of Pooled Proportions\n");
					break;
				case 2: //Standard Error of Population
					renameFileChoosers("Choose Population...", "Choose Subgroup...");
					clearFiles();
					System.out.println("\n Test Type: Standard Error of Population\n");
					break;
				}
			}
		});
		
	}
	
}
