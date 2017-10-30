package util;

import java.awt.Cursor;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;

import view.MainFrame;

/**
 * Used for updating JComponents
 * 
 * @author amcan
 */
public class SwingUpdater {
	
	/**
	 * Changes the cursor of a JComponent object
	 * 
	 * @param component
	 *            JComponent object to be manipulated
	 * @param cursor
	 *            Cursor object representing the new cursor
	 */
	public static void updateComponentCursor(JComponent component, Cursor cursor) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				component.setCursor(cursor);

			}
		});

	}

	/**
	 * Changes the enable state of a JComponent object
	 * 
	 * @param component
	 *            JComponent object to be manipulated
	 * @param isEnabled
	 *            boolean indicating the enable state
	 */
	public static void updateComponentEnable(JComponent component, boolean isEnabled) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				component.setEnabled(isEnabled);

			}
		});

	}

	/**
	 * Changes the visible state of a JComponent object
	 * 
	 * @param component
	 *            JComponent object to be manipulated
	 * @param isVisible
	 *            boolean indicating the visible state
	 */
	public static void updateComponentVisibility(JComponent component, boolean isVisible) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				component.setVisible(isVisible);

			}
		});

	}

	/**
	 * Changes the text of a JTextComponent object
	 * 
	 * @param textComponent
	 *            JTextComponent object to be manipulated
	 * @param text
	 *            String object representing the new text
	 */
	public static void updateTextComponentText(JTextComponent textComponent, String text) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				textComponent.setText(text);

			}
		});

	}
	
	public static void appendJTextAreaText(JTextArea textArea, String text) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				textArea.append(text + "\n");

			}
		});

	}
	
	/**
	 * Displays an error message in a JOptionPane
	 * 
	 * @param title
	 *            String object representing the title of the JOptionPane
	 * @param error
	 *            String object representing the error message to be shown
	 */
	public static void displayError(String title, String error) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				JOptionPane.showMessageDialog(MainFrame.getInstance(), error, title, JOptionPane.ERROR_MESSAGE);

			}
		});

	}

}
