package org.usfirst.frc.team1699.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import org.apache.commons.io.output.TeeOutputStream;

public class Robot extends IterativeRobot {
    // Autonomous Chooser strings
	final String defaultAuto = "1699-autoDef";
    final String auto1S = "1699-auto1";
    final String auto2S = "1699-auto2";
    final String auto3S = "1699-auto3";
    final String auto4S = "1699-auto4";
    final String auto5S = "1699-auto5";
    final String auto6S = "1699-auto6";
    final String auto7S = "1699-auto7";
    final String auto8S = "1699-auto8";
    String autoSelected;
    SendableChooser chooser;
    
    // Logging decelerations
    File logf;
    Runtime runtime;
    
    //Camera Server
    CameraServer cam;
    
    //Joysticks
    Joystick extreme3d;
    Joystick attack3;
    Joystick xbox;
    
    //Motor Control
    //Drive Motors
    CANTalon rightDrive1;
    CANTalon rightDrive2;
    CANTalon leftDrive1;
    CANTalon leftDrive2;
    
    // Robot Drive for easier motor control
    RobotDrive rDrive;
    
    // Encoders
    Encoder frontLeftE;
    Encoder frontRightE;
    
    //Shooter Motors
    VictorSP leftShoot;
    VictorSP rightShoot;
    VictorSP bottomShoot;
    VictorSP topShoot;
    
    //VictorSP leftPickup;
    VictorSP rightPickup;
    
    // Open config files
    iniReader teleopIni;
    
    //Moar things
    // Joystick speed after "gearing"
    double xSpeed1;
    double xSpeed2;
    
    // Current "gear"
    double gearRatio;
    
    //Ini vars
    double gear1;
    double gear2;
    double gear3;
      
    double autoSpeed;
    
    boolean leftNotHeld;
    boolean rightNotHeld;
    
    // Current "gear" number (current options: 1-3) (initializes at 2)
    int cGear = 2; 
    
    //Joystick bindings
    // ALL should be finals
    //Shooter
    final int TRIGGER_AXIS_1 = 3;
    final int TRIGGER_AXIS_2 = 2;
    final int X_BUTTON = 1;
    final int Y_BUTTON = 2;
    final int A_BUTTON = 3;
    final int B_BUTTON = 4;
    
    //Ball pickup
    final int PICK_UP = 3;
    final int DROP_BALL = 2;
    final int XBOX_UP = 7;
    final int XBOX_DOWN = 8;
    
    //Camera control
    final int CAM_1 = 5;
    final int CAM_2 = 6;
    
    
    
    public void robotInit() { 
    	// Logging start
    	this.loggingInit();
    	
    	// iniReader
    	teleopIni = new iniReader("1699-config.ini");
    	gear1 = teleopIni.getValue("gear1");
        gear2 = teleopIni.getValue("gear2");
        gear3 = teleopIni.getValue("gear3");
    	
    	// Autonomous chooser
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("Autonomous 1", auto1S);
        chooser.addObject("Autonomous 2", auto2S);
        chooser.addObject("Autonomous 3", auto3S);
        chooser.addObject("Autonomous 4", auto4S);
        chooser.addObject("Autonomous 5", auto5S);
        chooser.addObject("Autonomous 6", auto6S);
        chooser.addObject("Autonomous 7", auto7S);
        chooser.addObject("Autonomous 8", auto8S);
        SmartDashboard.putData("Auto Chooser", chooser);
        
        //Human Controls
        extreme3d = new Joystick(0);
        attack3 = new Joystick(1);
        xbox = new Joystick(2);
        
        //Motor Control
        //Drive Motors
        rightDrive1 = new CANTalon(10);
        rightDrive2 = new CANTalon(11);
        leftDrive1 = new CANTalon(12);
        leftDrive2 = new CANTalon(13);
        
        //Shooter Motors
        leftShoot = new VictorSP(1);
        topShoot = new VictorSP(2);
        bottomShoot = new VictorSP(3);
        rightShoot = new VictorSP(4);
        
        //Ball pickup
        //leftPickup = new VictorSP(5);
        rightPickup = new VictorSP(6);
        
        //Drive
        rDrive = new RobotDrive(leftDrive1, leftDrive2, rightDrive1, rightDrive2);        
        
        // More Encoders
        frontLeftE = new Encoder(1, 2, true, Encoder.EncodingType.k4X);
        frontRightE = new Encoder(3, 4, true, Encoder.EncodingType.k4X);
        frontLeftE.setDistancePerPulse(12.0);
        frontRightE.setDistancePerPulse(12.0);
        
        // Joystick booleans
        leftNotHeld = false;
        rightNotHeld = false;
        
        //Camera
        cam = CameraServer.getInstance();
        cam.setQuality(50);
        cam.startAutomaticCapture("cam0");  
    }
    
