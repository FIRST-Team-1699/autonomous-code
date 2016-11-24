package org.usfirst.frc.team1699.utils.command;

public class NameUsedException extends RuntimeException{
	
	// Auto-generated Serial ID
	private static final long serialVersionUID = -2248943409746496340L;
	
	// This is an exception that it thrown when a command is not found inside the CommandNameArray or CommandIdArray
	public NameUsedException() {
		super();
	}

	public NameUsedException(String arg0) {
		super(arg0);
	}
}
