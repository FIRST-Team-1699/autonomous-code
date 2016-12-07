/**
 * A class that houses an array list and makes sure that each name is unique
 * 
 * @author squirlemaster42
 */
package org.usfirst.frc.team1699.utils.command;

import java.util.ArrayList;

@Deprecated
public class CommandNameArray extends ArrayList{
	
	private ArrayList<String> list; //Hold list of names

	/**
	 * Constructor
	 */
	public CommandNameArray() { //Constructor to name new instance of an ArrayList that will hold String objects
		list = new ArrayList<String>();
	}

	/**
	 * Adds a name to the array
	 * 
	 * @param name
	 */
	public void addName(String name) { //Adds name to array if it is unique
		if(list.contains(name)){
			System.err.println("Id has already been used.");
			throw new NameUsedException();
		}
		list.add(name);
	}
	
	/**
	 * Puts a name at a certain index
	 * 
	 * @param index
	 * @param name
	 */
	public void setName(int index, String name){ //Used to put a name as a specific index
		if(list.contains(name)){
			System.err.println("Id has already been used.");
			throw new NameUsedException();
		}
		list.set(index, name);
	}
	
	/**
	 * Returns the name array
	 * 
	 * @return
	 */
	public ArrayList<String> getList() { //Returns list of names
		return list;
	}
	
}