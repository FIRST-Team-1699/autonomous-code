/**
 * FIRST Team 1699
 * 
 * A timer object that actually times.
 * 
 * @author thatging3rkid, FIRST Team 1699
 * 
 */
package org.usfirst.frc.team1699.utils.command;

/**
 * A timer. It starts, stops, resets, and tells the elapsed time. Uses FPGA over OS time.
 */
@Deprecated // this class has not been updated in almost a year, and the WPILib Timer needs evaluation
public class BetterTimer extends edu.wpi.first.wpilibj.Timer {

	private Double startTime;
	private Double endTime;
	private Double timePassed;

	/**
	 * Creates a better timer that has not been initialized.
	 */
	public BetterTimer() {
		super();
		System.err.println("BetterTimer has been depreciated, please do not use!");
		this.timePassed = (double) 0;
	}

	/**
	 * Starts the timer
	 */
	@Override
	public void start() {
		this.startTime = super.getFPGATimestamp();
	}

	/**
	 * Ends the timer
	 */
	@Override
	public void stop() {
		this.endTime = super.getFPGATimestamp();
		this.timePassed = Math.abs(startTime - endTime);
	}

	/**
	 * Resets the timer
	 */
	@Override
	public void reset() {
		this.startTime = null;
		this.endTime = null;
		this.timePassed = null;
	}

	/**
	 * Gets the time between the timer starting and stopping
	 * 
	 * @return time between the timer starting and stopping
	 */
	public double getElapsed() {
		if (this.timePassed == null) {
			System.err.println("timePassed is null!");
		}
		return this.timePassed;
	}

}
