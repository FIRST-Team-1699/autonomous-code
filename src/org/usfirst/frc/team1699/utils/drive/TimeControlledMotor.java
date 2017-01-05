package org.usfirst.frc.team1699.utils.drive;

import org.usfirst.frc.team1699.utils.command.BetterTimer;

import edu.wpi.first.wpilibj.SpeedController;

public class TimeControlledMotor implements Runnable{
	
	private SpeedController controller;
	private BetterTimer timer;
	private boolean running = false;
	private Thread thread;
	private Double speed;
	private double time;
	
	public TimeControlledMotor(SpeedController controller, BetterTimer timer){
		this.controller = controller;
		this.timer = timer;
		this.time = 0;
		this.speed = 0.0;
	}
	
	public void setSpeed(double speed){
		this.speed = speed;
	}
	
	public double getSpeed(){
		return this.speed;
	}
	
	public BetterTimer getTimer() {
		return timer;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public void run() {
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		controller.set(speed);
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				if(this.timer.get() == this.time){
					running = false;
				}
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000){
				System.out.println(ticks);
				ticks = 0;
				timer = 0;
			}
			
		} 
		
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
