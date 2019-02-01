package main.util;

import main.prefabs.CollidableGameObject;

public class BoxCollider {

	private Rectangle collider;
	
	public BoxCollider(final Rectangle collider) {
		this.collider = collider;
	}
	
	public BoxCollider(Vector pos, double width, double height) {
		collider = new Rectangle(pos, width, height);
	}
	
	public Rectangle getRectangle() {
		return collider;
	}
	
	public void setRectangle(Rectangle collider) {
		this.collider = collider;
	}
	
	public double getWidth() {
		return collider.getWidth();
	}
	
	public double getHeight() {
		return collider.getHeight();
	}
	
	public void setPosition(Vector v) {
		collider = new Rectangle(v.getX(), v.getY(),
				getWidth(), getHeight());
	}
	
	public double getTop() {
		return collider.getTop();
	}
	
	public double getRight() {
		return collider.getRight();
	}
	
	public double getBottom() {
		return collider.getBottom();
	}
	
	public double getLeft() {
		return collider.getLeft();
	}
	
	public Vector getPosition() {
		return new Vector(collider.getX1(), collider.getY1());
	}
	
	public BoxCollider copyOf() {
		return new BoxCollider(getRectangle().copyOf());
	}
}
