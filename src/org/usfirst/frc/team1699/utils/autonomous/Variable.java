/**
 * Will be used to store variables created in auto script
 * 
 * @author squirlemaster42
 */
package org.usfirst.frc.team1699.utils.autonomous;

import java.util.ArrayList;

/**
 * Stores variables in the auto script
 */
public class Variable implements Comparable<Variable> {
	private Type type; // Stores type
	private Value value; // Stores value
	private String name;
	private static ArrayList<Variable> vars = new ArrayList<>();
	
	/**
	 * Constructor
	 * 
	 * @param type
	 * @param value
	 * @param name
	 */
	public Variable(Type type, Value value, String name) {
	    if (vars.contains(new Variable(Type.INTEGER, new Value(0), name))) {
	        // print error message and overwrite old one
	        System.err.println("Variable: " + name + " already exists! Overwriting...");
	        Variable old = Variable.getVar(name);
	        old.setType(type);
	        old.setValue(value);
	    } else {
	        this.type = type;
		    this.name = name;
		    this.value = value;
		    vars.add(this);
	    }
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
	 * @param type new Type of this Variable
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * Returns value
	 * 
	 * @return the value of this Variable
	 */
	public Value getValue() {
		return value;
	}

	/**
	 * Sets a new value for this Variable
	 * 
	 * @param value new value of this Variable
	 */
	public void setValue(Value value) {
		this.value = value;
	}
	
	/**
	 * Gets the name of the Variable
	 * 
	 * @return the name of this Variable
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Gets a variable if it exists
	 * 
	 * @param name variable name
	 * @return the variable if it exists, null if it does not
	 */
	public static Variable getVar(String name){
		for(int i = 0; i < vars.size(); i++){
			if(vars.get(i).getName().equals(name)){
				return vars.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Tests to see if the given variable exists
	 * 
	 * @param name variable name
	 * @return true if the variable exists
	 */
	public static boolean isVar(String name){
	    // See if the name is contained in the List<Variable>
		return vars.contains(new Variable(Type.INTEGER, new Value(0), name));
	}
	
	/**
	 * @inheritDoc
	 */
	@Override
	public boolean equals(Object obj) {
	    if (obj instanceof Variable) {
	        Variable var = (Variable) obj;
	        // if the variable names are equal, then we can assume that they are the same variable
	        // you should never have two variables with the same name in the script
	        return var.getName().equals(this.getName());
	    } else {
	        return false;
	    }
	}

    /**
     * @inheritDoc
     */
    @Override
    public int compareTo(Variable var) {
        return this.getName().compareTo(var.getName());
    }
}
