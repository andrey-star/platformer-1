package main.prefabs;

import main.util.Vector;

import java.awt.*;

public class Obstacle extends CollidableGameObject {
	
	public Obstacle(BoxCollider collider, Vector speed, Polygon polygon, Color color) {
		super(collider, speed, polygon, color);
	}
	
}