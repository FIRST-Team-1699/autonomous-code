/**
 * Converts strings into tokens
 * 
 * @author squirlemaster42
 * @see http://cogitolearning.co.uk/?p=525
 */
package org.usfirst.frc.team1699.utils.autonomous;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
	
	private class TokenInfo{
		public final Pattern regex; //Stores a pattern
		public final int token; //Stores token id
		
		/**
		 * Constructor
		 * 
		 * @param regex
		 * @param token
		 */
		public TokenInfo(Pattern regex, int token){
			super();
			this.regex = regex;
			this.token = token;
		}
	}
	
	public class Token{
		public final int token; //Stores token id
		public final String sequence; //Stores string
		
		/**
		 * Constructor
		 * 
		 * @param token
		 * @param sequence
		 */
		public Token(int token, String sequence){
			super();
			this.token = token;
			this.sequence = sequence;
		}
	}
	
	private LinkedList<TokenInfo> tokenInfos; //List of token info
	private LinkedList<Token> tokens; //List of tokens
	
	/**
	 * Constructor
	 */
	public Tokenizer(){
		tokenInfos = new LinkedList<TokenInfo>(); //Init
		tokens = new LinkedList<Token>(); //Init
	}
	
	/**
	 * Adds token
	 * 
	 * @param regex
	 * @param token
	 */
	public void add(String regex, int token){
		tokenInfos.add(new TokenInfo(Pattern.compile("^(" + regex + ")"), token));
	}
	
	/**
	 * Creates tokens for string
	 * 
	 * @param str
	 */
	public void tokenize(String str){
		String s = str.trim();
		tokens.clear();
		while(!s.equals("")){
			boolean match = false;
			for (TokenInfo info : tokenInfos){
				Matcher m = info.regex.matcher(s);
				if(m.find()){
					match = true;
					String tok = m.group().trim();
					s = m.replaceFirst("").trim();
					tokens.add(new Token(info.token, tok));
					break;
				}
			}
			if(!match){
				throw new ParserException("Unexpected character input: "+ s);
			}
		}
	}

	/**
	 * Returns tokens
	 * 
	 * @return
	 */
	public LinkedList<Token> getTokens() {
		return tokens;		
	}
}
