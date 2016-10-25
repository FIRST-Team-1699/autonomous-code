/**
 * A class that houses an array list and makes sure that each name is unique
 * 
 * @author squirlemaster42
 */
package org.usfirst.frc.team1699.utils.command;

import java.util.ArrayList;

public class CommandNameArray {
	
		// Should store name and id and make sure that no id or name is a duplicate

		private ArrayList<String> list;

		public CommandNameArray() {
			list = new ArrayList<String>();
		}

		public void addName(String name) {
			if(list.contains(name)){
				System.err.println("Id has already been used.");
				return;
			}
			list.add(name);
		}

		public ArrayList<String> getList() {
			return list;
		}
}
