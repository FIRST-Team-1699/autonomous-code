package org.usfirst.team1699.test.motorController;

import org.junit.Test;
import org.usfirst.frc.team1699.utils.command.BetterTimer;
import org.usfirst.frc.team1699.utils.drive.TimeControlledMotor;

public class ControllerTests {

	@Test
	public void threadedControllerTest(){
		TestController tst1 = new TestController();
		
		TimeControlledMotor tstTime1 = new TimeControlledMotor(tst1);
		tstTime1.start();
		System.out.println(tstTime1.getSpeed());
	}
}
