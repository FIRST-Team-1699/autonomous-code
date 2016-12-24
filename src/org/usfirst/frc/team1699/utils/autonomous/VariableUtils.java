package org.usfirst.frc.team1699.utils.autonomous;

import org.usfirst.frc.team1699.utils.autonomous.Tokenizer.Token;

public class VariableUtils {
	
	public static Variable makeVar(String line, Tokenizer tokenizer){
		tokenizer.tokenize(line);
		Token tok =  tokenizer.getTokens().get(0);
		switch(tok.token){
			case 6: return makeInt(line);
			case 7: return makeBoolean(line);
			case 8: return makeDouble(line);
			case 9: return makeString(line);
			default: throw new InvalidLineException();
		}
	}
	
	private static Variable makeInt(String line){
		return null;
	}

	private static Variable makeBoolean(String line){
		return null;
	}
	
	private static Variable makeDouble(String line){
		return null;
	}
	
	private static Variable makeString(String line){
		return null;
	}
	
	public static boolean isVariable(String line, Tokenizer tokenizer){
		tokenizer.tokenize(line);
		Token tok = tokenizer.getTokens().get(0);
		return tok.token == 6 || tok.token == 7 || tok.token == 8 || tok.token == 9;
	}
}
