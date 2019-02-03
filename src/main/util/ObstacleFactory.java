package main.util;

import main.prefabs.Obstacle;

import java.awt.*;

public class ObstacleFactory {
	
	public static Obstacle staticCyanRectangleObstacle(int x1, int y1, int side) {
		return new Obstacle(new BoxCollider(new Rectangle(x1, y1, side, side)), Vector.ZERO, PolygonFactory.square(x1, y1, side), Color.CYAN);
	}
	
}
