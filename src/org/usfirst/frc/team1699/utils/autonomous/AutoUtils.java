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

import org.usfirst.frc.team1699.utils.inireader.ConfigFile;
import org.usfirst.frc.team1699.utils.inireader.ConfigSection;

public class AutoUtils {
	
	/**
	 * Converts String to integer
	 * 
	 * @param s
	 * @return
	 */
	public static int parseInt(String s){
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
	public static double parseDouble(String s){
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
	public static String[] loadFileAsArray(String path, int numLines){
		String[] fileAsString = new String[numLines];
		try ( // try-with-resources; automatically .close() at the end
			BufferedReader br = new BufferedReader(new FileReader(path));
			) {
			String line;
			while((line = br.readLine()) != null){
				String[] l = line.split(" ");
				for(int j = 0; j < l.length; j++){
					fileAsString[j] = l[j];
				}
				
			}
			// br.close() here would not be called if an exception was thrown
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
	 * @param file
	 * @param mode
	 * @return
	 */
	@SuppressWarnings("null")
	@Deprecated
	public static int[] loadFileAsArray(ConfigFile file, int mode) {
		
		ArrayList<ConfigSection> sections = new ArrayList<>();
		
		// find a ConfigSection that has the text "autonomous" and mode in the name
		int count1 = 0;
		ConfigSection cs = null;
		ConfigSection selected = null;
		String name;
		while (true) {
			cs = file.getSection(count1);
			
			if (cs == null) {
				break;
			}
			
			name = cs.getName().toLowerCase();
			
			if ((name.contains("autonomous")) && (name.contains(((Integer) mode).toString()))) {
				selected = cs;
			}
			count1 += 1;
		}
		
		count1 = (Integer) null; //safety because i'm tired
		
		// not found state
		if (selected == null) {
			return new int[0]; 
		}
		
		// generate commands
		int count2 = 0;
		int[] output = new int[selected.size()];
		while (count2 > selected.size()) {
			output[count2] = AutoUtils.parseInt((String) selected.getLine(count2).getValue());
			count2 += 1;
		}
		
		return output;
	}
}
