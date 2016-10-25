/**
 * FIRST Team 1699
 *
 * A class that contains the contents from a line.
 *
 * @author thatging3rkid, FIRST Team 1699
 */
package org.usfirst.frc.team1699.utils.inireader;

/**
 * Stores data from a configuration file. 
 */
public class ConfigLine {

	private String name;
	private Object value;

	/**
	 * Creates a basic ConfigLine with no contents.
	 */
	public ConfigLine() {
		this.name = new String();
		this.value = new Object();
	}

	/**
	 * Creates a ConfigLine with the specified contents
	 * 
	 * @param _name - the name of the value, what comes before the "="
	 * @param _value - an Object that can be any value
	 */
	public ConfigLine(String _name, Object _value) {
		this.name = _name;
		this.value = _value;
	}

	/**
	 * Gets the name of the line
	 * 
	 * @return - the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the value of the line in an Object
	 * 
	 * @return - the value of the line
	 */
	public Object getValue() {
		return value;
	}
	
	/**
	 * Overwrite the name
	 * 
	 * @param _name - new name
	 */
	public void setName(String _name) {
		this.name = _name;
	}
	
	/**
	 * Overwrite the value
	 * 
	 * @param _value - new value
	 */
	public void setValue(Object _value) {
		this.value = _value;
	}

	/**
	 * Creates a String that represents the line. For debugging or writing to a file.
	 * 
	 * @return - a String that represents the line
	 */
	@Override
	public String toString() {
		return "Name: " + this.name + "\nValue: " + this.value.toString();
	}

}
