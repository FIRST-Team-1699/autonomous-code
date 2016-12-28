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
		Integer value;
		String name;
		String[] lineArr = line.split(" ");
		name = lineArr[1];
		value = AutoUtils.parseInt(lineArr[2]);
		return new Variable(Type.INTEGER, new Value(value), name);
	}

	private static Variable makeBoolean(String line){
		Boolean value;
		String name;
		String[] lineArr = line.split(" ");
		name = lineArr[1];
		value = AutoUtils.parseBoolean(lineArr[2]);
		return new Variable(Type.INTEGER, new Value(value), name);
	}
	
	private static Variable makeDouble(String line){
		Double value;
		String name;
		String[] lineArr = line.split(" ");
		name = lineArr[1];
		value = AutoUtils.parseDouble(lineArr[2]);
		return new Variable(Type.INTEGER, new Value(value), name);
	}
	
	private static Variable makeString(String line){
		String value;
		String name;
		String[] lineArr = line.split(" ");
		name = lineArr[1];
		value = lineArr[2];
		return new Variable(Type.INTEGER, new Value(value), name);
	}
	
	public static boolean isVariable(String line, Tokenizer tokenizer){
		tokenizer.tokenize(line);
		Token tok = tokenizer.getTokens().get(0);
		return tok.token == 6 || tok.token == 7 || tok.token == 8 || tok.token == 9;
	}
}
