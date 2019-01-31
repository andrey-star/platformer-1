package main.prefabs;

import main.util.BoxCollider;
import main.util.Rectangle;
import main.util.Vector;

public class Player {
	
	private Vector playerPosition;
	private Vector playerSpeed;
	private BoxCollider collider;
	private double mass;
	
	public Player(final Vector playerPosition, final Vector playerSpeed) {
		this.playerPosition = playerPosition;
		this.playerSpeed = playerSpeed;
		mass = 2;
		collider = new BoxCollider(new Rectangle(0, 0, 30, 30));
	}
	
	public Vector getPosition() {
		return playerPosition;
	}
	
	public void setPosition(final Vector x) {
		playerPosition = x;
		collider.move(x);
	}
	
	public Vector getSpeed() {
		return playerSpeed;
	}
	
	public void setSpeed(final Vector x) {
		playerSpeed = x;
	}
	
	public double getMass() {
		return mass;
	}
	
	public void setMass(double mass) {
		this.mass = mass;
	}
	
	public BoxCollider getCollider() {
		return collider;
	}
}