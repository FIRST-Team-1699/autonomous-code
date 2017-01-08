//When writing JavaDocs, include team 3309
package org.usfirst.frc.team1699.utils.drive;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;

public class XboxController extends GenericHID {
	
	//Constants
	//Buttons
	public static final int BUTTON_A = 1;
	public static final int BUTTON_B = 2;
	public static final int BUTTON_X = 3;
	public static final int BUTTON_Y = 4;
	//DPad
	public static final int BUTTON_DPAD_UP = 5;
	public static final int BUTTON_DPAD_DOWN = 6;
	public static final int BUTTON_DPAD_LEFT = 7;
	public static final int BUTTON_DPAD_RIGHT = 8;
	//Middle Buttons
	public static final int BUTTON_START = 8;
	public static final int BUTTON_BACK = 7;
	public static final int BUTTON_HOME = 13;
	//Sticks
	public static final int BUTTON_LEFT_STICK = 9;
	public static final int BUTTON_RIGHT_STICK = 10;
	//Bumpers
	public static final int BUTTON_LEFT_BUMPER = 5;
	public static final int BUTTON_RIGHT_BUMPER = 6;
	
	//Axes
	public static final int AXIS_LEFT_X = 0;
	public static final int AXIS_LEFT_Y = 1;
	public static final int AXIS_LEFT_TRIGGER = 2;
	public static final int AXIS_RIGHT_TRIGGER = 3;
	public static final int AXIS_RIGHT_X = 4;
	public static final int AXIS_RIGHT_Y = 5;
	public static final int AXIS_DPAD = 6;
	
	
	@SuppressWarnings("unused")
	private int port;
	private double deadband;
	private Joystick joystick;
	
	public XboxController(int port, double deadband){
		super(port);
		this.port = port;
		this.deadband = deadband;
		joystick = new Joystick(port);
	}
	
	public boolean getA(){
		return joystick.getRawButton(3);
	}
	
	public boolean getB(){
		return joystick.getRawButton(4);
	}
	
	public boolean getXButton(){
		return joystick.getRawButton(1);
	}
	
	public boolean getYButton(){
		return joystick.getRawButton(2);
	}
	
	public double getLeftXAxis(){
		return scaleAxis(joystick.getRawAxis(AXIS_LEFT_X));
	}
	
	public double getLeftYAxis(){
		return scaleAxis(joystick.getRawAxis(AXIS_LEFT_Y));
	}
	
	public double getRightXAxis(){
		return scaleAxis(joystick.getRawAxis(AXIS_RIGHT_X));
	}
	
	public double getRightYAxis(){
		return scaleAxis(joystick.getRawAxis(AXIS_RIGHT_Y));
	}
	
	public double getRightTrigger(){
		return scaleAxis(joystick.getRawAxis(AXIS_RIGHT_TRIGGER));
	}
	
	public double getLeftTrigger(){
		return scaleAxis(joystick.getRawAxis(AXIS_LEFT_TRIGGER));
	}
	
	public boolean getStart(){
		return joystick.getRawButton(8);
	}
	
	public boolean getBack(){
		return joystick.getRawButton(7);
	}
	
	public boolean getHome(){
		return joystick.getRawButton(13);
	}
	
	public boolean getRightStickButton(){
		return joystick.getRawButton(9);
	}
	
	public boolean getLeftStickButton(){
		return joystick.getRawButton(10);
	}
	
	public boolean getRightBumper(){
		return joystick.getRawButton(BUTTON_LEFT_BUMPER);
	}
	
	public boolean getLeftBumper(){
		return joystick.getRawButton(BUTTON_RIGHT_BUMPER);
	}
	
	public double getDeadband(){
		return this.deadband;
	}
	
	public void setDeadband(int deadband){
		this.deadband = deadband;
	}
	
	public boolean getDPadUp(){
		return joystick.getPOV(0) < 45 || joystick.getPOV(0) > 325;
	}
	
	public boolean getDPadDown(){
		return joystick.getPOV(0) < 225 || joystick.getPOV(0) > 135;
	}
	
	public boolean getDPadLeft(){
		return joystick.getPOV(0) < 315 || joystick.getPOV(0) > 225;
	}
	
	public boolean getDPadRight(){
		return joystick.getPOV(0) < 135 || joystick.getPOV(0) > 245;
	}
	
	private double scaleAxis(double value){
		if(Math.abs(value) < this.deadband && Math.abs(value) > -this.deadband){
			return 0;
		}else{
			return value;
		}
	}
	
	

	@Override
	public double getX(Hand hand) {
		return joystick.getX(hand);
	}

	@Override
	public double getY(Hand hand) {
		return joystick.getY(hand);
	}

	@Override
	public double getRawAxis(int which) {
		return joystick.getRawAxis(which);
	}

	@Override
	public boolean getRawButton(int button) {
		return joystick.getRawButton(button);
	}

	@Override
	public int getPOV(int pov) {
		return joystick.getPOV(pov);
	}

	@Override
	public int getPOVCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HIDType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOutput(int outputNumber, boolean value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOutputs(int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRumble(RumbleType type, double value) {
		// TODO Auto-generated method stub
		
	}
}
