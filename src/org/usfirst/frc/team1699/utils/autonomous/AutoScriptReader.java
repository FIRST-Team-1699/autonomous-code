/**
 * Class that generates a path for auto
 * 
 * @author squirlemaster42
 * @author TheCookingCookie
 */
package org.usfirst.frc.team1699.utils.autonomous;

import java.util.List;

import org.usfirst.frc.team1699.utils.command.CommandMap;
import org.usfirst.frc.team1699.utils.inireader.ConfigSection;

public class AutoScriptReader {
	
	private String path; //Stores the path a the text file containing auto script
	private List<String> fileAsString; //Array list that hold the autoFile as an array of strings
	private ConfigSection cs; //Holds an instance of ConfigSection
	private CommandMap map;
	
	/**
	 * Constructor
	 * 
	 * @param path
	 * @param cmds
	 */
	public AutoScriptReader(String path, CommandMap map){
		//Sets instance vars to values input by programmer
		this.path = path;
		this.map = map;
		fileAsString = AutoUtils.loadFileAsArray(path); //Sets fileAsArray list equal to the file
	}
	
	/**
	 * Constructor
	 * 
	 * @param cs
	 * @param cmds
	 */
	public AutoScriptReader(ConfigSection cs, CommandMap map){
		//Sets instance vars to values input by programmer
		this.map = map;
		fileAsString = AutoUtils.loadFileAsArray(cs); //Sets fileAsArray list equal to the file
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
	public List<String> getFileAsString() { //Returns the ArrayList of the file as a string
		return fileAsString;
	}
	
	/**
	 * Returns an instance of ConfigSection
	 * 
	 * @return
	 */
	public ConfigSection getCs() {
		return cs;
	}
	
	/**
	 * Runs all lines of file (main)
	 */
	public void runScript(){
		for(int i = 0; i < fileAsString.size(); i++){ //Loops through fileAsString array
			try{
				if(ValueGetterUtils.isCommand(fileAsString.get(i), map)){
					ValueGetterUtils.callCommandFromString(fileAsString.get(i), map); //Sends string to method so it can be converted to an object then calls command's run autoMethod
				}else if(CommentUtils.isComment(fileAsString.get(i))){
					i += 1;
				}else{
					throw new InvalidLineException();
				}
			}catch(CommandNotFoundException e){ //Detects if there is a error where the command is not found
				System.out.println("Your autonomous script has failed because a command does not exist.");
				e.printStackTrace();
				break;
			}
		}
	}	
}
