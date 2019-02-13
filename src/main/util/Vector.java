package main.util;

public class Vector {

	private double x;
	private double y;
	public static final Vector ZERO = new Vector(0, 0);
	public static final Vector ONE = new Vector(1, 1);
	
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
}
