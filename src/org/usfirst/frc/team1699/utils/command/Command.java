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

	private String name;
	private int id;
	private static CommandNameArray cmdNames = new CommandNameArray();
	private static CommandIdArray cmdId = new CommandIdArray();

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param id
	 */
	public Command(String name, int id) {
		this.name = name;
		this.id = id;
		cmdNames.addName(name);
		cmdId.addId(id);
	}

	/**
	 * This method should contain initializers
	 */
	public abstract void init();
	
	/**
	 * This method should contain code run by teleop
	 */
	public abstract void run();
	
	/**
	 * This method should contain code run by auto script
	 * 
	 * @param distance
	 * @param speed
	 * @param useSensor
	 */
	public abstract void runAuto(int distance, double speed, boolean useSensor);
	
	/**
	 * This method should contains code that returns if the auto command is done
	 * 
	 * @return
	 */
	public abstract boolean autoCommandDone();
	
	/**
	 * This command should contain code to output value to a dashboard
	 */
	public abstract void outputToDashboard();
	
	/**
	 * This method should contain code to zero all sensor values
	 */
	public abstract void zeroAllSensors();

	/**
	 * Returns the name of the command
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the id of the command
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Turns the command into a string
	 */
	@Override
	public String toString() {
		return "Command [name=" + name + ", id=" + id + "]";
	}
	
	/**
	 * .equals override
	 * 
	 * @param inpStr
	 * @return
	 */
	public boolean equals(String inpStr){
		return this.name.toLowerCase().equals(inpStr.toLowerCase());
	}
	
	/**
	 * Gets command name array
	 * 
	 * @return
	 */
	public static CommandNameArray getCmdNames(){
		return cmdNames;
	}
	
	/**
	 * Gets command id array
	 * 
	 * @return
	 */
	public static CommandIdArray getCmdId(){
		return cmdId;
	}
	
}
