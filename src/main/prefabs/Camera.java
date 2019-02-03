package main.prefabs;

import main.util.Rectangle;
import main.util.Vector;

public class Camera {
	
	private Rectangle box;
	
	public Camera(Rectangle position) {
		this.box = position;
	}
	
	public Vector getPosition() {
		return new Vector(box.getX1(), box.getY1());
	}
	
	public void setPosition(Vector position) {
		box = new Rectangle(position.getX(), position.getY(), box.getWidth(), box.getHeight());
	}
	
	public Rectangle getBox() {
		return box;
	}
	
	public boolean isInView(Rectangle object) {
		if (object.getRight() < box.getLeft()) {
			return false;
		}
		if (object.getLeft() > box.getRight()) {
			return false;
		}
		
		if (object.getBottom() < box.getTop()) {
			return false;
		}
		if (object.getTop() > box.getBottom()) {
			return false;
		}
		return true;
	}
}
