package org.usfirst.team1699.test.autonomous;

import org.junit.Test;
import org.usfirst.frc.team1699.utils.autonomous.AutoPath;

public class AutoTestMain {
	
	@Test
	public void autonomousTest(){
		String filePath = "/test.nav";
		AutoPath path = new AutoPath(null, 0, null);
		path.callCommandFromString(null);
	}
}
