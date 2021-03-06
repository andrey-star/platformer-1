package main.util;

import main.prefabs.BoxCollider;
import main.prefabs.DynamicObstacle;
import main.prefabs.Obstacle;

import java.awt.*;

public class ObstacleFactory {
	
	public static Obstacle staticRectangleObstacle(int x1, int y1, int side) {
		return new Obstacle(new BoxCollider(new Rectangle(x1, y1, side, side)), Vector.zero(), PolygonFactory.square(x1, y1, side), Color.CYAN);
	}
	
	public static DynamicObstacle dynamicRectangleObstacle(int x1, int y1, int side, Vector speed, int changeRate) {
		return new DynamicObstacle(new BoxCollider(new Rectangle(x1, y1, side, side)), speed, PolygonFactory.square(x1, y1, side), Color.CYAN, changeRate);
	}
	
}
