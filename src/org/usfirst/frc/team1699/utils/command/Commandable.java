package org.usfirst.frc.team1699.utils.command;

public interface Commandable {

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
}
