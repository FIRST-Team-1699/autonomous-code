package org.usfirst.team1699.test.autonomous.commands;

public class Drive extends org.usfirst.frc.team1699.utils.command.Command{

	public Drive(String name, int id) {
		super(name, id);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void run() {
		
	}
	
	@Override
	public void runAuto(int distance, double speed) {
		System.out.println(distance + " " + speed);
	}

	@Override
	public boolean autoCommandDone() {
		return false;
	}

	@Override
	public void outputToDashboard() {
		
	}

	@Override
	public void zeroAllSensors() {
		
	}

}
