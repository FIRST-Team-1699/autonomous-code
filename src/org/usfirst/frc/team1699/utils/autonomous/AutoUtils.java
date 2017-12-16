/**
 * This class holds all of the utilities used in autonomous
 * 
 * @author squirlemaster42
 */
package org.usfirst.frc.team1699.utils.autonomous;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team1699.utils.inireader.ConfigSection;

public class AutoUtils {
	
	/**
	 * Converts String to integer
	 * 
	 * @param s
	 * @return
	 */
	public static int parseInt(final String s){ //This is used to turn a string into an int
		try{
			return Integer.parseInt(s); //Converts String to int
		}catch(NumberFormatException e){ //Catches if String is not an int
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Converts String to double
	 * 
	 * @param s
	 * @return
	 */
	public static double parseDouble(final String s){ //This is used to convert a string into a double
		try{
			return Double.parseDouble(s); //Converts String to double
		}catch(NumberFormatException e){ //Catches if String is not a double
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Reads a file and adds each line as a String to an array
	 * 
	 * @param path
	 * @param numLines
	 * @return
	 */
	@Deprecated
	public static String[] loadFileAsArray(final String path, final int numLines){ //This is no longer used but it took each line of a file and added it to an array
		String[] fileAsString = new String[numLines]; //New string array
		try (BufferedReader br = new BufferedReader(new FileReader(path));) { //Creates a new buffered reader
			for(int i = 0; i < numLines; i++){ //Loops through each line of the file and adds it to the array
				fileAsString[i] = br.readLine();
			}
		} catch (FileNotFoundException e) { //Catches if the file is not found
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileAsString;
	}
	
	/**
	 * Reads a file and returns it as an ArrayList
	 * 
	 * @param path
	 * @return
	 */
	public static List<String> loadFileAsArray(final String path){ //Takes each line of a file and adds it to an ArrayList then returns that ArrayList
		ArrayList<String> fileAsString = new ArrayList<String>(); //New ArrayList that store type String
		try (BufferedReader br = new BufferedReader(new FileReader(path));) { //Creates a new buffered reader
			String read = br.readLine();
			while(read != null){ //Reads each line of the file
				fileAsString.add(read);
				read = br.readLine();
			}
		} catch (FileNotFoundException e) { //Catches if file is not found
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileAsString;
	}
	
	/**
	 * Gets a List of Strings from a ConfigSection
	 * 
	 * @param section
	 * @return
	 */
	public static List<String> loadFileAsArray(final ConfigSection section) { 
		// Takes a ConfigSection and return it as a List of Strings
		return section.getStringValues();
	}

	/**
	 * Parses String to boolean
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean parseBoolean(final String str) { //Needs work
		return str.equals("true") || str.equals("True");
	}
}
