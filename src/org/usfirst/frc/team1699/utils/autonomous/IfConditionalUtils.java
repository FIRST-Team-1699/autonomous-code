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
	
	public static boolean ifConditional(ArrayList<String> fileAsString, int startLine, Tokenizer reader){
		String[] conLine = fileAsString.get(startLine).split(" ");
		String runLine = fileAsString.get(startLine + 1);
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
				 conditionalEnd = i;
			 }
		}
		
		for(int i = conditionalStart; i < conditionalEnd; i++){
			conditional += conLine[i] + " ";
		}
		
		System.out.println(conditional);
		return evaluateConditional(conditional, reader);
	}
	
	public static boolean evaluateConditional(String conditional, Tokenizer reader){
		String firstStatement = "";
		String secondStatement = "";
		String conditionalSymbol  = "";
		Type firstType;
		Type secondType;
		
		for(int i = 0; i < conditional.length(); i++){
			if(isConditional(conditional.substring(i, i + 1))){
				firstStatement = conditional.substring(0, i);
				secondStatement = conditional.substring(i + 1);
				conditionalSymbol = getConditional(conditional);
				break;
			}
		}
		
		firstType = getType(firstStatement);
		secondType = getType(secondStatement);
		
		if((firstType.equals(Type.DOUBLE) || firstType.equals(Type.INTEGER)) && (secondType.equals(Type.DOUBLE) || secondType.equals(Type.INTEGER))){
			Token tok = reader.getTokens().get(0);
			switch(tok.token){
				case 0: return AutoUtils.parseDouble(firstStatement) < AutoUtils.parseDouble(secondStatement);
				case 1: return AutoUtils.parseDouble(firstStatement) > AutoUtils.parseDouble(secondStatement);
				case 2: return AutoUtils.parseDouble(firstStatement) <= AutoUtils.parseDouble(secondStatement);
				case 3: return AutoUtils.parseDouble(firstStatement) >= AutoUtils.parseDouble(secondStatement);
				case 4: return AutoUtils.parseDouble(firstStatement) == AutoUtils.parseDouble(secondStatement);
				case 5: return AutoUtils.parseDouble(firstStatement) != AutoUtils.parseDouble(secondStatement);
				default: return false;
			}
		}else if(firstType.equals(Type.STRING) && secondType.equals(Type.STRING)){
			System.out.println("Here");
			return firstStatement.equals(secondStatement);
		}else{
			return false;
		}
	}
	
	public static Type getType(String str){
		if((!str.contains(".") && (!str.contains("\"")))){
			return Type.INTEGER;
		}else if((str.contains(".")) && (!str.contains("\""))){
			return Type.DOUBLE;
		}else{
			return Type.STRING;
		}
	}
	
	public static int getIfLength(ArrayList<String> strArr, int currentLine){
		for(int i = currentLine; i < strArr.size(); i++){
			if(strArr.get(i).trim().equals("end")){
				return i - currentLine;
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
