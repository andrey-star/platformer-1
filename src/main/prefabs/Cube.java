package main.prefabs;

import main.util.Vector;

import java.awt.*;

public class Cube extends Obstacle {
	
	public Cube(Vector pos, double colliderWidth, double colliderHeight, Vector speed, Polygon polygon, Color color) {
		super(pos, colliderWidth, colliderHeight, speed, polygon, color);
	}
	
}