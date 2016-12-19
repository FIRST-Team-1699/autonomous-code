package org.usfirst.frc.team1699.utils.autonomous;

import java.util.ArrayList;

import org.usfirst.frc.team1699.utils.command.Command;

public class IfConditionalUtils {
	private static final String[] validConditionalSymbols = {"<", ">", "==", "<=", ">="};
	
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
	
	public static boolean ifConditional(String[] cmdLine, int startLine){
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
		
		return evaluateConditional(conditional);
	}
	
	public static boolean evaluateConditional(String conditional){
		String firstStatement = "";
		String secondStatment = "";
		String conditionalSymbol  = "";
		
		for(int i = 0; i < conditional.length(); i++){
			if(isConditional(conditional.substring(i, i + 1))){
				
			}
		}
		
		return true;
	}
	
	public static boolean isConditional(String conditional){	
		for(String x: validConditionalSymbols){
			return x.equals(conditional);
		}
		return false;
	}
	
	public static String getConditional(String conditional){	
		for(String x: validConditionalSymbols){
			if(x.equals(conditional)){
				return x;
			}
		}
		return null;
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
