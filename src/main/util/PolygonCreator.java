package main.util;

import java.awt.*;

public class PolygonCreator {
	
	public static Polygon rectangle(int x1, int y1, int width, int height) {
		int[] xPoints = {x1, x1 + width, x1 + width, x1};
		int[] yPoints = {y1, y1, y1 + height, y1 + height};
		return new Polygon(xPoints, yPoints, 4);
	}
	
	public static Polygon square(int x1, int y1, int side) {
		return PolygonCreator.rectangle(x1, y1, side, side);
	}
	
}
