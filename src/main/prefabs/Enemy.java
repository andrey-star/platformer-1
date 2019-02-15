package main.prefabs;

import main.util.Vector;

import java.awt.*;

public class Enemy extends DynamicObstacle {
	
	private int iterationCounter;
	private int changeRate;
	
	public Enemy(BoxCollider collider, Vector speed, Polygon polygon, Color color, int changeRate) {
		super(collider, speed, polygon, color, changeRate);
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