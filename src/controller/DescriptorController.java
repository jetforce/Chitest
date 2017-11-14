package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import res.AppString;
import util.FileGetter;
import view.MainFrame;

public class DescriptorController {
	
	private static final DescriptorController DESCRIPTOR_CONTROLLER = new DescriptorController();
	
	private final MainFrame mainFrame = MainFrame.getInstance();
	
	private String varFilePath;
	private String valFilePath;
	
	private DescriptorController() {
		
		varFilePath = "";
		valFilePath = "";
		
		initListeners();
		
	}
	
	public static final DescriptorController getInstance() {
		return DescriptorController.DESCRIPTOR_CONTROLLER;
	}
	
	private void initListeners() {
		
		mainFrame.getButtonDescriptorVarFileChooseFile().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				File file = FileGetter.getInstance().getFile(AppString.CSV_TYPE_NAME, AppString.CSV_TYPES);
				if(file != null) {
					varFilePath = FileGetter.getInstance().getCanonicalPath(file);
					mainFrame.getTextFieldDescriptorVarFile().setText(varFilePath);
				}
				
				if(!varFilePath.isEmpty() && !valFilePath.isEmpty())
					mainFrame.getButtonDescriptorStart().setEnabled(true);
			}
		});
		
		mainFrame.getButtonDescriptorValFileChooseFile().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				File file = FileGetter.getInstance().getFile(AppString.CSV_TYPE_NAME, AppString.CSV_TYPES);
				if(file != null) {
					valFilePath = FileGetter.getInstance().getCanonicalPath(file);
					mainFrame.getTextFieldDescriptorValFile().setText(valFilePath);
				}
				
				if(!varFilePath.isEmpty() && !valFilePath.isEmpty())
					mainFrame.getButtonDescriptorStart().setEnabled(true);
				
			}
		});
		
		mainFrame.getButtonDescriptorStart().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
}
