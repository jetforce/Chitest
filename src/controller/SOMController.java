package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import factory.GenericInstanceFactory;
import factory.InstanceFactory;
import model.instance.Instance;
import res.AppString;
import util.CSVIO;
import util.FileGetter;
import util.worker.SOM;
import view.MainFrame;

public class SOMController {
	
	private static final SOMController SOM_CONTROLLER = new SOMController();
	
	private final MainFrame mainFrame = MainFrame.getInstance();
	
	private String filepath;
	
	private SOMController() {
		
		filepath = "";
		
		initListeners();
		
	}
	
	public static final SOMController getInstance() {
		return SOMController.SOM_CONTROLLER;
	}
	
	private void initListeners() {
		
		mainFrame.getButtonSOMChooseFile().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				File file = FileGetter.getInstance().getFile(AppString.CSV_TYPE_NAME, AppString.CSV_TYPES);
				if(file != null) {
					filepath = FileGetter.getInstance().getCanonicalPath(file);
					mainFrame.getTextFieldSOMFile().setText(filepath);
					mainFrame.getButtonSOMStart().setEnabled(true);
				}
				
			}
		});
		
		mainFrame.getButtonSOMStart().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int rows = Integer.parseInt(mainFrame.getSpinnerSOMRows().getValue().toString());
				int cols = Integer.parseInt(mainFrame.getSpinnerSOMCols().getValue().toString());
				double finalRate = Double.parseDouble(mainFrame.getSpinnerSOMFinalRate().getValue().toString());
				
				ArrayList<String> contents = CSVIO.read(filepath);
				if(mainFrame.getCheckboxSOMHeader().isSelected())
					contents.remove(0);
				ArrayList<Instance> instances = new ArrayList<Instance>();
				
				for(String content : contents) {
					instances.add(InstanceFactory.createInstance(new GenericInstanceFactory(content.split(","))));
				}
				
				String path = filepath.substring(0, filepath.lastIndexOf("\\")) + "\\";
				String weightSaveFilePath = path + "Weights-" + System.currentTimeMillis() + ".csv";
				
				SOM som = new SOM(rows, cols, finalRate, instances, weightSaveFilePath, mainFrame);
				som.execute();
				
			}
		});
		
	}
	
}
