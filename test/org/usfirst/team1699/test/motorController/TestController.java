/**
 * This is a class that is used when a test motor controller is needed
 * 
 * @author squirlemaster42
 */
package org.usfirst.team1699.test.motorController;

import edu.wpi.first.wpilibj.SpeedController;

public class TestController implements SpeedController{
	private boolean inverted;
	private double speed;

	/**
	 * Constructor
	 */
	public TestController(){
		this.inverted = false;
		this.speed = 0;
	}
	
	/**
	 * Unused
	 * 
	 * @param output
	 */
	@Override
	public void pidWrite(double output) {
		//Unused currently
	}

	/**
	 * Returns the speed
	 * 
	 * @return speed
	 */
	@Override
	public double get() {
		return speed; //Returns the current speed
	}

	/**
	 * Sets the speed
	 * 
	 * @param speed
	 */
	@Override
	public void set(double speed) {
		if(inverted){
			this.speed = speed * -1;
		}else{
			this.speed = speed;
		}
	}

	/**
	 * Sets if the motor is inverted
	 * 
	 * @param isInverted
	 */
	@Override
	public void setInverted(boolean isInverted) {
		inverted = isInverted;
	}

	/**
	 * Returns if the motor is inverted
	 * 
	 * @return inverted
	 */
	@Override
	public boolean getInverted() {
		return inverted;
	}

	/**
	 * Sets the speed to zero
	 */
	@Override
	public void disable() {
		set(0);
	}

	/**
	 * Sets the speed of the motor to zero
	 */
	@Override
	public void stopMotor() {
		set(0);
	}
	
	/**
	 * Returns the speed
	 * 
	 * @return speed
	 */
	public double getSpeed(){
		return this.speed;
	}

}
