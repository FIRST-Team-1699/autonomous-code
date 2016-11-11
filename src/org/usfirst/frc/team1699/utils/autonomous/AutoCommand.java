package org.usfirst.frc.team1699.utils.autonomous;

public interface AutoCommand {

	public void autoInit();
	public void runAuto(int distance, double speed);
	public boolean autoCommandDone();
}
