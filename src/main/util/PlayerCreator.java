package main.util;

import main.prefabs.Player;

import java.awt.*;

public class PlayerCreator {
	
	public static Player standardSquarePlayer(int x1, int y1, int side, Vector controlSpeed) {
		return new Player(new BoxCollider(new Rectangle(x1, y1, side, side)), Vector.ZERO, PolygonCreator.square(x1, y1, side), Color.BLACK, controlSpeed);
	}
	
}
