package org.usfirst.frc.team1699.utils.drive;

import edu.wpi.first.wpilibj.SpeedController;

public class TimeControlledMotor implements Runnable{
	
	private SpeedController controller;
	private boolean running = false;
	private Thread thread;
	private Double speed;
	private double time;
	
	public TimeControlledMotor(SpeedController controller){
		this.controller = controller;
		this.time = 0;
		this.speed = 0.0;
	}
	
	public void setSpeed(double speed){
		this.speed = speed;
	}
	
	public double getSpeed(){
		return this.speed;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public void run() {
		
		// Start the controller
		controller.set(speed);
		
		// Wait for the specified amount of time
		try {
			thread.wait((long) (time * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
		// Stop
		stop();
	}
	
	public synchronized void start(){
		if(running)return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
