package main.util;

public class Rectangle {

	private double x1, y1;
	private double width, height;
	
	public Rectangle(double x1, double y1, double width, double height) {
		this.x1 = x1;
		this.y1 = y1;
		this.width = width;
		this.height = height;
	}
	
	public Rectangle(Vector pos, double width, double height) {
		this(pos.getX(), pos.getY(), width, height);
	}
	
	public double getX1() {
		return x1;
	}
	
	public double getY1() {
		return y1;
	}
	
	public double getX2() {
		return x1 + width;
	}
	
	public double getY2() {
		return y1 + height;
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
		return x1 + width;
	}
	
	public double getBottom() {
		return y1 + height;
	}
	
	public double getLeft() {
		return x1;
	}
}
