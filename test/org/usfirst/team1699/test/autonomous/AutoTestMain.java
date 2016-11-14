package org.usfirst.team1699.test.autonomous;

import org.junit.Test;
import org.usfirst.frc.team1699.utils.autonomous.AutoPath;
import org.usfirst.frc.team1699.utils.autonomous.AutoUtils;
import org.usfirst.team1699.test.autonomous.commands.Drive;
import org.usfirst.team1699.test.autonomous.commands.Shoot;
import org.usfirst.team1699.test.autonomous.commands.Turn;

public class AutoTestMain {
	
	@Test
	public void autonomousTest(){
		String filePath = "autoTestFiles/test.nav";
		
		Drive d = new Drive("Drive", 0);
		Turn t = new Turn("Turn", 1);
		Shoot s = new Shoot("Shoot", 2);
		
		Object[] obj = new Object[3];
		obj[0] = d;
		obj[1] = t;
		obj[2] = s;
		
		AutoPath path = new AutoPath(filePath, obj);
		path.runScript();
	}
	
	@Test
	public void arrayListFileTest(){
		String filePath = "autoTestFiles/test.nav";
		System.out.println(AutoUtils.loadFileAsArray(filePath));
	}
}
