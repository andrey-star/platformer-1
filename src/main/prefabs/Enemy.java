package main.prefabs;

import main.util.Vector;

import java.awt.*;

public class Enemy extends ReactingObject {
	
	private int iterationCounter;
	private int changeRate;
	
	public Enemy(BoxCollider collider, Vector speed, Polygon polygon, Color color, int changeRate) {
		super(collider, speed, polygon, color);
		this.changeRate = changeRate;
		iterationCounter = 0;
	}
	
	public void updateSpeed() {
		if (iterationCounter > changeRate) {
			iterationCounter = 0;
			Vector speed = getSpeed().copyOf();
			speed.setX(-speed.getX());
			setSpeed(speed);
		} else {
			iterationCounter++;
		}
	}
	protected void applySpeeds() {
		setSpeed(new Vector(getVerSpeed().getX() + getObstacleSpeed().getX(),
				getVerSpeed().getY() + getObstacleSpeed().getY()));
	}
}