    // Starts logging, should be called first thing
    // apache-commons-io needs to be installed on the RIO and linked on the local PC
    public void loggingInit()
    {
        // More initializers, please. 
        runtime = Runtime.getRuntime();
        @SuppressWarnings("unused") // Actually used tho
		Process p;
        boolean logfcont = true;
        Integer logfcount = new Integer(0);
        
        // Looks for non-existent log file
        while (logfcont)
        {
        	logf = new File("/home/lvuser/1699-logs/log-" + logfcount + ".log");
        	if (logf.exists()) 
        	{
        		logfcont = true;
        		logfcount += 1;
        	}
        	else {logfcont = false;}
        }
        
        // Renames current log file to the last number in the list
        try {p = runtime.exec("mv /home/lvuser/1699-logs/log-current.log /home/lvuser/1699-logs/log-" + logfcount.toString() + ".log");}
        catch (Exception e) {e.printStackTrace();}
        
        // Prepares for new log
        logf = new File("/home/lvuser/1699-logs/log-current.log");
        try {logf.createNewFile();} 
        catch (IOException e) {System.out.println("BIG ERROR\n\n\n\n\n\n");e.printStackTrace();}
        try
        {
        	// Makes new output stream in log-current.log
        	FileOutputStream fos = new FileOutputStream(logf);
        	
        	// Makes Dual-output, called Tee for some reason.
        	TeeOutputStream tos = new TeeOutputStream(System.out, fos);
        	
        	// Makes a PrintStream out of the new, dual-output Tee 
        	PrintStream ps = new PrintStream(tos);
        	
        	// Sets the above PrintStream to System.out
        	System.setOut(ps);
        	System.out.println("Success setting Tee Output Stream.");
        } 
        catch (FileNotFoundException e) {e.printStackTrace();}
        catch (Exception e) {e.printStackTrace();}
    }	
               
    
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
		System.out.println("Auto selected: " + autoSelected);
    }

    public void autonomousPeriodic() 
    {
    	// Get commands from ini file
    	iniReader autoCommands = new iniReader(autoSelected + ".ini");
    	@SuppressWarnings("rawtypes") // Java doesn't like it when multiple types in an ArrayList, but I do
		ArrayList commands = autoCommands.getFile(); 
    	int count1 = 0;    	
    	
    	// Read through ArrayList
    	while (count1 != commands.size())
    	{
    		// Looks for key words in the ini
    		@SuppressWarnings("rawtypes") 
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
	    				rDrive.arcadeDrive(autoSpeed, 0);
	    				traveled = (double) ((frontLeftE.getDistance() + frontRightE.getDistance()) / 2);
	    			}
	    			rDrive.arcadeDrive(0, 0); 
	    			frontLeftE.reset();
	    			frontRightE.reset();
	    		}
	    		
	    		// Rotate command
	    		case "rotate":
	    		{
	    			double traveled = 0;
	    			
	    			// Check if positive
	    			if (value > 0)
	    			{
	    				// If so, go left
	    				while (traveled < Math.abs(value))
	    				{
	    					rDrive.tankDrive(autoSpeed, 0);
		    				traveled = (double) frontLeftE.getDistance();
	    				}
	    			}
	    			
	    			// Check if negative
	    			else if (value < 0)
	    			{
	    				// If so, go right
	    				while (traveled < Math.abs(value))
		    			{
		    				rDrive.tankDrive(0, autoSpeed);
			    			traveled = (double) frontRightE.getDistance();
		    			}
	    			}
	    			
	    			// Stop spinning
	    			rDrive.tankDrive(0, 0);
	    		}
	    		
	    		// Sleep command
	    		case "sleep":
	    		{
	    			try {Thread.sleep((long) (value * 1000));} 
	    			catch (InterruptedException e) {e.printStackTrace();}
	    		}
	    		
	    		// Shoot command
	    		case "shoot":
	    		{
	    			if (value == 1.0)
	    			{
	    				// Start shooter motors
	    				leftShoot.set(.7);
	    				rightShoot.set(-.7);
	    				topShoot.set(-.7);
	    				bottomShoot.set(-.7);
	    				
	    				// Spin up wait
	    				try {Thread.sleep(750);} 
	    				catch (InterruptedException e) {e.printStackTrace();}
	    				
	    				// Send ball into shooter motors
	    				rightPickup.set(.6);
	    				try {Thread.sleep(2000);} 
	    				catch (InterruptedException e) {e.printStackTrace();}
	    				
	    				// Stop shooter motors
	    				leftShoot.set(0);
	    				rightShoot.set(0);
	    				topShoot.set(0);
	    				bottomShoot.set(0);
	    			}
	    		}
	    		
	    		default: {System.out.println("Command not found at line: " + count1 + " in file " + autoSelected + ".ini");}	
    		}
    		
    		// A safety that will stop the robot after a command is executed
    		rDrive.arcadeDrive(0, 0);
    		
    		leftShoot.set(0);
			rightShoot.set(0);
			topShoot.set(0);
			bottomShoot.set(0);
    		
    		// Continue the loop!
    		count1 += 1;
    	}
    }

    public void teleopPeriodic() {
//    	Joystick 1 (extreme3d):
//    	    x-axis: drive right side
//    	Joystick 2 (attack3):
//    	    x-axis: drive left side
//    	    button 3: pickup
//    	Driver 2:
//    	Joystick 1 (xbox):
//    	    axis 3: shooter up/down
//    	    button 1-4: shooter speeds
//    	    button 5/6: camera switch
    	
    	//gearing should go near robotDrive call
    	
    	//Changed from raw axis must test
    	xSpeed1 = -1 * extreme3d.getRawAxis(1) * gearRatio;
    	xSpeed2 = -1 * attack3.getRawAxis(1) * gearRatio;
    	
    	rDrive.tankDrive(xSpeed2, xSpeed1); // check call and logic, did on the fly 
    	
    	if(attack3.getRawButton(PICK_UP) || xbox.getRawButton(XBOX_UP)){
    		//pickup
    		// all motors (except for drive) (or anything that we will never change) should be revived from the ini
    		// call to get value example below
    		//leftPickup.set(teleopIni.getValue("leftPickupSpeed"));
    		rightPickup.set(.6);
    	}else if (attack3.getRawButton(DROP_BALL) || xbox.getRawButton(XBOX_DOWN)){
    		rightPickup.set(-.6);
    	}else{
    		//set all 0
    		//leftPickup.set(0);
    		rightPickup.set(0);
    	}
    	
    	//Shoot with different speeds
    	if(xbox.getRawButton(X_BUTTON)){
    		//shooter speed 1
    		leftShoot.set(.2);
    		rightShoot.set(-.2);
    		topShoot.set(-.2);
    		bottomShoot.set(-.2);
    	}else if(xbox.getRawButton(Y_BUTTON)){
    		//shooter speed 2
    		leftShoot.set(.7);
    		rightShoot.set(-.7);
    		topShoot.set(-.7);
    		bottomShoot.set(-.7);
    	}else if(xbox.getRawButton(A_BUTTON)){
    		//shooter speed 3
    		leftShoot.set(.9);
    		rightShoot.set(-.9);
    		topShoot.set(-.9); 
    		bottomShoot.set(-.9);
    	}else if(xbox.getRawButton(B_BUTTON)){
    		//shooter speed 4
    		leftShoot.set(1);
    		rightShoot.set(-1);
    		topShoot.set(-1);
    		bottomShoot.set(-1);
    	}else{
    		//set all 0
    		leftShoot.set(0);
    		rightShoot.set(0);
    		topShoot.set(0);
    		bottomShoot.set(0); 
    	}
    	
    	//Camera control
    	if(xbox.getRawButton(CAM_1)){
    		//camera 1
    	}else if(xbox.getRawButton(CAM_2)){
    		//camera 2
    	}
    	
    	//Shooter up and down
    	if(xbox.getRawAxis(TRIGGER_AXIS_1) == 1){
    		//shooter up
    		//shootAdjust.set(.2);
    		//rightPickup.set(.6);
    	}else if(xbox.getRawAxis(TRIGGER_AXIS_2) == 1){
    		//shooter down
    		//shootAdjust.set(-.2);
    		//rightPickup.set(-.6);
    	}else{
    		//stop movement
    		//shootAdjust.set(0);
    		//rightPickup.set(0);
    	}
    	
    	//Gearing control
    	if(extreme3d.getTrigger() && !rightNotHeld)
    	{
    		//gear up
    		if (cGear == 1 && !rightNotHeld)
    		{
    			cGear = 2;
    			rightNotHeld = true;
    		}
    		else if (cGear == 2 && !rightNotHeld)
    		{
    			cGear = 3;
    			rightNotHeld = true;
    		}
    	}
    	else if(attack3.getTrigger() && !leftNotHeld)
    	{
    		//gear down
    		if (cGear == 2 && !leftNotHeld)
    		{
    			cGear = 1;
    			leftNotHeld = true;
    		}
    		else if (cGear == 3 && !leftNotHeld)
    		{
    			cGear = 2;
    			leftNotHeld = true;
    		}
    	}
    	
    	
    	if(!attack3.getTrigger() && leftNotHeld){
    		leftNotHeld = false;
    	}else if(!extreme3d.getTrigger() && rightNotHeld){
    		rightNotHeld = false;
    	}
    	
    	if (cGear == 1) {gearRatio = gear1;}
    	else if (cGear == 2) {gearRatio = gear2;}
    	else if (cGear == 3) {gearRatio = gear3;}
    	else {gearRatio = 0.0;}
    	
    	System.out.println(gearRatio);
    }
    
    public void disabledInit()
    {
    }
    
    public void testPeriodic() {
    
    }
    
}