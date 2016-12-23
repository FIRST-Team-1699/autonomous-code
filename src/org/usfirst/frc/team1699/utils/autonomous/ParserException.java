/**
 * Exception thrown when parser finds an error
 * 
 * @author squirlemaster42
 */
package org.usfirst.frc.team1699.utils.autonomous;

public class ParserException extends RuntimeException {

	private static final long serialVersionUID = 1L; //Version id

	/**
	 * Constructor
	 * 
	 * @param msg
	 */
	public ParserException(String msg) {
		super(msg);
	}
}