/**
 * Utils for finding an evaluating conditionals
 * 
 * @author squirlemaster42
 */
package org.usfirst.frc.team1699.utils.autonomous;

import java.util.ArrayList;

import org.usfirst.frc.team1699.utils.autonomous.Tokenizer.Token;

public class IfConditionalUtils {
	private static final String[] validConditionalSymbols = {"<", ">", "==", "<=", ">="}; //Stores valid conditional symbols
	
	/**
	 * Returns true if string is a conditional
	 * 
	 * @param fileAsString
	 * @param startLine
	 * @param reader
	 * @return
	 */
	public static boolean ifConditional(ArrayList<String> fileAsString, int startLine, Tokenizer reader){
		String[] conLine = fileAsString.get(startLine).split(" ");
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
		
		return evaluateConditional(conditional, reader);
	}
	
	/**
	 * Returns true if conditional string evaluates to true
	 * 
	 * @param conditional
	 * @param tokenizer
	 * @return
	 */
	public static boolean evaluateConditional(String conditional, Tokenizer tokenizer){
		String firstStatement = "";
		String secondStatement = "";
		String conditionalSymbol  = "";
		Type firstType;
		Type secondType;
		
		if(conditional.contains(">") || conditional.contains("<") || conditional.contains(">=") || conditional.contains("<=") || conditional.contains("==") || conditional.contains("!=")){
			for(int i = 0; i < conditional.length(); i++){
				if(conditional.substring(i, i + 1).equals(">") || conditional.substring(i, i + 1).equals("<") || conditional.substring(i, i + 1).equals(">=") || conditional.substring(i, i + 1).equals("<=") || conditional.substring(i, i + 1).equals("==") || conditional.substring(i, i + 1).equals("!=")){
					conditionalSymbol = conditional.substring(i, i + 1);
					firstStatement = conditional.substring(0, i);
					secondStatement = conditional.substring(i + 1).trim();
				}
			}
		}else{
			return AutoUtils.parseBoolean(conditional.trim());
		}
		
		firstType = getType(firstStatement);
		secondType = getType(secondStatement);
		
		if((firstType.equals(Type.DOUBLE) || firstType.equals(Type.INTEGER)) && (secondType.equals(Type.DOUBLE) || secondType.equals(Type.INTEGER))){
			tokenizer.tokenize(conditionalSymbol);
			Token tok = tokenizer.getTokens().get(0);
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
			return firstStatement.equals(secondStatement);
		}else{
			return false;
		}
	}
	
	/**
	 * Gets Type of string
	 * 
	 * @param str
	 * @return
	 */
	public static Type getType(String str){
		if((!str.contains(".") && (!str.contains("\"")))){
			return Type.INTEGER;
		}else if((str.contains(".")) && (!str.contains("\""))){
			return Type.DOUBLE;
		}else if(str.equals("true") || str.equals("false")){
			return Type.BOOLEAN;
		}else{
			return Type.STRING;
		}
	}
	
	/**
	 * Gets length of if
	 * 
	 * @param strArr
	 * @param currentLine
	 * @return
	 */
	public static int getIfLength(ArrayList<String> strArr, int currentLine){
		for(int i = currentLine; i < strArr.size(); i++){
			if(strArr.get(i).trim().equals("end")){
				return i - currentLine;
			}
		}
		return 0;
	}
	
	/**
	 * Returns true if string is a conditional symbol
	 * 
	 * @param conditional
	 * @return
	 */
	public static boolean isConditional(String conditional){	
		for(String x: validConditionalSymbols){
			return x.equals(conditional);
		}
		return false;
	}
	
	/**
	 * Returns conditional symbol from string
	 * 
	 * @param conditional
	 * @return
	 */
	public static String getConditional(String conditional){	
		for(String x: validConditionalSymbols){
			if(x.equals(conditional)){
				return x;
			}
		}
		return null;
	}
	
	/**
	 * Returns true if string contains a conditional
	 * 
	 * @param string
	 * @return
	 */
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
