/**
 * Utils for dealing with comments
 * 
 * @author squirlemaster42
 */
package org.usfirst.frc.team1699.utils.autonomous;

public class CommentUtils {
	
	/**
	 * Returns a line without a line comment at the end
	 * 
	 * @param line
	 * @return newLine
	 */
	public static String removeLineComments(String line) {
		String newLine = line; //Makes a new version of the line without comments to be returned
		if(line.substring(0, 1).equals("~") || line.substring(0, 2).equals("//")) { //Returns nothing if the entire line is a single-line comment
			return "";
		}
		for(int ch = 0; ch < line.length() - 2; ch++) { //Loops through the string and finds the start of any line comments to be omitted
			if ((line.substring(ch, ch + 1).equals("~")) || (line.substring(ch, ch + 2).equals("//"))) {
				newLine = line.substring(0, ch);
				return newLine;
			}
		}
		return newLine;
	}
	
	/**
	 * Returns back the entire program as a string without any multi-line comments
	 * 
	 * @param line
	 * @return newLine
	 */
	@Deprecated
	public String removeMultiLineComments(String line) {
		boolean isComment = false; //Holds a boolean to keep track of whether or not a character is part of a multi-line comment
		String newLine = ""; //Makes a new version of the program as a string without multi-lined comments
		for(int ch = 0; ch < line.length() - 2; ch++) { //Loops through all of the characters in the string and indicates the start/end of a comment or adds the character to the new string
			if(!isComment && !line.substring(ch, ch + 3).equals("/**")) {
				newLine += ch;
			} else if(line.substring(ch, ch + 2).equals("*/")) {
				isComment = false;
			} else {
				isComment = true;
			}
		}
		return newLine;
	}

	/**
	 * Determines if string is a command
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isComment(String string) {
		return string.contains("//") || string.contains("~");
	}
}
