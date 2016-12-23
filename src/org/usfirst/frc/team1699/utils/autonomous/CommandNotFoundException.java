/**
 * Exception thrown when an auto command is not found
 * 
 * @author squirlemaster42
 */
package org.usfirst.frc.team1699.utils.autonomous;

public class CommandNotFoundException extends RuntimeException {

	// Auto-generated Serial ID
	private static final long serialVersionUID = 3380041418989057044L;

	// This is an exception that it thrown when a command is not found inside the CommandNameArray or CommandIdArray
	
	/**
	 * Constructor
	 */
	public CommandNotFoundException() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param arg0
	 */
	public CommandNotFoundException(String arg0) {
		super(arg0);
	}
	
}
