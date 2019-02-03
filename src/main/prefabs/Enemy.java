package main.prefabs;

import main.util.Vector;

import java.awt.*;

public class Enemy extends CollidableGameObject {
	
	private Vector controlSpeed;
	
	public Enemy(BoxCollider collider, Vector speed, Polygon polygon, Color color, Vector controlSpeed) {
		super(collider, speed, polygon, color);
		this.controlSpeed = controlSpeed;
	}
	
	public Vector getControlSpeed() {
		return controlSpeed;
	}
}