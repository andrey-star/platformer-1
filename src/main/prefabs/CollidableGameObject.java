package main.prefabs;

import java.awt.*;

import main.util.*;
import main.util.Rectangle;

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
		int deltaX = (int) (v.getX() - collider.getPosition().getX());
		int deltaY = (int) (v.getY() - collider.getPosition().getY());
		polygon.translate(deltaX, deltaY);
		collider.setPosition(new Vector(collider.getPosition().getX() + deltaX, collider.getPosition().getY() + deltaY));
	}
	
	public Vector getPosition() {
		return collider.getPosition();
	}
	
	public CollisionState2D doesCollide(CollidableGameObject collideWith) {
		CollisionState collisionVer = CollisionState.NONE;
		CollisionState collisionHor = CollisionState.NONE;
		
		// todo
		if (collider.getPosition().getX() > collideWith.getPosition().getX()) {
			if (doesCollideHor(this, collideWith)) {
				collisionHor = CollisionState.LEFT;
			}
		} else {
			if (doesCollideHor(collideWith, this)) {
				collisionHor = CollisionState.RIGHT;
			}
		}
		
		if (collider.getPosition().getY() > collideWith.getPosition().getY()) {
			if (doesCollideVer(this, collideWith)) {
				collisionVer = CollisionState.TOP;
			}
		} else {
			if (doesCollideVer(collideWith, this)) {
				collisionVer = CollisionState.BOTTOM;
			}
		}
		
		
		return new CollisionState2D(collisionHor, collisionVer);
	}
	
	
	private boolean doesCollideHor(CollidableGameObject left, CollidableGameObject right) {
		
		SectionVertical sectionLeft = new SectionVertical(left.getCollider().getRight() + left.getSpeed().getX(),
				left.getCollider().getTop() + left.getSpeed().getY(), left.getCollider().getBottom() + left.getSpeed().getY());
		SectionVertical sectionRight = new SectionVertical(right.getCollider().getLeft() + right.getSpeed().getX(),
				right.getCollider().getTop() + right.getSpeed().getY(), right.getCollider().getBottom() + right.getSpeed().getY());
		
		
		if (sectionLeft.getBottomY() > sectionRight.getTopY() || sectionLeft.getTopY() < sectionRight.getBottomY()) {
			return false;
		}
		return true;
	}
	
	private boolean doesCollideVer(CollidableGameObject top, CollidableGameObject bottom) {
		
		SectionHorizontal sectionTop = new SectionHorizontal(top.getCollider().getBottom() + top.getSpeed().getY(),
				top.getCollider().getRight() + top.getSpeed().getX(), top.getCollider().getLeft() + top.getSpeed().getX());
		SectionHorizontal sectionBottom = new SectionHorizontal(bottom.getCollider().getTop() + bottom.getSpeed().getY(),
				bottom.getCollider().getRight() + bottom.getSpeed().getX(), bottom.getCollider().getLeft() + bottom.getSpeed().getX());
		
		if (top.getCollider().getRight() < bottom.getCollider().getLeft() || top.getCollider().getLeft() > bottom.getCollider().getRight()) {
			return false;
		}
		
		return true;
	}
	
}