package com.team1699.test.auto;

import org.usfirst.frc.team1699.utils.autonomous.AutoCommand;
import org.usfirst.frc.team1699.utils.command.Command;

public class Turn extends Command implements AutoCommand{
	
	public Turn(final String name, final int id) {
		super(name, id);
	}

	@Override
	public void runAuto(final double distance, final double speed, final boolean useSensor) {
		System.out.println("Distance: " + distance + " Speed: " + speed + " Use Sensor: " + useSensor);
	}

	@Override
	public boolean autoCommandDone() {
		return false;
	}

	@Override
	public void init() {
		
	}

	@Override
	public void run() {
		
	}

	@Override
	public void outputToDashboard() {
		
	}

	@Override
	public void zeroAllSensors() {
		
	}
}
