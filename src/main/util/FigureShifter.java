package main.util;

import main.prefabs.Obstacle;

import java.util.ArrayList;

public class FigureShifter {
	
	public static ArrayList<Obstacle> shift(ArrayList<Obstacle> obstacles, Vector shift) {
		ArrayList<Obstacle> shifted = new ArrayList<>();
		obstacles.forEach(obstacle -> shifted.add(obstacle.shift(shift)));
		return shifted;
	}
	
}
