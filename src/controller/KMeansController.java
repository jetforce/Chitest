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
import util.worker.KMeans;
import view.MainFrame;

public class KMeansController {
	
	private static final KMeansController K_MEANS_CONTROLLER = new KMeansController();
	
	private final MainFrame mainFrame = MainFrame.getInstance();
	
	private String filepath;
	
	private KMeansController() {
		
		filepath = "";
		
		initListeners();
		
	}
	
	public static final KMeansController getInstance() {
		return KMeansController.K_MEANS_CONTROLLER;
	}
	
	private void initListeners() {
		
		mainFrame.getButtonKMeansChooseFile().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				File file = FileGetter.getInstance().getFile(AppString.CSV_TYPE_NAME, AppString.CSV_TYPES);
				if(file != null) {
					filepath = FileGetter.getInstance().getCanonicalPath(file);
					mainFrame.getTextFieldKMeansFile().setText(filepath);
					mainFrame.getButtonKMeansStart().setEnabled(true);
				}
				
			}
		});
		
		mainFrame.getButtonKMeansStart().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				int k = Integer.parseInt(mainFrame.getSpinnerKMeansK().getValue().toString());
				int iterations = Integer.parseInt(mainFrame.getSpinnerKMeansIterations().getValue().toString());
				
				ArrayList<String> contents = CSVIO.read(filepath);
				if(!contents.isEmpty()) {
					
					if(mainFrame.getCheckBoxKMeansHeader().isSelected())
						contents.remove(0);
					
					ArrayList<Instance> instances = new ArrayList<Instance>();
					
					// add instance
					for(String content : contents) {
						instances.add(InstanceFactory.createInstance(new GenericInstanceFactory(content.split(","))));
					}
					
					String path = filepath.substring(0, filepath.lastIndexOf('\\')) + "\\";
					KMeans kMeans = new KMeans(k, iterations, instances, path, mainFrame);
					kMeans.execute();
					
					/*
					LinkedHashMap<String, Centroid> centroids = grouper.group(instances);
					
					long timestamp = System.currentTimeMillis();
					
					ArrayList<String> output = new ArrayList<String>();
					ArrayList<String> allOutput = new ArrayList<String>();
					String path = filepath.substring(0, filepath.lastIndexOf('\\')) + "\\";
					for(Centroid centroid : centroids.values()) {
						output.clear();
						for(Instance instance : centroid.getMembers()) {
							output.add(instance.toString());
							allOutput.add(centroid.getTag() + "," + instance.toString());
						}
						String outputSaveFilePath = path + centroid.getTag() + "-" + timestamp + ".csv";
						CSVIO.write(output, outputSaveFilePath);
						
						SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaKMeansStatus(), centroid.getTag() + " saved in " + outputSaveFilePath);
						
					}
					String allOutputSaveFilePath = path + "KMeans-Results-Summary-" + timestamp + ".csv";
					CSVIO.write(allOutput, allOutputSaveFilePath);
					
					SwingUpdater.appendJTextAreaText(mainFrame.getTextAreaKMeansStatus(), "Consolidated results saved in " + path + "KMeans-Results-Summary-" + timestamp + ".csv");
					*/
				}
				
			}
		});
		
	}
	
}
