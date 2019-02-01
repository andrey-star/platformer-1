package main.prefabs;

import main.util.Vector;

import java.awt.*;

public class Ground extends CollidableGameObject {
	
	public Ground(Vector pos, double colliderWidth, double colliderHeight, Vector speed, Polygon polygon, Color color) {
		super(pos, colliderWidth, colliderHeight, speed, polygon, color);
	}
	
}