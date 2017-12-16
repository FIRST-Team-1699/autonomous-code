/**
 * Interface for AutoCommand
 * 
 * @author squirlemaster42
 */
package org.usfirst.frc.team1699.utils.autonomous;

public interface AutoCommand{
	
 	/**
	 * This method should contain code run by auto script
	 * 
	 * @param distance
	 * @param speed
	 * @param useSensor
	 */
	public void runAuto(final double distance, final double speed, final boolean useSensor); //Abstract method that should hold code that is supposed to be called during auto when the command is called
	
	/**
	 * This method should contains code that returns if the auto command is done
	 * 
	 * @return
	 */
	public boolean autoCommandDone(); //Abstract method that should hold code that should return true if the auto command is done
	
}
