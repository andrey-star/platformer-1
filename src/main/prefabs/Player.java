package main.prefabs;

import main.util.Vector;

import java.awt.*;

public class Player extends CollidableGameObject {
	
	private Vector controlSpeed;
	
	public Player(Vector pos, double colliderWidth, double colliderHeight, Vector speed, Polygon polygon, Color color, Vector controlSpeed) {
		super(pos, colliderWidth, colliderHeight, speed, polygon, color);
		this.controlSpeed = controlSpeed;
	}
	
	public void moveRight() {
		setPosition(new Vector(controlSpeed.getX(), 0));
	}
	
	public void moveLeft() {
		setPosition(new Vector(-controlSpeed.getX(), 0));
	}
	
	public void moveUp() {
		setPosition(new Vector(0, -controlSpeed.getY()));
	}
	
	public void moveDown() {
		setPosition(new Vector(0, controlSpeed.getY()));
	}
	
}