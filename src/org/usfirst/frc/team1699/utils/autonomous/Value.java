/**
 * Holds an object that represents the value of a variable 
 * 
 * @author squirlemaster42
 */
package org.usfirst.frc.team1699.utils.autonomous;

public class Value {
	private Object value;
	
	public Value(Object value){
		this.value = value;
	}
	
	public Object getValue(){
		return value;
	}
}
