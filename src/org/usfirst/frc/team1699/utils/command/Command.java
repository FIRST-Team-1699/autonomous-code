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

	// id is used for commands run in auto. It should be set to an integer
	// value that corresponds to
	// the value used when wanting to call the command from the autonomous file.
	public Command(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public abstract void init();
	public abstract void run();
	public abstract void zeroAllSensors(); // zeroAllSensors may need to be looked at and changed
	//public abstract boolean isFinished(); Not used at this time may change in the future.

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Command [name=" + name + ", id=" + id + "]";
	}
	
	@Override
	public boolean equals(String inpStr){
		return this.name.equals(inpStr);
	}
}
