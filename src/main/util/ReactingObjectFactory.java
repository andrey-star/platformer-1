package main.util;

import main.prefabs.BoxCollider;
import main.prefabs.Enemy;
import main.prefabs.Player;

import java.awt.*;

public class ReactingObjectFactory {
	
	public static Player standartSquarePlayer(int x1, int y1, int side, Vector controlSpeed) {
		return new Player(new BoxCollider(new Rectangle(x1, y1, side, side)), Vector.zero(), PolygonFactory.square(x1, y1, side), Color.BLACK, controlSpeed);
	}
	
	public static Player standardRectanglePlayer(int x1, int y1, int width, int height, Vector controlSpeed) {
		return new Player(new BoxCollider(new Rectangle(x1, y1, width, height)), Vector.zero(), PolygonFactory.rectangle(x1, y1, width, height), Color.BLACK, controlSpeed);
	}
	
	public static Enemy standartSquareEnemy(int x1, int y1, int side, int speed, int changeRate) {
		Vector enemySpeed = new Vector(speed, 0);
		return new Enemy(new BoxCollider(new Rectangle(x1, y1, side, side)), enemySpeed, PolygonFactory.square(x1, y1, side), Color.MAGENTA, changeRate);
	}
	
	public static Enemy standardRectangleEnemy(int x1, int y1, int width, int height, int speed, int changeRate) {
		Vector enemySpeed = new Vector(speed, 0);
		return new Enemy(new BoxCollider(new Rectangle(x1, y1, width, height)), enemySpeed, PolygonFactory.rectangle(x1, y1, width, height), Color.MAGENTA, changeRate);
	}
	
}
