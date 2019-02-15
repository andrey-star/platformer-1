package main.util;

public class Vector {

	private double x;
	private double y;
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public Vector copyOf() {
		return new Vector(x, y);
	}
	
	@Override
	public String toString() {
		return "x=" + x +
				", y=" + y;
	}
	
	static public Vector zero() {
		return new Vector(0, 0);
	}
}
