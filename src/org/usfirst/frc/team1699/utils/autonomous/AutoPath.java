/**
 * Class that generates a path for auto
 * 
 * @author squirlemaster42
 */
package org.usfirst.frc.team1699.utils.autonomous;

import org.usfirst.frc.team1699.utils.command.Command;

public class AutoPath {
	
	private String path;
	private String[] fileAsString;
	private Object[] cmds;
	
	/**
	 * Constructor
	 * 
	 * @param path
	 * @param numLines
	 * @param cmds
	 */
	//Change to arrayList 
	public AutoPath(String path, int numLines, Object[] cmds){
		this.path = path;
		this.cmds = cmds;
		fileAsString = AutoUtils.loadFileAsArray(path, numLines);
	}
	
	/**
	 * Runs all lines of file (main)
	 */
	public void runScript(){
		for(int i = 0; i < fileAsString.length; i++){
			try{
				callCommandFromString(fileAsString[i]);
			}catch(CommandNotFoundException e){
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
	//We may want to split this up into more than one method
	public void callCommandFromString(String inp){
		String[] cmdLine = inp.split(" ");
		double speed = getSpeed(cmdLine);
		int distance = getDistance(cmdLine);
		Object cmd = getCmd(cmdLine);
		boolean useSensor = getUseSensor(cmdLine);
		
		((Command) cmd).runAuto(distance, speed, useSensor);
		
	}
	
	/**
	 * Determines whether to use sensor or not
	 * 
	 * @param cmdLine
	 * @return
	 */
	public boolean getUseSensor(String[] cmdLine){
		try{
			for(int i = 0; i <= cmdLine.length; i++){
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
	public Object getCmd(String[] cmdLine) throws CommandNotFoundException{
		if(Command.getCmdNames().contains(cmdLine[0])){
			String cmdStr = cmdLine[0];
			
			for(int i = 0; i < cmds.length; i++){
				Command cmdToTest = (Command) cmds[i];
				if(cmdToTest.equals(cmdStr)){
					Object cmd = cmds[i];
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
	public int getDistance(String[] cmdLine){
		try{
			for(int i = 0; i <= cmdLine.length; i++){
				if(((cmdLine[i].equals("until")) || cmdLine[i].equals("for")) && (i + 1 < cmdLine.length)){
					int distance = AutoUtils.parseInt(cmdLine[i + 1]);
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
		for(int j = 0; j <= cmdLine.length; j++){
			if((cmdLine[j].equals("at")) && (j + 1 < cmdLine.length)){
				double speed = AutoUtils.parseDouble(cmdLine[j + 1]);
				return speed;
			}
		}
		return 0.0;
	}
	
	/**
	 * Returns path to file that contains autonomous scripts
	 * 
	 * @return
	 */
	public String getPath() {
		return path;
	}
 
	/**
	 * Returns file as string array
	 * 
	 * @return
	 */
	public String[] getFileAsString() {
		return fileAsString;
	}

}