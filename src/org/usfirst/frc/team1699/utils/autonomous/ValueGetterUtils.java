package org.usfirst.frc.team1699.utils.autonomous;

import java.util.ArrayList;

import org.usfirst.frc.team1699.utils.command.Command;

public class ValueGetterUtils {
	
	/**
	 * Determines whether to use sensor or not
	 * 
	 * @param cmdLine
	 * @return
	 */
	public static boolean getUseSensor(String[] cmdLine){https://www.youtube.com/watch?v=Lgw3D2KKqDQ
		try{
			for(int i = 0; i < cmdLine.length; i++){ //This loops looks for the word until in the cmdLine array and returns true if it is found
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
	public static Command getCmd(String[] cmdLine, ArrayList<Command> cmds) throws CommandNotFoundException{
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
	public static double getDistance(String[] cmdLine){
		try{
			for(int i = 0; i < cmdLine.length; i++){ //Loops through cmdLine array and looks at the value after until or for then returns that value
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
	public static double getSpeed(String[] cmdLine){
		for(int j = 0; j < cmdLine.length; j++){ //Loops through the cmdLine array and looks at the value after at then returns that value
			if((cmdLine[j].equals("at")) && (j + 1 < cmdLine.length)){
				double speed = AutoUtils.parseDouble(cmdLine[j + 1]);
				return speed;
			}
		}
		return 0.0;
	}
}
