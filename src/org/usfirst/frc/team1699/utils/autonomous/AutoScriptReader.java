/**
 * Class that generates a path for auto
 * 
 * @author squirlemaster42
 * @author TheCookingCookie
 */
package org.usfirst.frc.team1699.utils.autonomous;

import java.util.ArrayList;

import org.usfirst.frc.team1699.utils.command.Command;
import org.usfirst.frc.team1699.utils.inireader.ConfigSection;

public class AutoScriptReader {
	
	private String path; //Stores the path a the text file containing auto script
	private ArrayList<String> fileAsString; //Array list that hold the autoFile as an array of strings
	private static ArrayList<Command> cmds; //Holds a list of all commands in an array
	private Tokenizer tokenizer; //Holds an instance of Tokenizer
	private ConfigSection cs; //Holds an instance of ConfigSection
	
	/**
	 * Constructor
	 * 
	 * @param path
	 * @param cmds
	 */
	@SuppressWarnings("static-access")
	public AutoScriptReader(String path, ArrayList<Command> cmds){
		//Sets instance vars to values input by programmer
		this.path = path;
		this.cmds = cmds;
		fileAsString = AutoUtils.loadFileAsArray(path); //Sets fileAsArray list equal to the file
		tokenizer = new Tokenizer();
		addTokens();
	}
	
	/**
	 * Constructor
	 * 
	 * @param cs
	 * @param cmds
	 */
	@SuppressWarnings("static-access")
	public AutoScriptReader(ConfigSection cs, ArrayList<Command> cmds){
		//Sets instance vars to values input by programmer
		this.cmds = cmds;
		fileAsString = AutoUtils.loadFileAsArray(cs); //Sets fileAsArray list equal to the file
		tokenizer = new Tokenizer();
		addTokens();
	}
	
	/**
	 * Adds tokens to tokenizer
	 */
	private void addTokens(){
		tokenizer.add("<", 0);
		tokenizer.add(">", 1);
		tokenizer.add("<=", 2);
		tokenizer.add(">=", 3);
		tokenizer.add("==", 4);
		tokenizer.add("!=", 5);
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
	 * Returns an instance of Tokenizer
	 * 
	 * @return
	 */
	public Tokenizer getTokenizer(){
		return this.tokenizer;
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
				if(IfConditionalUtils.containsIfConditional(fileAsString.get(i))){
					if(IfConditionalUtils.ifConditional(fileAsString, i, tokenizer)){
						for(int j = i + 1; j < IfConditionalUtils.getIfLength(fileAsString, i) + i; j++){
							ValueGetterUtils.callCommandFromString(fileAsString.get(j), cmds);
						}
					}
					i += IfConditionalUtils.getIfLength(fileAsString, i);
				}else if(ValueGetterUtils.isCommand(fileAsString.get(i), cmds)){
					ValueGetterUtils.callCommandFromString(fileAsString.get(i), cmds); //Sends string to method so it can be converted to an object then calls command's run autoMethod
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
