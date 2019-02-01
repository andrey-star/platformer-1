package main.mvc;

import main.prefabs.CollidableGameObject;
import main.prefabs.Ground;
import main.prefabs.Player;
import main.util.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class GameModel implements ActionListener {
	
	int width;
	int height;
	Player player;
	Ground ground;
	ArrayList<CollidableGameObject> obstacles;
	
	private final double G = 90;
	private int deltaTime = 15; //ms
	private double jumpForce = 17;
	private double jumpSpeed;
	private Timer jumpTimer = new Timer(deltaTime, this);
	
	GameModel(int width, int height) {
		this.width = width;
		this.height = height;
		int[] xpoints = {0, 30, 30, 0};
		int[] ypoints = {0, 0, 30, 30};
		Polygon p = new Polygon(xpoints, ypoints, 4);
		player = new Player(new Vector(0, 0), 30, 30, new Vector(0, 0), p, Color.BLACK, new Vector(2, 2));
		xpoints = new int[]{-width / 2, width / 2, width / 2, -width / 2};
		ypoints = new int[]{100, 100, 101, 101};
		ground = new Ground(new Vector(0, 100), width, 1, new Vector(0, 0), new Polygon(xpoints, ypoints, xpoints.length), Color.BLACK);
		obstacles = new ArrayList<>();
	}
	
	void moveRight() {
		player.applyCustomSpeed(new Vector(player.getControlSpeed().getX(), 0));
	}
	
	void moveLeft() {
		player.applyCustomSpeed(new Vector(-player.getControlSpeed().getX(), 0));
;	}
	
	void moveUp() {
		player.applyCustomSpeed(new Vector(0, -player.getControlSpeed().getY()));
	}
	
	void moveDown() {
		player.applyCustomSpeed(new Vector(0, player.getControlSpeed().getY()));
	}
	
	private boolean moveHor(double x) {
//		player.applyCustomSpeed(x);
		return false;
	}
	
	private boolean moveVert(double y) {
//		boolean hit = checkVertHit(y);
		boolean hit = checkGroundHit(y);
		if (hit) {
			if (y < 0) { // hit upper bound
			
			} else { // hit lower bound
				player.setPosition(new Vector(player.getPosition().getX(), ground.getCollider().getTop() - player.getCollider().getHeight()));
			}
		} else {
			player.setPosition(new Vector(player.getPosition().getX(), y + player.getPosition().getY()));
		}
		return hit;
	}
	
	private CollidableGameObject checkVertHit(double y) {
		CollidableGameObject nearestObstacle;
		for (CollidableGameObject obstacle : obstacles) {
//			if ()
		}
		return null;
	}
	
	private void checkObstacleHit() {
	
	}
	
	private boolean checkGroundHit(double y) {
		if (y > 0) {
			return player.getCollider().getBottom() + y >= ground.getCollider().getTop();
		} else {
			return false;
		}
	}
	
	private void jumpIter() {
		if (!moveVert(jumpSpeed)) {
			jumpSpeed += G * (1 / (1000.0 / deltaTime));
		} else {
			jumpTimer.stop( );
		}
	}
	
	void jump() {
		jumpSpeed = -jumpForce;
		jumpTimer.start();
	
	}
	
	void update() {
		player.setPosition(new Vector(player.getPosition().getX() + player.getSpeed().getX(),
				player.getPosition().getY() + player.getSpeed().getY()));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		jumpIter();
	}
}
