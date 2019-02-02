package main.prefabs;

import main.util.BoxCollider;
import main.util.Vector;

import java.awt.*;

public class Player extends CollidableGameObject {
	
	private Vector controlSpeed;
	private boolean moveRight, moveLeft, moveUp, moveDown;
	
	private double jumpForce = 2.5;
	boolean airborne = false;
	
	public Player(BoxCollider collider, Vector speed, Polygon polygon, Color color, Vector controlSpeed) {
		super(collider, speed, polygon, color);
		this.controlSpeed = controlSpeed;
	}
	
	public void jump() {
//		if (!airborne) {
			Vector speed = getSpeed().copyOf();
			speed.setY(speed.getY() - jumpForce);
			setSpeed(speed);
			airborne = true;
//		}
	}
	
	public void moveRight(boolean move) {
		if (!moveRight && move) {
			Vector speed = getSpeed().copyOf();
			speed.setX(speed.getX() + controlSpeed.getX());
			setSpeed(speed);
			moveRight = true;
		} else if (moveRight && !move) {
			Vector speed = getSpeed().copyOf();
			speed.setX(speed.getX() - controlSpeed.getX());
			setSpeed(speed);
			moveRight = false;
		}
	}
	
	public void moveLeft(boolean move) {
		if (!moveLeft && move) {
			Vector speed = getSpeed().copyOf();
			speed.setX(speed.getX() - controlSpeed.getX());
			setSpeed(speed);
			moveLeft = true;
		} else if (moveLeft && !move) {
			Vector speed = getSpeed().copyOf();
			speed.setX(speed.getX() + controlSpeed.getX());
			setSpeed(speed);
			moveLeft = false;
		}
	}
	
	public void moveUp(boolean move) {
		if (!moveUp && move) {
			Vector speed = getSpeed().copyOf();
			speed.setY(speed.getY() - controlSpeed.getY());
			setSpeed(speed);
			moveUp = true;
		} else if (moveUp && !move) {
			Vector speed = getSpeed().copyOf();
			speed.setY(speed.getY() + controlSpeed.getY());
			setSpeed(speed);
			moveUp = false;
		}
	}
	
	public void moveDown(boolean move) {
		if (!moveDown && move) {
			Vector speed = getSpeed().copyOf();
			speed.setY(speed.getY() + controlSpeed.getY());
			setSpeed(speed);
			moveDown = true;
		} else if (moveDown && !move) {
			Vector speed = getSpeed().copyOf();
			speed.setY(speed.getY() - controlSpeed.getY());
			setSpeed(speed);
			moveDown = false;
		}
	}
	
	public void applyG(double g, double deltaTime) {
		double deltaSpeed = g * (1 / (1000.0 / deltaTime));
		Vector speed = getSpeed().copyOf();
		speed.setY(speed.getY() + deltaSpeed);
		setSpeed(speed);
	}
	
	public void hitBottom() {
		if (moveDown) {
			setSpeed(new Vector(getSpeed().getX(), controlSpeed.getY()));
		} else if (moveUp) {
			setSpeed(new Vector(getSpeed().getX(), -controlSpeed.getY()));
		} else {
			setSpeed(new Vector(getSpeed().getX(), 0));
		}
		airborne = false;
	}
	
	public void hitTop(double g, double deltaTime) {
		hitBottom() ;
		applyG(g, deltaTime);
	}
}