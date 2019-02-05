package main.prefabs;

import main.util.*;

import java.awt.*;
import java.util.Arrays;

public class DynamicObstacle extends Obstacle {
	
	private int iterationCounter;
	private int changeRate;
	
	public DynamicObstacle(BoxCollider collider, Vector speed, Polygon polygon, Color color, int changeRate) {
		super(collider, speed, polygon, color);
		this.changeRate = changeRate;
		iterationCounter = 0;
	}
	
	public void updateSpeed() {
		if (iterationCounter > changeRate) {
			iterationCounter = 0;
			Vector speed = getSpeed().copyOf();
			speed.setX(-speed.getX());
			speed.setY(-speed.getY());
			setSpeed(speed);
		} else {
			iterationCounter++;
		}
	}
}