/**
 * Class that generates a path for auto
 * 
 * @author squirlemaster42
 * @author TheCookingCookie
 */
package org.usfirst.frc.team1699.utils.autonomous;

import java.util.ArrayList;

import org.usfirst.frc.team1699.utils.command.Command;

public class AutoScriptReader {
	
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
	public AutoScriptReader(String path, ArrayList<Command> cmds){
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
				System.out.println(CommentUtils.removeLineComments(fileAsString.get(i)));
				if(IfConditionalUtils.containsIfConditional(fileAsString.get(i))){
					//Does conditional stuff
				}else if(IfConditionalUtils.isCommand(fileAsString.get(i), cmds)){
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
	public void callCommandFromString(String inp){
		String[] cmdLine = inp.split(" "); //Creates a string array and sets it equal to the input string split at spaces
		double speed = ValueGetterUtils.getSpeed(cmdLine); //Makes a double called speed and set it equal to the speed specified in the auto file
		double distance = ValueGetterUtils.getDistance(cmdLine); //Makes a double called distance and set it equal to the distance specified in the auto file
		Command cmd = ValueGetterUtils.getCmd(cmdLine, cmds); //Makes an object called command and sets it equal to the command in the auto file
		boolean useSensor = ValueGetterUtils.getUseSensor(cmdLine); //Makes a boolean called use sensor and set true if the auto file specifies that a sensor should be used
		
		((Command) cmd).runAuto(distance, speed, useSensor); //Cast the cmd object to type Command and runs the runAuto method in it
	}
	
}
