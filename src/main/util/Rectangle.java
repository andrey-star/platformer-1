package main.util;

public class Rectangle {

	private double x1, y1, x2, y2;
	private double width, height;
	
	public Rectangle(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		width = x2 - x1;
		height = y2 - y1;
	}
	
	public double getX1() {
		return x1;
	}
	
	public double getY1() {
		return y1;
	}
	
	public double getX2() {
		return x2;
	}
	
	public double getY2() {
		return y2;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public double getTop() {
		return y1;
	}
	
	public double getRight() {
		return x2;
	}
	
	public double getBottom() {
		return y2;
	}
	
	public double getLeft() {
		return x1;
	}
}
