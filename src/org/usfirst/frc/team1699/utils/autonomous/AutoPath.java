/**
 * Class that generates a path for auto
 * 
 * @author squirlemaster42
 * @author TheCookingCookie
 */
package org.usfirst.frc.team1699.utils.autonomous;

import java.util.ArrayList;

import org.usfirst.frc.team1699.utils.command.Command;

public class AutoPath {
	
	private String path; //Stores the path a the text file containing auto script
	private ArrayList<String> fileAsString; //Array list that hold the autoFile as an array of strings
	private static ArrayList<Command> cmds; //Holds a list of all commands in an array
	
	/**
	 * Constructor
	 * 
	 * @param path
	 * @param numLines
	 * @param cmds
	 */
	@SuppressWarnings("static-access")
	public AutoPath(String path, ArrayList<Command> cmds){
		//Sets instance vars to values input by programmer
		this.path = path;
		this.cmds = cmds;
		fileAsString = AutoUtils.loadFileAsArray(path); //Sets fileAsArray list equal to the file
	}
	
	/**
	 * Returns path to file that contains autonomous scripts
	 * 
	 * @return
	 */
	public String getPath() { //Returns the file path
		return path;
	}
 
	/**
	 * Returns file as string array
	 * 
	 * @return
	 */
	public ArrayList<String> getFileAsString() { //Returns the ArrayList of the file as a string
		return fileAsString;
	}
	
	/**
	 * Runs all lines of file (main)
	 */
	public void runScript(){
		for(int i = 0; i < fileAsString.size(); i++){ //Loops through fileAsString array
			try{
				System.out.println(fileAsString.get(i));
				System.out.println(removeLineComments(fileAsString.get(i)));
				if(containsIfConditional(fileAsString.get(i))){
					//Does conditional stuff
				}else if(isCommand(fileAsString.get(i))){
					callCommandFromString(fileAsString.get(i)); //sends string to method so it can be converted to an object
				}else{
					//Throw exception
				}
			}catch(CommandNotFoundException e){ //Detects if there is a error where the command is not found
				System.out.println("Your autonomous script has failed because a command does not exist.");
				e.printStackTrace();
				break;
			}
		}
	}
	
	/**
	 * Runs each line
	 * Called from runScript()
	 * 
	 * @param inp
	 * @throws CommandNotFoundException
	 */
	public static void callCommandFromString(String inp){
		String[] cmdLine = inp.split(" "); //Creates a string array and sets it equal to the input string split at spaces
		double speed = ValueGetterUtils.getSpeed(cmdLine); //Makes a double called speed and set it equal to the speed specified in the auto file
		double distance = ValueGetterUtils.getDistance(cmdLine); //Makes a double called distance and set it equal to the distance specified in the auto file
		Command cmd = ValueGetterUtils.getCmd(cmdLine, cmds); //Makes an object called command and sets it equal to the command in the auto file
		boolean useSensor = ValueGetterUtils.getUseSensor(cmdLine); //Makes a boolean called use sensor and set true if the auto file specifies that a sensor should be used
		
		((Command) cmd).runAuto(distance, speed, useSensor); //Cast the cmd object to type Command and runs the runAuto method in it
	}
	
	public static boolean isCommand(String string) {
		String[] inp = string.split(" ");
		for(int i = 0; i < inp.length; i++){
			for(int j = 0; j < cmds.size(); j++){
				if(cmds.get(j).getName().equals(inp[i])){
					return true;
				}
			}
		}
		return false;
	}
	
	public static int ifConditional(String[] cmdLine, int startLine){
		String[] conLine = cmdLine[startLine].split(" ");
		String runLine = cmdLine[startLine + 1];
		String conditional = "";
		int conditionalStart = 0;
		int conditionalEnd = 0;
		
		for(int i = 0; i < conLine.length; i++){
			 if(conLine[i].equals("if")){
				 conditionalStart = i + 1;
			 }
		}
		
		for(int i = 0; i < conLine.length; i++){
			 if(conLine[i].equals("then:")){
				 conditionalStart = i - 1;
			 }
		}
		
		for(int i = conditionalStart; i < conditionalEnd; i++){
			conditional += conLine[i];
		}
		
		return startLine + 2;
	}
	
	public static boolean containsIfConditional(String string){
		String[] inp = string.split(" ");
		for(int i = 0; i < inp.length; i++){
			if(inp[i].equals("if")){
				return true;
			}
		}
		return false;
	}
	
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
}
