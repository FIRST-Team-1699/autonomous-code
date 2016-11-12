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
public class BetterTimer extends edu.wpi.first.wpilibj.Timer {

	private Double startTime;
	private Double endTime;
	private Double timePassed;

	/**
	 * Creates a better timer that has not been initialized.
	 */
	public BetterTimer() {
		super();
		this.timePassed = (double) 0;
	}

	/**
	 * Starts the timer
	 */
	@Override
	public void start() {
		if (this.endTime == null) {
			System.err.println("User tried to start a started timer.");
		} else {
			this.startTime = super.getFPGATimestamp();
		}
	}

	/**
	 * Ends the timer
	 */
	@Override
	public void stop() {
		if (this.startTime == null) {
			System.err.println("User tried to end a finished timer.");
		} else {
			this.endTime = super.getFPGATimestamp();
		}

		this.timePassed = Math.abs(startTime - endTime);
	}

	/**
	 * Resets the timer
	 */
	@Override
	public void reset() {
		this.startTime = null;
		this.endTime = null;
	}

	/**
	 * Gets the time between the timer starting and stopping
	 * 
	 * @return time between the timer starting and stopping
	 */
	public double getElapsed() {
		if ((startTime == null) || (endTime == null)) {
			throw new NullPointerException("Bad startTime or endTime in BetterTimer");
		}
		return this.timePassed;
	}

}
