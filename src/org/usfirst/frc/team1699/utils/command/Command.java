/**
 * FIRST Team 1699
 * 
 * Abstract Command Class
 * 
 * @author squirlemaster42, FIRST Team 1699
 * 
 * @version v0.2-norobot
 */
package org.usfirst.frc.team1699.utils.command;

public abstract class Command implements Commandable{

	private String name; //Holds command name
	private int id; //Holds command id

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param id
	 */
	public Command(final String name, final int id) { //Constructor, sets instance vars equal to input and adds command name and id to respective sets
		this.name = name;
		this.id = id;
	}

	/**
	 * This method should contain initializers
	 */
	public abstract void init(); //Abstract method that should hold code that is supposed to be called during init
	
	/**
	 * This method should contain code run by teleop
	 */
	public abstract void run(); //Abstract method that should hold code that is supposed to be called during teleop
	
	/**
	 * This command should contain code to output value to a dashboard
	 */
	public abstract void outputToDashboard(); //Abstract method that should hold code that send information to Dashboard
	
	/**
	 * This method should contain code to zero all sensor values
	 */
	public abstract void zeroAllSensors(); //Abstract method that should hold code that to zero any and all sensors

	/**
	 * Returns the name of the command
	 * 
	 * @return
	 */
	public String getName() { //Returns name of command
		return name;
	}

	/**
	 * Returns the id of the command
	 * 
	 * @return
	 */
	public int getId() { //Returns id of command
		return id;
	}

	/**
	 * Turns the command into a string
	 */
	@Override
	public String toString() { //Turns the command into a string
		return "Command [name=" + name + ", id=" + id + "]";
	}
	
	/**
	 * .equals override
	 * 
	 * @param inpStr
	 * @return
	 */
	public boolean equals(final String inpStr){ //Used to compare commands
		return this.name.toLowerCase().equals(inpStr.toLowerCase());
	}
}
