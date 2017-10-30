package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import factory.ChildRespondentFactory;
import factory.InstanceFactory;
import factory.NodeFactory;
import model.instance.Instance;
import model.instance.Node;
import res.AppString;
import util.CSVIO;
import util.FileGetter;
import util.worker.Uploader;
import view.MainFrame;

public class UploaderController {
	
	private static final UploaderController UPLOADER_CONTROLLER = new UploaderController();
	
	private final MainFrame mainFrame = MainFrame.getInstance();
	
	private LinkedHashMap<String, String> clusterFilePaths;
	private String instanceFilePath;
	private String saveFilePath;
	
	private UploaderController() {
		
		clusterFilePaths = new LinkedHashMap<String, String>();
		instanceFilePath = "";
		saveFilePath = "";
		
		initListeners();
		
	}
	
	public static final UploaderController getInstance() {
		return UploaderController.UPLOADER_CONTROLLER;
	}
	
	private void initListeners() {
		
		mainFrame.getButtonUploaderChooseFileWeights().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				File[] files = FileGetter.getInstance().getFiles(AppString.CSV_TYPE_NAME, AppString.CSV_TYPES);
				clusterFilePaths.clear();
				if(files != null && files.length != 0) {
					
					for(File file : files) {
						
						String filePath = FileGetter.getInstance().getCanonicalPath(file);
						String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.lastIndexOf("."));
						
						clusterFilePaths.put(fileName, filePath);
						
					}
					mainFrame.getTextFieldUploaderFileWeights().setText(clusterFilePaths.values().stream().map(path -> path).collect(Collectors.joining(",")));
					if(!clusterFilePaths.isEmpty() &&
							instanceFilePath != "" &&
							saveFilePath != "") {
						mainFrame.getButtonUploaderStart().setEnabled(true);
					}
					
				}
				
			}
		});
		
		mainFrame.getButtonUploaderChooseFileInstances().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				File file = FileGetter.getInstance().getFile(AppString.CSV_TYPE_NAME, AppString.CSV_TYPES);
				if(file != null) {
					
					instanceFilePath = FileGetter.getInstance().getCanonicalPath(file);
					mainFrame.getTextFieldUploaderFileInstances().setText(instanceFilePath);
					
					if(!clusterFilePaths.isEmpty() &&
							instanceFilePath != "" &&
							saveFilePath != "") {
						mainFrame.getButtonUploaderStart().setEnabled(true);
					}
					
				}
				
			}
		});
		
		mainFrame.getButtonUploaderChoosePathSave().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				File dir = FileGetter.getInstance().getDir();
				if(dir != null) {
					
					saveFilePath = FileGetter.getInstance().getCanonicalPath(dir);
					mainFrame.getTextFieldUploaderPathSave().setText(saveFilePath);
					
					if(!clusterFilePaths.isEmpty() &&
							instanceFilePath != "" &&
							saveFilePath != "") {
						mainFrame.getButtonUploaderStart().setEnabled(true);
					}
					
				}
				
			}
		});
		
		mainFrame.getButtonUploaderStart().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<String> instanceData = CSVIO.read(instanceFilePath);
				
				if(!instanceData.isEmpty()) {
					
					ArrayList<Instance> instances = new ArrayList<Instance>();
					String[] questions = instanceData.remove(0).split(",");
					for(String data : instanceData) {
						instances.add(InstanceFactory.createInstance(new ChildRespondentFactory(data, questions)));
					}
					
					ArrayList<Node> nodes = new ArrayList<Node>();
					HashMap<String, ArrayList<Instance>> hashMap = new HashMap<String, ArrayList<Instance>>();
					for(String cluster : clusterFilePaths.keySet()) {
						
						String clusterFilePath = clusterFilePaths.get(cluster);
						ArrayList<String> nodeData = CSVIO.read(clusterFilePath);
						hashMap.put(cluster, new ArrayList<Instance>());
						if(!nodeData.isEmpty()) {
							
							for(String data : nodeData) {
								Node node = (Node) InstanceFactory.createInstance(new NodeFactory(data.split(",")));
								node.setTag(cluster);
								nodes.add(node);
							}
							
						}
						
					}
					
					if(!nodes.isEmpty() && !instances.isEmpty()) {
						Uploader uploader = new Uploader(hashMap, nodes, instances, saveFilePath, mainFrame);
						uploader.execute();
					}
					
				}
				
			}
		});
		
	}
	
}
