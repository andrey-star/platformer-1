package main.prefabs;

import main.util.Vector;

import java.awt.*;

public class Player extends ReactingObject {
	
	private Vector controlSpeed;
	private boolean moveRight, moveLeft, moveUp, moveDown;
	private Vector arrowSpeed;
	
	
	public Player(BoxCollider collider, Vector speed, Polygon polygon, Color color, Vector controlSpeed) {
		super(collider, speed, polygon, color);
		this.controlSpeed = controlSpeed;
		arrowSpeed = Vector.zero();
	}
	
	public void moveRight(boolean move) {
		if (!moveRight && move) {
			arrowSpeed.setX(controlSpeed.getX());
			moveRight = true;
			moveLeft = false;
		} else if (moveRight && !move) {
			arrowSpeed.setX(0);
			moveRight = false;
		}
		applySpeeds();
	}
	
	public void moveLeft(boolean move) {
		if (!moveLeft && move) {
			arrowSpeed.setX(-controlSpeed.getX());
			moveLeft = true;
			moveRight = true;
		} else if (moveLeft && !move) {
			arrowSpeed.setX(0);
			moveLeft = false;
		}
		applySpeeds();
	}
	
	public void moveUp(boolean move) {
		if (!moveUp && move) {
			arrowSpeed.setY(-controlSpeed.getY());
			moveUp = true;
		} else if (moveUp && !move) {
			arrowSpeed.setY(0);
			moveUp = false;
		}
		applySpeeds();
	}
	
	public void moveDown(boolean move) {
		if (!moveDown && move) {
			arrowSpeed.setY(controlSpeed.getY());
			moveDown = true;
		} else if (moveDown && !move) {
			arrowSpeed.setY(0);
			moveDown = false;
		}
		applySpeeds();
	}
	
	protected void applySpeeds() {
		setSpeed(new Vector(arrowSpeed.getX() + getVerSpeed().getX() + getObstacleSpeed().getX(),
				arrowSpeed.getY() + getVerSpeed().getY() + getObstacleSpeed().getY()));
	}
	
	
}