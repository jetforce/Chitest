package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains methods for reading from and writing to a CSV file
 * 
 * @author amcan
 */
public class CSVIO {
	
	/**
	 * Reads a CSV file
	 * 
	 * @param filename
	 *            filename of the CSV file to be read
	 * @return ArrayList of CSV contents
	 */
	public static ArrayList<String> read(String filename) {

		ArrayList<String> input = new ArrayList<String>();

		try {

			Scanner sc = new Scanner(new File(filename));
			sc.useDelimiter(",");

			while (sc.hasNext()) {
				input.add(sc.nextLine());
			}

			sc.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return input;

	}

	/**
	 * Writes a String object to a CSV file
	 * 
	 * @param output
	 *            String object to be written
	 * @param filename
	 *            filename of the CSV file where the output will be written
	 */
	public static void write(String output, String filename) {

		FileWriter fw;

		try {

			File file = new File(filename);

			if (file.exists())
				fw = new FileWriter(file, true);
			else
				fw = new FileWriter(file);

			String[] line = output.split(",");

			for (int i = 0; i < line.length; i++) {

				fw.append(line[i]);
				if (i + 1 != line.length)
					fw.append(",");

			}

			fw.append("\n");

			fw.flush();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Writes multiple String objects to a CSV file
	 * 
	 * @param output
	 *            String objects to be written
	 * @param filename
	 *            filename of the CSV file where the output will be written
	 */
	public static void write(ArrayList<String> output, String filename) {

		FileWriter fw;

		try {

			File file = new File(filename);

			if (file.exists())
				fw = new FileWriter(file, true);
			else
				fw = new FileWriter(file);

			for (String s : output) {

				String[] line = s.split(",");
				for (int i = 0; i < line.length; i++) {

					fw.append(line[i]);
					if (i + 1 != line.length)
						fw.append(",");

				}

				fw.append("\n");

			}
			fw.flush();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
