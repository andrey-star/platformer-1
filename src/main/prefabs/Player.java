package main.prefabs;

import main.util.Vector;

import java.awt.*;

public class Player extends CollidableGameObject {
	
	private Vector controlSpeed;
	private Vector realShift;
	private boolean moveRight, moveLeft, moveUp, moveDown;
	private Vector arrowSpeed;
	private Vector verSpeed;
	private Vector obstacleSpeed;
	
	private double jumpForce = 7.5;
	private boolean airborne = false;
	
	public Player(BoxCollider collider, Vector speed, Polygon polygon, Color color, Vector controlSpeed) {
		super(collider, speed, polygon, color);
		this.controlSpeed = controlSpeed;
		arrowSpeed = Vector.zero();
		verSpeed = Vector.zero();
		obstacleSpeed = Vector.zero();
	}
	
	public void jump() {
//		if (!airborne) {
		verSpeed.setY(-jumpForce);
		applySpeeds();
//		}
	}
	
	public void setAirborne(boolean isAirborne) {
		airborne = isAirborne;
	}
	
	public Vector getRealShift() {
		return realShift;
	}
	
	public void setRealShift(Vector realShift) {
		this.realShift = realShift;
	}
	
	public void moveRight(boolean move) {
		if (!moveRight && move) {
			arrowSpeed.setX(controlSpeed.getX());
			moveRight = true;
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
	
	private void applySpeeds() {
		setSpeed(new Vector(arrowSpeed.getX() + verSpeed.getX() + obstacleSpeed.getX(),
				arrowSpeed.getY() + verSpeed.getY() + obstacleSpeed.getY()));
	}
	
	public void applyG(double g, double deltaTime) {
		double deltaSpeed = g * (1 / (1000.0 / deltaTime));
		verSpeed.setY(verSpeed.getY() + deltaSpeed);
		applySpeeds();
	}
	
	public void hitBottom(CollidableGameObject gameObject) {
		verSpeed = Vector.zero();
		applyBottomObjectSpeed(gameObject.getSpeed());
		applySpeeds();
	}
	
	public void hitTop(double g, double deltaTime) {
		verSpeed = Vector.zero();
		applyG(g, deltaTime);
		applySpeeds();
	}
	
	private void applyBottomObjectSpeed(Vector v) {
		if (!airborne) {
			obstacleSpeed.setX(v.getX());
			obstacleSpeed.setY(v.getY());
		}
		applySpeeds();
	}
}