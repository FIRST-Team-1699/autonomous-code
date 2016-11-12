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
	public AutoPath(String path, int numLines, Object[] cmds){
		this.path = path;
		this.cmds = cmds;
		fileAsString = AutoUtils.loadFileAsArray(path, numLines);
	}
	
	/**
	 * Runs all lines of file (main)
	 */
	public void runScript(){
		for(int i = 0; i <= fileAsString.length; i++){
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
	public void callCommandFromString(String inp) throws CommandNotFoundException{
		String[] cmdLine = inp.split(" ");
		String cmdStr;
		double speed = 0;
		int distance = 0;
		Object cmd = null;
		if(Command.getCmdNames().contains(cmdLine[0])){
			cmdStr = cmdLine[0];
			
			for(int i = 0; i <= cmds.length; i++){
				if(cmds[i].equals(cmds)){
					cmd = cmds[i];
				}
			}
		}else{
			throw new CommandNotFoundException();
		}
		
		for(int i = 0; i <= cmdLine.length; i++){
			if(((cmdLine[i].equals("until")) || cmdLine[i].equals("for")) && (i + 1 < cmdLine.length)){
				distance = AutoUtils.parseInt(cmdLine[i]);
				break;
			}
		}
		
		for(int j = 0; j <= cmdLine.length; j++){
			if((cmdLine[j].equals("at")) && (j + 1 < cmdLine.length)){
				speed = AutoUtils.parseDouble(cmdLine[j + 1]);
				break;
			}
		}
		
		((Command) cmd).runAuto(distance, speed);
		
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