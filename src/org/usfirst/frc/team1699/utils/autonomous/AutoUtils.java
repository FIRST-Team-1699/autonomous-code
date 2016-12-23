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

import org.usfirst.frc.team1699.utils.inireader.ConfigLine;
import org.usfirst.frc.team1699.utils.inireader.ConfigSection;

public class AutoUtils {
	
	/**
	 * Converts String to integer
	 * 
	 * @param s
	 * @return
	 */
	public static int parseInt(String s){ //This is used to turn a string into an int
		try{
			return Integer.parseInt(s);
		}catch(NumberFormatException e){
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
	public static double parseDouble(String s){ //This is used to convert a string into a double
		try{
			return Double.parseDouble(s);
		}catch(NumberFormatException e){
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
	public static String[] loadFileAsArray(String path, int numLines){ //This is no longer used but it took each line of a file and added it to an array
		String[] fileAsString = new String[numLines];
		try (BufferedReader br = new BufferedReader(new FileReader(path));) {
			for(int i = 0; i < numLines; i++){
				fileAsString[i] = br.readLine();
			}
		} catch (FileNotFoundException e) {
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
	public static ArrayList<String> loadFileAsArray(String path){ //Takes each line of a file and adds it to an ArrayList then returns that ArrayList
		ArrayList<String> fileAsString = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(path));) {
			String read = br.readLine();
			while(read != null){
				fileAsString.add(read);
				read = br.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileAsString;
	}
	
	/**
	 * Reads a file and returns it as an array of integers
	 * 
	 * @param section
	 * @return
	 */
	public static ArrayList<String> loadFileAsArray(ConfigSection section) { //Takes a ConfigFile and return it as an array of ints
		// Make the output list
		ArrayList<String> out = new ArrayList<>();
		
		// Run through all the values in the list until null is hit
		int i = 0;
		ConfigLine<?> cl;
		while ((cl = section.getLine(i)) != null) {
			// Check that the ConfigLine is a String
			if (cl.getClass().equals(String.class)) {
				out.add((String) cl.getValue(String.class));
			}
			// Iterate
			i += 1;
		}
		
		// Return the output
		return out;
	}

	public static boolean parseBoolean(String str) {
		return str.equals("true") || str.equals("True");
	}
}
