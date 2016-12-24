/**
 * Will be used to store variables created in auto script
 * 
 * @author squirlemaster42
 */
package org.usfirst.frc.team1699.utils.autonomous;

public class Variable {
	private Type type; //Stores type
	private Value value; //Stores value
	private String name;
	
	/**
	 * Constructor
	 * 
	 * @param type
	 * @param value
	 */
	public Variable(Type type, Value value, String name){
		this.type = type;
		this.name = name;
		this.value = value;
	}

	/**
	 * Returns type
	 * 
	 * @return
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Sets type
	 * 
	 * @param type
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * Returns value
	 * 
	 * @return
	 */
	public Value getValue() {
		return value;
	}

	/**
	 * Gets value
	 * 
	 * @param value
	 */
	public void setValue(Value value) {
		this.value = value;
	}
}
