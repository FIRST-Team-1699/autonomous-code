package org.usfirst.frc.team1699.utils.autonomous;

import java.util.ArrayList;

import org.usfirst.frc.team1699.utils.autonomous.Tokenizer.Token;
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
	
	public static boolean ifConditional(String[] cmdLine, int startLine, AutoScriptReader reader){
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
		
		return evaluateConditional(conditional, reader);
	}
	
	public static boolean evaluateConditional(String conditional, AutoScriptReader reader){
		String firstStatement = "";
		String secondStatement = "";
		String conditionalSymbol  = "";
		
		for(int i = 0; i < conditional.length(); i++){
			if(isConditional(conditional.substring(i, i + 1))){
				firstStatement = conditional.substring(0, i);
				secondStatement = conditional.substring(i + 1);
				conditionalSymbol = getConditional(conditional);
				break;
			}
		}
		
		Object firstStatmentObject = parseStringToObject(firstStatement);
		Object secondStatmentObject = parseStringToObject(secondStatement);
		
		if((firstStatmentObject instanceof Integer && secondStatmentObject instanceof Integer) || (firstStatmentObject instanceof Double && secondStatmentObject instanceof Double) || (firstStatmentObject instanceof Integer && secondStatmentObject instanceof Double) || (firstStatmentObject instanceof Double && secondStatmentObject instanceof Integer)){
			Token tok = reader.getTokenizer().getTokens().get(0);
			switch(tok.token){
				case 0: return (int) firstStatmentObject < (int) secondStatmentObject;
				case 1: return (int) firstStatmentObject > (int) secondStatmentObject;
				case 2: return (int) firstStatmentObject <= (int) secondStatmentObject;
				case 3: return (int) firstStatmentObject >= (int) secondStatmentObject;
				case 4: return (int) firstStatmentObject == (int) secondStatmentObject;
				case 5: return (int) firstStatmentObject != (int) secondStatmentObject;
				default: return false;
			}
		}else if(firstStatmentObject instanceof String && secondStatmentObject instanceof String){
			return firstStatmentObject.equals(secondStatmentObject);
		}else{
			return false;
		}
	}
	
	public static Object parseStringToObject(String str){
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			try {
				return Double.parseDouble(str);
			} catch (NumberFormatException e1) {
				return str;
			}
		}
	}
	
	public static int getIfLength(ArrayList<String> strArr, int currentLine){
		for(int i = currentLine; i < strArr.size(); i++){
			if(strArr.get(i).trim().equals("end")){
				return i;
			}
		}
		return 0;
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
