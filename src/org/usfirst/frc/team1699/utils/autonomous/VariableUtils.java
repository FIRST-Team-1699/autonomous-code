package org.usfirst.frc.team1699.utils.autonomous;

import org.usfirst.frc.team1699.utils.autonomous.Tokenizer.Token;

public class VariableUtils {
	
	public static Variable makeVar(String line, Tokenizer tokenizer){
		return null;
	}
	
	public static boolean isVariable(String line, Tokenizer tokenizer){
		tokenizer.tokenize(line);
		Token tok = tokenizer.getTokens().get(0);
		return tok.token == 6 || tok.token == 7 || tok.token == 8 || tok.token == 9;
	}
}
