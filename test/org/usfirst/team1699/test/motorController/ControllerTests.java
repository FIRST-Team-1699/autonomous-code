package org.usfirst.team1699.test.motorController;

import org.junit.Test;
import org.usfirst.frc.team1699.utils.drive.TimeControlledMotor;

public class ControllerTests {

	@Test
	public void threadedControllerTest() throws InterruptedException{
		TestController tst1 = new TestController();
		
		TimeControlledMotor tstTime1 = new TimeControlledMotor(tst1);
		System.out.println("-1 " + tstTime1.getCurrentSpeed());
		tst1.set(.5);
		System.out.println("0 " + tstTime1.getCurrentSpeed());
		tstTime1.setTime(5000);
		tstTime1.setSpeed(1);
		tstTime1.start();
		for(int i = 0; i < 75; i++){
			System.out.println(i + " " + tstTime1.getCurrentSpeed());
			Thread.sleep(100);
		}
	}
}
