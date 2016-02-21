package org.usfirst.frc.team1699.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
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
    String autoSelected;
	
    // The edits start here
    Encoder frontLeftE; // more data needs to be added
    Encoder frontRightE; // ^^^
    
    CANTalon rightDrive1 = new CANTalon(10);
    CANTalon rightDrive2 = new CANTalon(11);
    CANTalon leftDrive1 = new CANTalon(12);
    CANTalon leftDrive2 = new CANTalon(13);    
    
    RobotDrive drive = new RobotDrive(leftDrive1, leftDrive2, rightDrive1, rightDrive2); 
    
    iniReader autoIni;
    iniReader configIni = new iniReader("1699-config.ini");
    
    
    //double autoDriveSpeed = configIni.getValue("autoDriveSpeed");
    double autoDriveSpeed = .4;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	frontLeftE = new Encoder(1, 2, false, Encoder.EncodingType.k4X);
    	frontRightE = new Encoder(3, 4, false, Encoder.EncodingType.k4X);
    	
    	frontLeftE.reset();
    	frontRightE.reset();
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
    	
    }
    // Called during Auto
	public void autonomousPeriodic()
    {
    	// call Autonomous here.
    }


    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	System.out.println("Left");
        System.out.println(frontLeftE.getDistance());
        System.out.println("Right");
        System.out.println(frontRightE.getDistance());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
