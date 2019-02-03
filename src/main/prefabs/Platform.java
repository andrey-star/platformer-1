package main.prefabs;

import main.util.Vector;

import java.awt.*;

public class Platform extends Obstacle {
	
	Vector speed;
	
	public Platform(BoxCollider collider, Vector speed, Polygon polygon, Color color) {
		super(collider, speed, polygon, color);
	}
	
}