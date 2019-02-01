package main.util;

public class SectionHorizontal {
	
	private double y;
	private double rightX;
	private double leftX;
	
	public SectionHorizontal (double y, double rightX, double leftX){
		this.y = y;
		this.rightX= rightX;
		this.leftX = leftX;
	}
	
	public double getY() {
		return y;
	}
	
	public double getRightX() {
		return rightX;
	}
	
	public double getLeftX() {
		return leftX;
	}
}
