package org.usfirst.frc.team1699.utils.autonomous;

public class Variable {
	private Type type;
	private Value value;
	
	public Variable(Type type, Value value){
		this.type = type;
		this.value = value;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}
}
