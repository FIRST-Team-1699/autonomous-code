//When writing JavaDocs, include team 3309
package org.usfirst.frc.team1699.utils.drive;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;

public class XboxController extends GenericHID {
	
	//Constants
	//Buttons
	private static final int BUTTON_A = 1;
	private static final int BUTTON_B = 2;
	private static final int BUTTON_X = 3;
	private static final int BUTTON_Y = 4;
	//DPad
	private static final int BUTTON_DPAD_UP = 5;
	private static final int BUTTON_DPAD_DOWN = 6;
	private static final int BUTTON_DPAD_LEFT = 7;
	private static final int BUTTON_DPAD_RIGHT = 8;
	//Middle Buttons
	private static final int BUTTON_START = 8;
	private static final int BUTTON_BACK = 7;
	private static final int BUTTON_HOME = 13;
	//Sticks
	private static final int BUTTON_LEFT_STICK = 9;
	private static final int BUTTON_RIGHT_STICK = 10;
	//Bumpers
	private static final int BUTTON_LEFT_BUMPER = 5;
	private static final int BUTTON_RIGHT_BUMPER = 6;
	
	//Axes
	private static final int AXIS_LEFT_X = 0;
	private static final int AXIS_LEFT_Y = 1;
	private static final int AXIS_LEFT_TRIGGER = 2;
	private static final int AXIS_RIGHT_TRIGGER = 3;
	private static final int AXIS_RIGHT_X = 4;
	private static final int AXIS_RIGHT_Y = 5;
	private static final int AXIS_DPAD = 6;
	
	
	private int port;
	private double deadband;
	private Joystick joystick;
	
	public XboxController(int port, double deadband){
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
		if(Math.abs(value) < this.deadband && Math.abs(value) > -0.05){
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
	public double getZ(Hand hand) {
		return joystick.getZ(hand);
	}

	@Override
	public double getTwist() {
		return joystick.getTwist();
	}

	@Override
	public double getThrottle() {
		return joystick.getThrottle();
	}

	@Override
	public double getRawAxis(int which) {
		return joystick.getRawAxis(which);
	}

	@Override
	public boolean getTrigger(Hand hand) {
		return joystick.getTrigger();
	}

	@Override
	public boolean getTop(Hand hand) {
		return joystick.getTop(hand);
	}

	@Override
	public boolean getBumper(Hand hand) {
		return joystick.getBumper(hand);
	}

	@Override
	public boolean getRawButton(int button) {
		return joystick.getRawButton(button);
	}

	@Override
	public int getPOV(int pov) {
		return joystick.getPOV(pov);
	}
}
