package main.mvc;

import main.prefabs.*;
import main.util.CollisionState;
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
	ArrayList<Obstacle> obstacles;
	ArrayList<Enemy> enemies;
	
	private final double G = 90;
	private int deltaTime = 15; //ms
	private Timer jumpTimer = new Timer(deltaTime, this);
	
	GameModel(int width, int height) {
		this.width = width;
		this.height = height;
		int[] xpoints = {0, 30, 30, 0};
		int[] ypoints = {0, 0, 30, 30};
		Polygon p = new Polygon(xpoints, ypoints, 4);
		player = new Player(new Vector(0, 0), 30, 30, new Vector(0, 0), p, Color.BLACK, new Vector(2, 2));
		xpoints = new int[]{-width / 2, width / 2, width / 2, -width / 2};
		ypoints = new int[]{-height / 2, -height / 2, 100, 100};
		ground = new Ground(new Vector(-width / 2, -height / 2), width, height / 2 + 100, new Vector(0, 0), new Polygon(xpoints, ypoints, xpoints.length), Color.BLACK);
		obstacles = new ArrayList<>();
	}
	
	void moveRight(boolean move) {
		player.moveRight(move);
		movePlayer();
	}
	
	void moveLeft(boolean move) {
		player.moveLeft(move);
		movePlayer();
	}
	
	void moveUp(boolean move) {
		player.moveUp(move);
		movePlayer();
	}
	
	void moveDown(boolean move) {
		player.moveDown(move);
		movePlayer();
	}
	
	private void movePlayer() {
		moveObject(player);
	}
	
	private void moveObject(CollidableGameObject gameObject) {
		//todo collison with ground
//		if (ground.doesCollide(gameObject).getX() != CollisionState.NONE) {
//
//		} else {
//			player.applySpeed();
//		}
		// collision with other objects
//		if (gameObject instanceof Player) {
//			Obstacle nearest;
//			boolean collision = false;
//			for (Obstacle obstacle : obstacles) {
//				if (gameObject.doesCollide(obstacle).getX() != CollisionState.NONE) {
//					collision = true;
//					break;
//				}
//			}
//		}
		gameObject.applySpeed();
		
	}
	
	private boolean moveVert(double y) {
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
	
	private boolean checkGroundHit(double y) {
		if (y > 0) {
			return player.getCollider().getBottom() + y >= ground.getCollider().getTop();
		} else {
			return false;
		}
	}
	
	void jump() {
		player.endJump();
		player.jump();
		jumpTimer.start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!ground.doesCollideVer(player)) {
			player.jumpIter(G, deltaTime);
		} else {
			Vector v = player.getPosition();
			v.setY(ground.getCollider().getBottom() - player.getCollider().getHeight());
			player.setPosition(v);
			player.endJump();
			jumpTimer.stop();
		}
	}
	
	void update() {
		movePlayer();
	}
}
