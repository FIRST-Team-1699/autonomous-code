/**
 * Exception thrown when line is invalid
 * 
 * @author squirlemaster42
 */
package org.usfirst.frc.team1699.utils.autonomous;

public class InvalidLineException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor
	 */
	public InvalidLineException() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param arg0
	 */
	public InvalidLineException(String arg0) {
		super(arg0);
	}

}
