package main.util;

import main.prefabs.Obstacle;

import java.util.ArrayList;

public class FigureShifter {
	
	public static ArrayList<Obstacle> shiftX(ArrayList<Obstacle> obstacles, int shift) {
		ArrayList<Obstacle> shifted = new ArrayList<>();
		obstacles.forEach(obstacle -> shifted.add(obstacle.shiftX(shift)));
		return shifted;
	}
	
}
