package main.prefabs;

import main.util.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player extends CollidableGameObject implements ActionListener {
	
	private Vector controlSpeed;
	private boolean moveRight, moveLeft, moveUp, moveDown;
	
	private double jumpForce = 17;
	private double jumpSpeed;
	
	private double g;
	
	public Player(Vector pos, double colliderWidth, double colliderHeight, Vector speed, Polygon polygon, Color color, Vector controlSpeed) {
		super(pos, colliderWidth, colliderHeight, speed, polygon, color);
		this.controlSpeed = controlSpeed;
	}
	
	public void jump(double g) {
		this.g = g;
		jumpSpeed = -jumpForce;
		jumpTimer.start();
	}
	
	private void jumpIter() {
		jumpSpeed += g * (1 / (1000.0 / deltaTime));
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