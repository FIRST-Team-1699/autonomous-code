/**
 * Class that generates a path for auto
 * 
 * @author squirlemaster42
 */
package org.usfirst.frc.team1699.utils.autonomous;

public class AutoPath {
	
	private String path;
	private String[] fileAsString;
	private int numLines;
	
	public AutoPath(String path, int width){
		this.path = path;
		this.numLines = width;
		fileAsString = AutoUtils.loadFileAsArray(path, numLines);
	}
	
	public String getPath() {
		return path;
	}
 
	public String[] getFileAsString() {
		return fileAsString;
	}
	
	public void setNumLines(int numLines){
		this.numLines = numLines;
	}

	public int getWidth() {
		return numLines;
	}
}