package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import res.AppString;
import util.FileGetter;
import util.worker.Preprocessor;
import view.MainFrame;

public class PreprocessorController {
	
	private static final PreprocessorController PREPROCESSOR_CONTROLLER = new PreprocessorController();
	
	private final MainFrame mainFrame = MainFrame.getInstance();
	
	private String varDesFilePath;
	private String rawFilePath;
	
	private PreprocessorController() {
		
		varDesFilePath = "";
		rawFilePath = "";
		
		initListeners();
		
	}
	
	public static final PreprocessorController getInstance() {
		return PreprocessorController.PREPROCESSOR_CONTROLLER;
	}
	
	private void initListeners() {
		
		mainFrame.getButtonPreprocessorVarDesFileChooseFile().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				File file = FileGetter.getInstance().getFile(AppString.CSV_TYPE_NAME, AppString.CSV_TYPES);
				if(file != null) {
					varDesFilePath = FileGetter.getInstance().getCanonicalPath(file);
					mainFrame.getTextFieldPreprocessorVarDesFile().setText(varDesFilePath);
				}
				
				if(!varDesFilePath.isEmpty() && !rawFilePath.isEmpty())
					mainFrame.getButtonPreprocessorStart().setEnabled(true);
			}
		});
		
		mainFrame.getButtonPreprocessorRawFileChooseFile().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				File file = FileGetter.getInstance().getFile(AppString.CSV_TYPE_NAME, AppString.CSV_TYPES);
				if(file != null) {
					rawFilePath = FileGetter.getInstance().getCanonicalPath(file);
					
					
					if(!varDesFilePath.isEmpty() && !rawFilePath.isEmpty())
						mainFrame.getButtonPreprocessorStart().setEnabled(true);
						mainFrame.getTextFieldPreprocessorRawFile().setText(rawFilePath);
				}
				
			}
		});	
		
		mainFrame.getButtonPreprocessorStart().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Preprocessor preprocessor = new Preprocessor(varDesFilePath, rawFilePath, mainFrame);
				preprocessor.execute();
			}
		});
		
	}
	
}
