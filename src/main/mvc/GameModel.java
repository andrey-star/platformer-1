package main.mvc;

import main.prefabs.GameObject;
import main.prefabs.Ground;
import main.prefabs.Player;
import main.util.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GameModel implements ActionListener {
	
	Player player;
	private Vector arrowsMoveSpeed;
	Ground ground;
	final double G = 9.81;
	private double jumpSpeed;
	private Timer jumpTimer = new Timer(1000 / 60, this);
	
	GameModel() {
		player = new Player(new Vector(0, 0), new Vector(0, 0));
		ground = new Ground(30);
		arrowsMoveSpeed = new Vector(7, 7);
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
	
	private GameObject checkVertHit(double y) {
//		return checkGroundHit(y);
//		checkObstacleHit();
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
			jumpSpeed += 2;
		} else {
			jumpTimer.stop( );
		}
	}
	
	void jump() {
		jumpSpeed = -17;
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
