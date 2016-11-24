package org.usfirst.frc.team1699.utils.drive;

import org.usfirst.frc.team1699.utils.command.BetterTimer;

import edu.wpi.first.wpilibj.SpeedController;

public class DriveUtils {
	
	//Needs to be looked at
	public static void driveForTime(BetterTimer time, SpeedController motCont, double timeAmt, double speed){ //Used to tell a motor to run for a specified amount of time
		time.start();
		if(time.getElapsed() >= timeAmt){
			motCont.set(speed);
		}else{		
			motCont.set(0);
		}
	}
}
