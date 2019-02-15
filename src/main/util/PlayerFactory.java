package main.util;

import main.prefabs.BoxCollider;
import main.prefabs.Player;

import java.awt.*;

public class PlayerFactory {
	
	public static Player standardSquarePlayer(int x1, int y1, int side, Vector controlSpeed) {
		return new Player(new BoxCollider(new Rectangle(x1, y1, side, side)), Vector.zero(), PolygonFactory.square(x1, y1, side), Color.BLACK, controlSpeed);
	}
	
	public static Player standardRectanglePlayer(int x1, int y1, int width, int height, Vector controlSpeed) {
		return new Player(new BoxCollider(new Rectangle(x1, y1, width, height)), Vector.zero(), PolygonFactory.rectangle(x1, y1, width, height), Color.BLACK, controlSpeed);
	}
	
}
