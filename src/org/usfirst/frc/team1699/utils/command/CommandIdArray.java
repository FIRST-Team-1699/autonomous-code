/**
 * A class that houses an array list and makes sure that each id is unique
 * 
 * @author squirlemaster42
 */
package org.usfirst.frc.team1699.utils.command;

import java.util.ArrayList;

public class CommandIdArray {
	// Should store name and id and make sure that no id or name is a duplicate

	private ArrayList<Integer> list; //Hold list of ids

	/**
	 * Constructor
	 */
	public CommandIdArray() { //Constructor to name new instance of an ArrayList that will hold Integer objects
		list = new ArrayList<Integer>();
	}

	/**
	 * Adds id to the array
	 * 
	 * @param id
	 */
	public void addId(int id) { //Adds id to array if it is unique
		if (list.contains(id)) {
			System.err.println("Id has already been used.");
			throw new IdUsedException();
		}

		list.add(id);
	}

	/**
	 * Returns the id array
	 * 
	 * @return
	 */
	public ArrayList<Integer> getList() { //Returns list of ids
		return list;
	}
}
