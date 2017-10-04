/**
 * Utils for getting values and running commands
 * 
 * @author squirlemaster42
 */
package org.usfirst.frc.team1699.utils.autonomous;

import org.usfirst.frc.team1699.utils.command.AutoCommandMap;

public class ValueGetterUtils {
	
	/**
	 * Returns true if the string is a command
	 * 
	 * @param string
	 * @param cmds
	 * @return boolean
	 */
	@SuppressWarnings("unused")
	public static boolean isCommand(String string, AutoCommandMap map) {
		String[] inp = string.split(" "); //Splits string at spaces
		for(int i = 0; i < inp.length; i++){ //Looks for key
			return map.hasKey(inp[i]);
		}
		return false;
	}
	
	/**
	 * Determines whether to use sensor or not
	 * 
	 * @param cmdLine
	 * @return boolean
	 */
	public static boolean getUseSensor(String[] cmdLine){
		try{
			for(int i = 0; i < cmdLine.length; i++){ //This loops looks for the word until in the cmdLine array and returns true if it is found
				if(cmdLine[i].equals("until")){  //Looks for word until
					return true;
				}
			}
		}catch(ArrayIndexOutOfBoundsException e){ //Catches ArrayIndexOutOfBoundsException
			return false;
		}
		return false;
	}
	
	/**
	 * Looks as a string and converts it to a command
	 * 
	 * @param cmdLine
	 * @return Command
	 * @throws CommandNotFoundException
	 */
	public static AutoCommand getCmd(String[] cmdLine, AutoCommandMap map) throws CommandNotFoundException{
		if(map.hasKey(cmdLine[0])){ //Looks at cmdLine and determines if the first line is a valid command
			String cmdStr = cmdLine[0]; //Splits String at space
			AutoCommand cmd = map.getCommand(cmdStr); //Gets the Command from the map at a specific key
			return cmd;
		}else{
			throw new CommandNotFoundException(); //Throws exception
		}
	}
	
	/**
	 * Gets the distance from a string
	 * 
	 * @param cmdLine
	 * @return double
	 */
	public static double getDistance(String[] cmdLine){
		try{
			for(int i = 0; i < cmdLine.length; i++){ //Loops through cmdLine array and looks at the value after until or for then returns that value
				if(((cmdLine[i].equals("until")) || cmdLine[i].equals("for")) && (i + 1 < cmdLine.length)){ //Loops the array and gets the distance
					double distance = AutoUtils.parseDouble(cmdLine[i + 1]); //Parses String to double
					return distance;
				}
			}
		}catch(ArrayIndexOutOfBoundsException e){ //Catches ArrayIndexOutOfBoundsException
			return 0;
		}
		
		return 0;
	}
	
	/**
	 * Gets the speed from a string
	 * 
	 * @param cmdLine
	 * @return double
	 */
	public static double getSpeed(String[] cmdLine){
		for(int j = 0; j < cmdLine.length; j++){ //Loops through the cmdLine array and looks at the value after at then returns that value
			if((cmdLine[j].equals("at")) && (j + 1 < cmdLine.length)){
				double speed = AutoUtils.parseDouble(cmdLine[j + 1]);
				return speed;
			}
		}
		return 0.0;
	}
	
	/**
	 * Runs each line
	 * Called from runScript()
	 * 
	 * @param inp
	 * @throws CommandNotFoundException
	 */
	public static void callCommandFromString(String inp, AutoCommandMap map){
		String[] cmdLine = inp.split(" "); //Creates a string array and sets it equal to the input string split at spaces
		double speed = ValueGetterUtils.getSpeed(cmdLine); //Makes a double called speed and set it equal to the speed specified in the auto file
		double distance = ValueGetterUtils.getDistance(cmdLine); //Makes a double called distance and set it equal to the distance specified in the auto file
		AutoCommand cmd = ValueGetterUtils.getCmd(cmdLine, map); //Makes an object called command and sets it equal to the command in the auto file
		boolean useSensor = ValueGetterUtils.getUseSensor(cmdLine); //Makes a boolean called use sensor and set true if the auto file specifies that a sensor should be used
		
		((AutoCommand) cmd).runAuto(distance, speed, useSensor); //Cast the cmd object to type Command and runs the runAuto method in it
	}
}
