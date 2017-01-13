package org.usfirst.team1699.test.motorController;

import org.junit.Test;
import org.usfirst.frc.team1699.utils.drive.TimeControlledMotor;

public class ControllerTests {

	@Test
	public void threadedControllerTest() throws InterruptedException{
		TestController tst1 = new TestController(); //New test controller instance
		
		TimeControlledMotor tstTime1 = new TimeControlledMotor(tst1); //New timed controller instance, passed test controller
		System.out.println("-1 " + tstTime1.getCurrentSpeed()); //Prints current speed
		tst1.set(.5); //Sets speed to 0.5
		System.out.println("0 " + tstTime1.getCurrentSpeed()); //Prints current speed
		tstTime1.setTime(5000); //Sets time delay to 5000
		tstTime1.setSpeed(1); //Sets speed to 1.0
		tstTime1.start(); //Starts motor
		for(int i = 0; i < 75; i++){ //Loop for evaluating motor over time
			System.out.println(i + " " + tstTime1.getCurrentSpeed());
			Thread.sleep(100);
		}
	}
}
