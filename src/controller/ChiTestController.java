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
	
	private void initListeners() {
		
		mainFrame.getButtonChiChooseFiles().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				File[] files = FileGetter.getInstance().getFiles(AppString.CSV_TYPE_NAME, AppString.CSV_TYPES);
				clusterFilePaths.clear();
				if(files != null && files.length != 0) {
					
					for(File file : files) {
						
						String filePath = FileGetter.getInstance().getCanonicalPath(file);
						String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.lastIndexOf("."));
						
						filesStrings.add(filePath);
						
						clusterFilePaths.put(fileName, filePath);
						
						
					}
					
					
					mainFrame.getTextFieldChiFiles().setText(clusterFilePaths.values().stream().map(path -> path).collect(Collectors.joining(",")));
					//mainFrame.getTextFieldUploaderFileWeights().setText(clusterFilePaths.values().stream().map(path -> path).collect(Collectors.joining(",")));
					if(!clusterFilePaths.isEmpty())
						mainFrame.getButtonChiStart().setEnabled(true);
					
				}
				
			}
		});
		
		mainFrame.getButtonChiStart().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("hello world");
				PythonExecutor pe = new PythonExecutor(filesStrings);
				pe.Execute();
				// TODO
				
			}
		});
		
	}
	
}
