package main;

import javax.swing.SwingUtilities;

import controller.ChiTestController;
import controller.DescriptorController;
import controller.KMeansController;
import controller.PreprocessorController;
import controller.SOMController;
import controller.UploaderController;
import view.MainFrame;

public class MainGUI {
	
	public static final void main(String args[]) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				MainFrame.getInstance().setVisible(true);
				PreprocessorController.getInstance();
				DescriptorController.getInstance();
				SOMController.getInstance();
				KMeansController.getInstance();
				UploaderController.getInstance();
				ChiTestController.getInstance();
				
			}
		});
		
	}
	
}
