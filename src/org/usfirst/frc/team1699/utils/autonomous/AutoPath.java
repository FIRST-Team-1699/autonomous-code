/**
 * Class that generates a path for auto
 * 
 * @author squirlemaster42
 */
package org.usfirst.frc.team1699.utils.autonomous;

import java.util.ArrayList;

import org.usfirst.frc.team1699.utils.command.Command;

public class AutoPath {
	
	private String path; //Stores the path a the text file containing auto script
	private ArrayList<String> fileAsString; //Array list that hold the autoFile as an array of strings
	private ArrayList<Command> cmds; //Holds a list of all commands in an array
	
	/**
	 * Constructor
	 * 
	 * @param path
	 * @param numLines
	 * @param cmds
	 */
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
				callCommandFromString(fileAsString.get(i)); //sends string to method so it can be converted to an object
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
		double speed = getSpeed(cmdLine); //Makes a double called speed and set it equal to the speed specified in the auto file
		double distance = getDistance(cmdLine); //Makes a double called distance and set it equal to the distance specified in the auto file
		Command cmd = getCmd(cmdLine); //Makes an object called command and sets it equal to the command in the auto file
		boolean useSensor = getUseSensor(cmdLine); //Makes a boolean called use sensor and set true if the auto file specifies that a sensor should be used
		
		((Command) cmd).runAuto(distance, speed, useSensor); //Cast the cmd object to type Command and runs the runAuto method in it
		
	}
	
	/**
	 * Determines whether to use sensor or not
	 * 
	 * @param cmdLine
	 * @return
	 */
	public boolean getUseSensor(String[] cmdLine){
		try{
			for(int i = 0; i <= cmdLine.length; i++){ //This loops looks for the word until in the cmdLine array and returns true if it is found
				if(cmdLine[i].equals("until")){ 
					return true;
				}
			}
		}catch(ArrayIndexOutOfBoundsException e){
			return false;
		}
		return false;
	}
	
	/**
	 * Looks as a string and converts it to a command
	 * 
	 * @param cmdLine
	 * @return
	 * @throws CommandNotFoundException
	 */
	public Command getCmd(String[] cmdLine) throws CommandNotFoundException{
		if(Command.getCmdNames().contains(cmdLine[0])){ //Looks at cmdLine and determines if the first line is a valid command
			String cmdStr = cmdLine[0];
			
			for(int i = 0; i < cmds.size(); i++){ //Loops through the list of command objects and determines what object matches the command then that object is returned
				Command cmdToTest = cmds.get(i);
				if(cmdToTest.equals(cmdStr)){
					Command cmd = cmds.get(i);
					return cmd;
				}
			}
		}else{
			throw new CommandNotFoundException();
		}
		
		return null;
	}
	
	/**
	 * Gets the distance from a string
	 * 
	 * @param cmdLine
	 * @return
	 */
	public double getDistance(String[] cmdLine){
		try{
			for(int i = 0; i <= cmdLine.length; i++){ //Loops through cmdLine array and looks at the value after until or for then returns that value
				if(((cmdLine[i].equals("until")) || cmdLine[i].equals("for")) && (i + 1 < cmdLine.length)){
					double distance = AutoUtils.parseDouble(cmdLine[i + 1]);
					return distance;
				}
			}
		}catch(ArrayIndexOutOfBoundsException e){
			return 0;
		}
		
		return 0;
	}
	
	/**
	 * Gets the speed from a string
	 * 
	 * @param cmdLine
	 * @return
	 */
	public double getSpeed(String[] cmdLine){
		for(int j = 0; j <= cmdLine.length; j++){ //Loops through the cmdLine array and looks at the value after at then returns that value
			if((cmdLine[j].equals("at")) && (j + 1 < cmdLine.length)){
				double speed = AutoUtils.parseDouble(cmdLine[j + 1]);
				return speed;
			}
		}
		return 0.0;
	}

}
