package org.usfirst.frc.team1699.utils.drive;

import org.usfirst.frc.team1699.utils.command.BetterTimer;

import edu.wpi.first.wpilibj.SpeedController;

public class DriveUtils {
	
	public static void driveForTime(SpeedController motCont, double timeAmt, double speed){ //Used to tell a motor to run for a specified amount of time
		BetterTimer time = new BetterTimer();
		time.start();
		do{
			motCont.set(speed);
		}while(time.getElapsed() < timeAmt);
		
		motCont.set(0);
	}
}
