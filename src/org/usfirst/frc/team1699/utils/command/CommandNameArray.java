/**
 * A class that houses an array list and makes sure that each name is unique
 * 
 * @author squirlemaster42
 */
package org.usfirst.frc.team1699.utils.command;

import java.util.ArrayList;

public class CommandNameArray {
	
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
				return;
			}
			list.add(name);
		}

		/**
		 * Returns the name array
		 * 
		 * @return
		 */
		public ArrayList<String> getList() { //Returns list of names
			return list;
		}
		
		/**
		 * Used to determine if the list contains a value
		 * 
		 * @param str
		 * @return
		 */
		public boolean contains(String str){ //Returns true if the ArrayList contains the input string
			return list.contains(str);
		}
}
