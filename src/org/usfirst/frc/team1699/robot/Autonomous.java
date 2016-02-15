package org.usfirst.frc.team1699.robot;

import java.util.ArrayList;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;

public class Autonomous
{
	// Initialization
	double autoSpeed;
	RobotDrive drive;
	Encoder frontLeftE;
	Encoder frontRightE;
		
	public Autonomous(RobotDrive idrive, Encoder ifrontLeftE, Encoder ifrontRightE, double iautoSpeed)
	{
		this.drive = idrive;
		this.frontLeftE = ifrontLeftE;
		this.frontRightE = ifrontRightE;
		this.autoSpeed = iautoSpeed;
	}
	
	@SuppressWarnings("rawtypes")
	public void AutoRun(ArrayList commands)
	{
		int count1 = 0;    	
    	
    	// Read through ArrayList
    	while (count1 != commands.size())
    	{
    		// Looks for key words in the ini
    		ArrayList ref = (ArrayList) commands.get(count1);
    		Double value = (Double) ref.get(1);
    		String keyword = ((String) (ref.get(0))).toLowerCase();
    		
    		// Make a case for all possible words
    		switch (keyword)
    		{
	    		// Drive command
    			case "drive":
	    		{
	    			double length = (double) value;
	    			double traveled = 0;
	    			
	    			while (traveled < length)
	    			{
	    				drive.arcadeDrive(autoSpeed, 0);
	    				traveled = (double) ((frontLeftE.getDistance() + frontRightE.getDistance()) / 2);
	    			}
	    			drive.arcadeDrive(0, 0); 
	    			frontLeftE.reset();
	    			frontRightE.reset();
	    		}
	    		
	    		// Rotate command
	    		case "rotate":
	    		{
	    			double turn = (double) value;
	    			drive.arcadeDrive(0, turn);
	    		}
	    		
	    		// Sleep command
	    		case "sleep":
	    		{
	    			try {Thread.sleep((long) (value * 1000));} 
	    			catch (InterruptedException e) {e.printStackTrace();}
	    		}
	    		
	    		// setAngle command
	    		case "setAngle":
	    		{
	    			// insert reference to shooter adjustment code
	    		}
	    		
	    		// Shoot command
	    		case "shoot":
	    		{
	    			// insert reference to shooter code
	    		}
	    		
	    		default: {this.messageMaker("Unknown command at line: " + count1);}	
    		}
    		
    		// A safety that will stop the robot after a command is executed
    		drive.arcadeDrive(0, 0);
    		/*
    		 * Insert shooter code stop here.
    		 */
    		
    		// Continue the loop!
    		count1 += 1;
    	}
	}
	
    // A quick method to make printing clean boxes around important info
    // Used to make info more visible in console
	// Taken straight from ini-Reader
    private void messageMaker(String output)
    {
    	// Time to make sure people can make sense of this after I leave...
    	System.out.println("|------------------------------------------------------|");
    	System.out.println("| Team 1699 Autonomous                                 |");
  		
   		// Available characters for print
  		// "Team 1699 iniReader                                 " should match printRoom
   		final int printRoom = 52;
    		
   		// Some variables
   		String printed;
   		String notPrinted = output.substring(0, output.length());
   		while(notPrinted.length() != 0)
    	{
    		// Checks if it can be printed on one line
    		if (notPrinted.length() <= printRoom)
    		{
    			System.out.print("| " + notPrinted);
    			for (int count1 = notPrinted.length(); count1 != printRoom; count1 += 1){System.out.print(" ");}
    			System.out.print(" |\n");
    			break;
    		}
    		
    		// Break up lines :O
    		else
    		{
    			printed = "| " + notPrinted.substring(0, printRoom) + " |";
    			notPrinted = notPrinted.substring(47, notPrinted.length());
    			System.out.println(printed);
    		}
    	}	
    	// Closer.
    	System.out.println("|------------------------------------------------------|");		
    }
	
}