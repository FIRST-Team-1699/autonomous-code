package org.usfirst.frc.team1699.utils.autonomous;

import java.util.ArrayList;

import org.usfirst.frc.team1699.utils.command.Command;

public class IfConditionalUtils {
	
	public static boolean isCommand(String string, ArrayList<Command> cmds) {
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
}
