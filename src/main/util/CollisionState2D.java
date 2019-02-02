package main.util;

public class CollisionState2D {
	
	CollisionState x;
	CollisionState y;
	
	public CollisionState2D(CollisionState x, CollisionState y) {
		this.x = x;
		this.y = y;
	}
	
	public CollisionState getX() {
		return x;
	}
	
	public CollisionState getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return "x=" + x +
				", y=" + y;
	}
}
