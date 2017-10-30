package main;

import javax.swing.SwingUtilities;

import controller.ChiTestController;
import controller.KMeansController;
import controller.SOMController;
import controller.UploaderController;
import view.MainFrame;

public class MainGUI {
	
	public static final void main(String args[]) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				MainFrame.getInstance().setVisible(true);
				SOMController.getInstance();
				KMeansController.getInstance();
				UploaderController.getInstance();
				ChiTestController.getInstance();
				
			}
		});
		
	}
	
}
