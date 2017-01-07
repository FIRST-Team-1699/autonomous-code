package org.usfirst.team1699.test.motorController;

import org.junit.Test;
import org.usfirst.frc.team1699.utils.command.BetterTimer;
import org.usfirst.frc.team1699.utils.drive.TimeControlledMotor;

public class ControllerTests {

	@Test
	public void threadedControllerTest() throws InterruptedException{
		TestController tst1 = new TestController();
		
		TimeControlledMotor tstTime1 = new TimeControlledMotor(tst1);
		tstTime1.setTime(100);
		tstTime1.setSpeed(1);
		tstTime1.start();
		for(int i = 0; i < 10000; i++){
			System.out.println(i + " " + tstTime1.getSpeed());
			Thread.sleep(10);
		}
	}
}
