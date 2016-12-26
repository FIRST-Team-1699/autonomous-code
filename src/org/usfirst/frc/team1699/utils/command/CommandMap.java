package org.usfirst.frc.team1699.utils.command;

import java.util.HashMap;
import java.util.Map;

public class CommandMap {
	private Map<String, Command> cmds;
	
	public CommandMap(){
		cmds = new HashMap<String, Command>();
	}
	
	public Map<String, Command> getCmds(){
		return cmds;
	}
	
	public Command getCommand(String key){
		return cmds.get(key);
	}
	
	public boolean hasKey(String key){
		return cmds.containsKey(key);
	}
	
	public void removeCmd(String key){
		cmds.remove(key);
	}
	
	public int size(){
		return cmds.size();
	}
	
	public void addEntry(String key, Command cmd){
		if(cmds.containsKey(key)){
			throw new NameUsedException();
		}
		
		cmds.put(key, cmd);
	}
}
