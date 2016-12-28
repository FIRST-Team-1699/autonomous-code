package org.usfirst.team1699.test.autonomous;

import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;
import org.usfirst.frc.team1699.utils.autonomous.AutoScriptReader;
import org.usfirst.frc.team1699.utils.autonomous.AutoUtils;
import org.usfirst.frc.team1699.utils.autonomous.CommentUtils;
import org.usfirst.frc.team1699.utils.autonomous.ParserException;
import org.usfirst.frc.team1699.utils.autonomous.Tokenizer;
import org.usfirst.frc.team1699.utils.command.Command;
import org.usfirst.frc.team1699.utils.command.CommandMap;
import org.usfirst.team1699.test.autonomous.commands.Drive;
import org.usfirst.team1699.test.autonomous.commands.Shoot;
import org.usfirst.team1699.test.autonomous.commands.Turn;

public class AutoTestMain {
	
	@Test
	public void autonomousTest(){
		String filePath = "autoTestFiles/test.nav";
		
		Drive d = new Drive("Drive", 0);
		Turn t = new Turn("Turn", 1);
		Shoot s = new Shoot("Shoot", 2);
		CommandMap map = new CommandMap();
		map.addEntry(d.getName(), d);
		map.addEntry(t.getName(), t);
		map.addEntry(s.getName(), s);
		
		AutoScriptReader path = new AutoScriptReader(filePath, map);
		
		path.runScript();
	}
	
	@Test
	@Ignore
	public void arrayListFileTest(){
		String filePath = "autoTestFiles/test.nav";
		System.out.println(AutoUtils.loadFileAsArray(filePath));
	}
	
	@Test
	@Ignore
	public void commentTest(){
		String test = "This is a line. //This should be gone";
		String other = "This stays. ~This is gone.";
		
		System.out.println(CommentUtils.removeLineComments(test));
		System.out.println(CommentUtils.removeLineComments(other));
	}
	
	@Test
	@Ignore
	public void tokenizerTest(){
		Tokenizer tokenizer = new Tokenizer();
		tokenizer.add("<", 0);
		tokenizer.add(">", 1);
		tokenizer.add("<=", 2);
		tokenizer.add(">=", 3);
		tokenizer.add("==", 4);
		tokenizer.add("!=", 5);
		
		try{
			tokenizer.tokenize("==  < > !=");
			
			for(Tokenizer.Token tok : tokenizer.getTokens()){
				System.out.println(tok.token + "	" + tok.sequence);
			}
		}catch (ParserException e){
			System.out.println(e.getMessage());
		}
	}
}
