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

public abstract class Command {

	private String name; //Holds command name
	private int id; //Holds command id
	private static CommandNameArray cmdNames = new CommandNameArray(); //Makes a new static instance of CommandNameArray
	private static CommandIdArray cmdId = new CommandIdArray(); //Makes a new static instance of CommandIdArray

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param id
	 */
	public Command(String name, int id) { //Constructor, sets instance vars equal to input and adds command name and id to respective lists
		this.name = name;
		this.id = id;
		cmdNames.addName(name);
		cmdId.addId(id);
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
	public boolean equals(String inpStr){ //Used to compare commands
		return this.name.toLowerCase().equals(inpStr.toLowerCase());
	}
	
	/**
	 * Gets command name array
	 * 
	 * @return
	 */
	public static CommandNameArray getCmdNames(){ //Returns CommandNameArray
		return cmdNames;
	}
	
	/**
	 * Gets command id array
	 * 
	 * @return
	 */
	public static CommandIdArray getCmdId(){ //ReturnCommandIdArray
		return cmdId;
	}
	
}
