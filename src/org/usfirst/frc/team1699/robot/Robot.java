package org.usfirst.frc.team1699.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String auto1 = "Autonomous 1";
    final String auto2 = "Autonomous 2";
    final String auto3 = "Autonomous 3";
    String autoSelected;
    SendableChooser chooser;
	
    // The edits start here
    Encoder frontLeftE = new Encoder(0, 1); // more data needs to be added
    Encoder frontRightE = new Encoder(2, 3); // ^^^
    
    RobotDrive drive = new RobotDrive(10, 11, 12, 13);
    
    iniReader autoIni;
    iniReader configIni = new iniReader("1699-config.ini");
    
    double autoDriveSpeed = configIni.getValue("autoDriveSpeed");
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Auto0", defaultAuto);
        chooser.addObject("Auto1", auto1);
        chooser.addObject("Auto2", auto2);
        chooser.addObject("Auto3", auto3);
        SmartDashboard.putData("Auto Choices", chooser);
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit()
    {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
		if (autoSelected.equals("Auto0")) {autoIni = new iniReader("1699-autonomous-def.ini");}
		if (autoSelected.equals("Auto1")) {autoIni = new iniReader("1699-autonomous-1.ini");}
		if (autoSelected.equals("Auto2")) {autoIni = new iniReader("1699-autonomous-2.ini");}
		if (autoSelected.equals("Auto3")) {autoIni = new iniReader("1699-autonomous-3.ini");}
    }

    // Called during Auto
    @SuppressWarnings("rawtypes")
	public void autonomousPeriodic()
    {
    	int count1 = 0;
    	ArrayList commands = new ArrayList();
    	
    	
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
	    			double traveled;
	    			
	    			while (traveled > length)
	    			{
	    				drive.arcadeDrive(autoDriveSpeed, 0);
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
	    		
	    		default:
	    		{
	    			System.out.println("Unknown command at line: " + count1);
	    		}	
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


    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
