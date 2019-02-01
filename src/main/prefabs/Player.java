package main.prefabs;

import main.util.Vector;

import java.awt.*;

public class Player extends CollidableGameObject {
	
	private Vector controlSpeed;
	private boolean moveRight, moveLeft, moveUp, moveDown;
	
	private double jumpForce = 4.5;
	private double fullJumpDelta = 0;
	
	public Player(Vector pos, double colliderWidth, double colliderHeight, Vector speed, Polygon polygon, Color color, Vector controlSpeed) {
		super(pos, colliderWidth, colliderHeight, speed, polygon, color);
		this.controlSpeed = controlSpeed;
	}
	
	public void jump() {
		Vector speed = getSpeed();
		speed.setY(speed.getY() - jumpForce);
		fullJumpDelta = jumpForce;
	}
	
	public void jumpIter(double g, int deltaTime) {
		double deltaSpeed = g * (1 / (1000.0 / deltaTime));
		Vector speed = getSpeed();
		speed.setY(speed.getY() + deltaSpeed);
		fullJumpDelta -= deltaSpeed;
	}
	
	public void endJump() {
		Vector speed = getSpeed();
		speed.setY(speed.getY() + fullJumpDelta);
		fullJumpDelta = 0;
	}
	
	public void upperHit() {
		Vector speed = getSpeed();
		double hitJump = 2 * fullJumpDelta;
		fullJumpDelta = -fullJumpDelta;
		speed.setY(speed.getY() + hitJump);
	}
	
	public void moveRight(boolean move) {
		if (!moveRight && move) {
			Vector speed = getSpeed();
			speed.setX(speed.getX() + controlSpeed.getX());
			moveRight = true;
		} else if (moveRight && !move) {
			Vector speed = getSpeed();
			speed.setX(speed.getX() - controlSpeed.getX());
			moveRight = false;
		}
	}
	
	public void moveLeft(boolean move) {
		if (!moveLeft && move) {
			Vector speed = getSpeed();
			speed.setX(speed.getX() - controlSpeed.getX());
			moveLeft = true;
		} else if (moveLeft && !move) {
			Vector speed = getSpeed();
			speed.setX(speed.getX() + controlSpeed.getX());
			moveLeft = false;
		}
	}
	
	public void moveUp(boolean move) {
		if (!moveUp && move) {
			Vector speed = getSpeed();
			speed.setY(speed.getY() - controlSpeed.getY());
			moveUp = true;
		} else if (moveUp && !move) {
			Vector speed = getSpeed();
			speed.setY(speed.getY() + controlSpeed.getY());
			moveUp = false;
		}
	}
	
	public void moveDown(boolean move) {
		if (!moveDown && move) {
			Vector speed = getSpeed();
			speed.setY(speed.getY() + controlSpeed.getY());
			moveDown = true;
		} else if (moveDown && !move) {
			Vector speed = getSpeed();
			speed.setY(speed.getY() - controlSpeed.getY());
			moveDown = false;
		}
	}
}