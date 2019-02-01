package main.prefabs;

import main.util.Vector;

import java.awt.*;

public class Platform extends Obstacle {
	
	Vector speed;
	
	public Platform(Vector pos, double colliderWidth, double colliderHeight, Vector speed, Polygon polygon, Color color) {
		super(pos, colliderWidth, colliderHeight, speed, polygon, color);
	}
	
}