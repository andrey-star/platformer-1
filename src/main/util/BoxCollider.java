package main.util;

public class BoxCollider {

	private Rectangle collider;
	private double width, height;
	
	public BoxCollider(final Rectangle collider) {
		this.collider = collider;
		width = collider.getWidth();
		height = collider.getHeight();
	}
	
	public Rectangle getRectangle() {
		return collider;
	}
	
	public void setRectangle(Rectangle collider) {
		this.collider = collider;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void move(Vector v) {
		collider = new Rectangle(v.getX(), v.getY(),
				v.getX() + width, v.getY() + height);
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
	
}
