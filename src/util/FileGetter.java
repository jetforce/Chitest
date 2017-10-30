package util;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;


public class FileGetter {
	
	private final static FileGetter INSTANCE = new FileGetter();
	
	/**
	 * CONSTRUCTOR
	 */
	private FileGetter() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	
	public static final FileGetter getInstance() {
		return FileGetter.INSTANCE;
	}
	
	/**
	 * returns a File object selected from a JFileChooser
	 * 
	 * @param typeName
	 *            name of the type
	 * @param types
	 *            file extensions of the type
	 * @return File object selected
	 */
	public File getFile(String typeName, String[] types) {

		File file = null;

		JFileChooser fc = new JFileChooser();
		fc.addChoosableFileFilter(new FileNameExtensionFilter(typeName, types));
		fc.setAcceptAllFileFilterUsed(false);
		fc.setMultiSelectionEnabled(true);

		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION)
			file = fc.getSelectedFile();

		return file;

	}

	/**
	 * returns an array of File objects selected from a JFileChooser
	 * 
	 * @param typeName
	 *            name of the type
	 * @param types
	 *            file extensions of the type
	 * @return File objects selected
	 */
	public File[] getFiles(String typeName, String[] types) {

		File[] files = null;

		JFileChooser fc = new JFileChooser();
		fc.addChoosableFileFilter(new FileNameExtensionFilter(typeName, types));
		fc.setAcceptAllFileFilterUsed(false);
		fc.setMultiSelectionEnabled(true);

		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION)
			files = fc.getSelectedFiles();

		return files;

	}

	/**
	 * returns a directory selected from a JFileChooser
	 * 
	 * @return selected directory
	 */
	public File getDir() {

		File file = null;

		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION)
			file = fc.getSelectedFile();

		return file;

	}
	
	/**
	 * returns a File object to save on selected using a JFileChooser
	 * 
	 * @param typeName
	 *            name of the type
	 * @param types
	 *            file extension of the type
	 * @return File object selected
	 */
	public File getSaveFile(String typeName, String[] types) {

		File file = null;

		JFileChooser fc = new JFileChooser();
		fc.addChoosableFileFilter(new FileNameExtensionFilter(typeName, types));
		fc.setAcceptAllFileFilterUsed(false);
		fc.setMultiSelectionEnabled(true);

		int returnVal = fc.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION)
			file = fc.getSelectedFile();

		return file;

	}

	/**
	 * returns the string of the canonical path of a File object
	 * 
	 * @param file
	 *            File object
	 * @return canonical path of the File object
	 */
	public String getCanonicalPath(File file) {

		String canonicalPath = new String();

		try {
			canonicalPath = file.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return canonicalPath;

	}

}
