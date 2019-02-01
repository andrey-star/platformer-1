package main.prefabs;

import java.awt.*;

import main.util.BoxCollider;
import main.util.CollisionState2D;
import main.util.Rectangle;
import main.util.Vector;

public class CollidableGameObject {
	
	private BoxCollider collider;
	private Vector speed;
	private Polygon polygon;
	private Color color;
	
	public CollidableGameObject(Vector pos, double colliderWidth, double colliderHeight, Vector speed, Polygon polygon, Color color) {
		collider = new BoxCollider(pos, colliderWidth, colliderHeight);
		this.speed = speed;
		this.polygon = polygon;
		this.color = color;
	}

	public BoxCollider getCollider() {
		return collider;
	}

	public void setCollider(BoxCollider collider) {
		this.collider = collider;
	}

	public Vector getSpeed() {
		return speed;
	}

	public void setSpeed(Vector speed) {
		this.speed = speed;
	}

	public Polygon getPolygon() {
		return polygon;
	}

	public void setPolygon(Polygon polygon) {
		this.polygon = polygon;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void applySpeed() {
		translate(speed);
	}
	
	private void translate(Vector speed) {
		Vector pos = Vector.clone(getPosition());
		pos.setX(pos.getX() + speed.getX());
		pos.setY(pos.getY() + speed.getY());
		setPosition(pos);
	}
	
	public void setPosition(Vector v) {
		int deltaX = (int)(v.getX() - collider.getPosition().getX());
		int deltaY = (int)(v.getY() - collider.getPosition().getY());
		polygon.translate(deltaX, deltaY);
		collider.setPosition(v);
	}
	
	public Vector getPosition() {
		return collider.getPosition();
	}
	
	public CollisionState2D doesCollide(CollidableGameObject collideWith) {
		// todo
		
		return null;
	}
	

}