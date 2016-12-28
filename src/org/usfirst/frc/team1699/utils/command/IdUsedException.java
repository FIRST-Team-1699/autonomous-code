package org.usfirst.frc.team1699.utils.command;

public class IdUsedException extends RuntimeException{

	// Auto-generated Serial ID
	private static final long serialVersionUID = 363167376679308527L;
	
	// This is an exception that it thrown when a command is not found inside the CommandNameArray or CommandIdArray
	public IdUsedException() {
		super();
	}

	public IdUsedException(String arg0) {
		super(arg0);
	}

}
