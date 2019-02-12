package main.prefabs;

import java.awt.*;
import java.util.Arrays;

import main.util.*;

public class CollidableGameObject {
	
	private BoxCollider collider;
	private Vector speed;
	private Polygon polygon;
	private Color color;
	
	public CollidableGameObject(BoxCollider collider, Vector speed, Polygon polygon, Color color) {
		this.collider = collider;
		this.speed = speed;
		this.polygon = polygon;
		this.color = color;
	}
	
	public BoxCollider getCollider() {
		return collider.copyOf();
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
	
	public void applySpeedX() {
		translate(new Vector(speed.getX(), 0));
	}
	
	public void applySpeedY() {
		translate(new Vector(0, speed.getY()));
	}
	
	private void translate(Vector speed) {
		Vector pos = getPosition().copyOf();
		pos.setX(pos.getX() + speed.getX());
		pos.setY(pos.getY() + speed.getY());
		setPosition(pos);
	}
	
	public void setPosition(Vector v) {
		int deltaX = (int) Math.round(v.getX() - collider.getPosition().getX());
		int deltaY = (int) Math.round(v.getY() - collider.getPosition().getY());
		polygon.translate(deltaX, deltaY);
		collider.setPosition(new Vector(collider.getPosition().getX() + deltaX, collider.getPosition().getY() + deltaY));
	}
	
	public Vector getPosition() {
		return collider.getPosition().copyOf();
	}
	
	public CollisionState2D doesCollide(CollidableGameObject collideWith) {
		CollisionState collisionHor = CollisionState.NONE;
		CollisionState collisionVer = CollisionState.NONE;
		
		if (collider.getPosition().getX() < collideWith.getPosition().getX()) {
			if (doesCollideHor(this, collideWith)) {
				collisionHor = CollisionState.RIGHT;
			}
		} else {
			if (doesCollideHor(collideWith, this)) {
				collisionHor = CollisionState.LEFT;
			}
		}
		
		if (collider.getPosition().getY() < collideWith.getPosition().getY()) {
			if (doesCollideVer(this, collideWith)) {
				collisionVer = CollisionState.BOTTOM;
			}
		} else {
			if (doesCollideVer(collideWith, this)) {
				collisionVer = CollisionState.TOP;
			}
		}
		
		
		return new CollisionState2D(collisionHor, collisionVer);
	}
	
	
	private boolean doesCollideHor(CollidableGameObject left, CollidableGameObject right) {
		
		SectionVertical sectionLeft = new SectionVertical(left.getCollider().getRight() + left.getSpeed().getX(),
				left.getCollider().getTop(), left.getCollider().getBottom());
		SectionVertical sectionRight = new SectionVertical(right.getCollider().getLeft() + right.getSpeed().getX(),
				right.getCollider().getTop(), right.getCollider().getBottom());
		
		if (sectionLeft.getBottomY() <= sectionRight.getTopY() || sectionLeft.getTopY() >= sectionRight.getBottomY()) {
			// can't collide - Y axis coordinates to different
			return false;
		}
		if (sectionLeft.getX() <= sectionRight.getX()) {
			// can't collide - X axis coordinates not close enough
			return false;
		}
		return true;
	}
	
	private boolean doesCollideVer(CollidableGameObject top, CollidableGameObject bottom) {
		
		SectionHorizontal sectionTop = new SectionHorizontal(top.getCollider().getBottom() + top.getSpeed().getY(),
				top.getCollider().getRight(), top.getCollider().getLeft());
		SectionHorizontal sectionBottom = new SectionHorizontal(bottom.getCollider().getTop() + bottom.getSpeed().getY(),
				bottom.getCollider().getRight(), bottom.getCollider().getLeft());
		
		// todo
//		System.out.println(sectionTop.getRightX() + " " + sectionBottom.getLeftX());
		if (sectionTop.getRightX() <= sectionBottom.getLeftX() || sectionTop.getLeftX() >= sectionBottom.getRightX()) {
			// can't collide - X axis coordinates too different
			return false;
		}
		
		if (sectionTop.getY() <= sectionBottom.getY()) {
			// can't collide - Y axis coordinates not close enough
			return false;
		}
		return true;
	}
	
	public void makeYSpeedZero() {
		setSpeed(new Vector(getSpeed().getX(), 0));
	}
	
	public Obstacle shiftX(int shift) {
		Polygon copy = new Polygon(Arrays.copyOf(polygon.xpoints, polygon.npoints), Arrays.copyOf(polygon.ypoints, polygon.npoints), polygon.npoints);
		copy.translate(shift, 0);
		return new Obstacle(collider.copyOf().shiftX(shift), speed.copyOf(), copy, getColor());
	}
}