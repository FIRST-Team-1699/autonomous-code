package org.usfirst.team1699.test.motorController;

import edu.wpi.first.wpilibj.SpeedController;

public class TestController implements SpeedController{
	private boolean inverted;
	private double speed;
	private boolean disabled;

	public TestController(){
		this.inverted = false;
		this.speed = 0;
		this.disabled = false;
	}
	
	@Override
	public void pidWrite(double output) {
		
	}

	@Override
	public double get() {
		return speed;
	}

	@Override
	public void set(double speed, byte syncGroup) {
		
	}

	@Override
	public void set(double speed) {
		if(inverted){
			this.speed = speed * -1;
		}else{
			this.speed = speed;
		}
	}

	@Override
	public void setInverted(boolean isInverted) {
		inverted = isInverted;
	}

	@Override
	public boolean getInverted() {
		return inverted;
	}

	@Override
	public void disable() {
		disabled = false;
	}

	@Override
	public void stopMotor() {
		set(0);
	}
	
	public double getSpeed(){
		return this.speed;
	}

}
