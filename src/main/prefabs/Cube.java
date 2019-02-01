package main.prefabs;

import main.util.BoxCollider;
import main.util.Vector;

import java.awt.*;

public class Cube extends Obstacle {
	
	public Cube(BoxCollider collider, Vector speed, Polygon polygon, Color color) {
		super(collider, speed, polygon, color);
	}
	
}