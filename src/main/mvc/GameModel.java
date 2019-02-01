package main.mvc;

import main.prefabs.CollidableGameObject;
import main.prefabs.Ground;
import main.prefabs.Player;
import main.util.Vector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class GameModel implements ActionListener {
	
	Player player;
	Ground ground;
	ArrayList<CollidableGameObject> obstacles;
	
	private Vector arrowsMoveSpeed;
	private final double G = 90;
	private int deltaTime = 15; //ms
	private double jumpForce = 17;
	private double jumpSpeed;
	private Timer jumpTimer = new Timer(deltaTime, this);
	
	GameModel() {
//		player = new Player(new Vector(0, 0), new Vector(0, 0), 30, 30);  hgjgj
		ground = new Ground(30);
		arrowsMoveSpeed = new Vector(7, 7);
		obstacles = new ArrayList<>();
	}
	
	void moveRight() {
		moveHor(arrowsMoveSpeed.getX());
	}
	
	void moveLeft() {
		moveHor(-arrowsMoveSpeed.getX())
;	}
	
	void moveUp() {
		moveVert(-arrowsMoveSpeed.getY());
	}
	
	void moveDown() {
		moveVert(arrowsMoveSpeed.getY());
	}
	
	private boolean moveHor(double x) {
		player.setPosition(new Vector(player.getPosition().getX() + x, player.getPosition().getY()));
		return false;
	}
	
	private boolean moveVert(double y) {
//		boolean hit = checkVertHit(y);
		boolean hit = checkGroundHit(y);
		if (hit) {
			if (y < 0) { // hit upper bound
			
			} else { // hit lower bound
				player.setPosition(new Vector(player.getPosition().getX(), ground.getVerticalPosition() - player.getCollider().getHeight()));
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
			return player.getCollider().getBottom() + y >= ground.getVerticalPosition();
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
