package main.util;

public class SectionVertical {
	
	private double x;
	private double topY;
	private double bottomY;
	
	public SectionVertical(double x, double topY, double bottomY){
		this.x = x;
		this.topY = topY;
		this.bottomY = bottomY;
	}
	
	public double getX() {
		return x;
	}
	
	public double getTopY() {
		return topY;
	}
	
	public double getBottomY() {
		return bottomY;
	}
}
