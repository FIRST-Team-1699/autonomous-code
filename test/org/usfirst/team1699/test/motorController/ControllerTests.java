package org.usfirst.team1699.test.motorController;

import org.junit.Test;
import org.usfirst.frc.team1699.utils.command.BetterTimer;
import org.usfirst.frc.team1699.utils.drive.TimeControlledMotor;

public class ControllerTests {

	@Test
	public void threadedControllerTest(){
		TestController tst1 = new TestController();
		BetterTimer time = new BetterTimer();
		TimeControlledMotor tstTime1 = new TimeControlledMotor(tst1, time, .5, 10);
	}
}
