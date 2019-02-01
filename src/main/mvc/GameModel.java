package main.mvc;

import main.prefabs.*;
import main.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class GameModel implements ActionListener {
	
	Player player;
	Ground ground;
	ArrayList<Obstacle> obstacles;
	ArrayList<Enemy> enemies;
	
	private int width;
	private int height;
	
	private final double G = 15;
	private int deltaTime = 15; //ms
	private Timer jumpTimer = new Timer(deltaTime, this);
	
	GameModel(int width, int height) {
		this.width = width;
		this.height = height;
		player = new Player(new BoxCollider(ShapeCreator.square(width / 2, height / 2, 30)), Vector.ZERO, PolygonCreator.square(width / 2, height / 2, 30), Color.BLACK, Vector.ONE);
		ground = new Ground(new BoxCollider(ShapeCreator.rectangle(3, 1, width - 7, height * 2 / 3)), Vector.ZERO, PolygonCreator.rectangle(0, 0, width, height), Color.BLACK);
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
		CollisionState2D colState = ground.doesCollide(gameObject);
		if (colState.getX() != CollisionState.NONE || colState.getY() != CollisionState.NONE) {
			if (colState.getX() != CollisionState.NONE) {
				Vector v = player.getPosition().copyOf();
				if (colState.getX() == CollisionState.RIGHT) {
					v.setX(ground.getCollider().getRight() - player.getCollider().getWidth());
				} else {
					v.setX(ground.getCollider().getLeft());
				}
				player.setPosition(v);
			} else {
				player.applySpeedX();
			}
			if (colState.getY() != CollisionState.NONE) {
				Vector v = player.getPosition().copyOf();
				if (colState.getY() == CollisionState.BOTTOM) {
					v.setY(ground.getCollider().getBottom() - player.getCollider().getHeight());
				} else {
					v.setY(ground.getCollider().getTop());
				}
				player.setPosition(v);
			} else {
				player.applySpeedY();
			}
		} else {
			player.applySpeed();
		}
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
	
	void jump() {
		player.endJump();
		player.jump();
		jumpTimer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		CollisionState verColState = ground.doesCollide(player).getY();
		if (verColState == CollisionState.BOTTOM) {
			Vector v = player.getPosition();
			v.setY(ground.getCollider().getBottom() - player.getCollider().getHeight());
			player.setPosition(v);
			player.endJump();
			jumpTimer.stop();
		} else if (verColState == CollisionState.TOP) {
			Vector v = player.getPosition();
			v.setY(ground.getCollider().getTop());
			player.setPosition(v);
			player.upperHit();
		} else {
			player.jumpIter(G, deltaTime);
		}
	}
	
	void update() {
		movePlayer();
	}
}
