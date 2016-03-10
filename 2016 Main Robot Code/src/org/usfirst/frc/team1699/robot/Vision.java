package org.usfirst.frc.team1699.robot;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Vision {

	//Image variable
	private BufferedImage image;
	
	//Array for image
	private Boolean[][] imgArr;
	private double[][] colorArr;
	
	public Vision(BufferedImage image){
		this.image = image;
	}
	
	public Boolean[][] makeColorArray(int red, int green, int blue){
		//Sets array size
		imgArr = new Boolean[image.getHeight()][image.getWidth()];
		
		//Runs through image pixel by pixel
		 for (int y = 0; y < image.getHeight(); y++) {
             for (int x = 0; x < image.getWidth(); x++) {
            	             	 
            	 //Variables to help find colors
            	 int c = image.getRGB(x, y);
            	 Color color = new Color(c);
            	 
            	 //Looks for a pixel of a certain color in the image
            	 if (color.getRed() == red && color.getGreen() == green && color.getBlue() == blue){
            		 //If the color is found that spot in the array is set to true
            		 imgArr[x][y] = true;
            	 }else{
            		//If the color is not found that spot in the array is set to false
            		 imgArr[x][y] = false;
            	 }
             }
		 }
		 
		 return imgArr;
	}
	
	public double[][] colorString(){
		//Sets array size
		colorArr = new double[image.getHeight()][image.getWidth()];
		
		//Runs through image pixel by pixel
		 for (int y = 0; y < image.getHeight(); y++) {
			 for (int x = 0; x < image.getWidth(); x++) {
				 int c = image.getRGB(x, y);
			   	 Color color = new Color(c);
			   	 	
				 colorArr[x][y] = color.getRGB();
			 }
		 }
		
		return colorArr;
	}
	
	//Getters and setters
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public Boolean[][] getImgArr() {
		return imgArr;
	}
	
	public double[][] colorArr(){
		return colorArr;
	}
}