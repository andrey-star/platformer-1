package main.util;

import main.prefabs.Obstacle;

import java.awt.*;

public class ObstacleCreator {
	
	public static Obstacle staticCyanRectangleObstacle(int x1, int y1, int side) {
		return new Obstacle(new BoxCollider(new Rectangle(x1, y1, side, side)), Vector.ZERO, PolygonCreator.square(x1, y1, side), Color.CYAN);
	}
	
}
