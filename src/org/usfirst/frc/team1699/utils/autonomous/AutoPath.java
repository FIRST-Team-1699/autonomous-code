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
	private int numLines;
	
	public AutoPath(String path, int numLines  ){
		this.path = path;
		this.numLines = numLines;
		fileAsString = AutoUtils.loadFileAsArray(path, numLines);
	}
	
	//Drive for 20 at 0.6
	public void callCommandFromString(String inp) throws CommandNotFoundException{
		String[] cmdLine = inp.split(" ");
		String cmd;
		double speed;
		if(Command.getCmdNames().contains(cmdLine[0])){
			cmd = cmdLine[0];
		}else{
			throw new CommandNotFoundException();
		}
		
		int distance = AutoUtils.parseInt(cmdLine[3]);
		
		for(int i = 0; i < cmdLine.length; i++){
			if((cmdLine[i].equals("at")) && (i + 1 < cmdLine.length)){
				speed = AutoUtils.parseDouble(cmdLine[i + 1]);
			}
		}
		
	}
;	
	public Object stringToObject(String str){
		return null;
	}
	
	public String getPath() {
		return path;
	}
 
	public String[] getFileAsString() {
		return fileAsString;
	}
	
	public void setNumLines(int numLines){
		this.numLines = numLines;
	}

	public int getWidth() {
		return numLines;
	}

}