package main.util;

import main.prefabs.Obstacle;

import java.util.ArrayList;
import java.util.List;

public class FigureShifter {
	
	public static List<Obstacle> shift(List<Obstacle> obstacles, Vector shift) {
		List<Obstacle> shifted = new ArrayList<>();
		obstacles.forEach(obstacle -> shifted.add(obstacle.shift(shift)));
		return shifted;
	}
	
}
