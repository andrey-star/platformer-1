package main.mvc;

import main.prefabs.*;
import main.util.*;

import java.awt.*;
import java.util.ArrayList;

class GameModel {
	
	Player player;
	Ground ground;
	ArrayList<Obstacle> obstacles;
	ArrayList<Enemy> enemies;
	
	private final double G = 3;
	private int deltaTime = 16; //ms
	
	GameModel(int width, int height) {
		player = PlayerCreator.standardSquarePlayer(width / 6, height / 2, 30, Vector.ONE);
		ground = new Ground(new BoxCollider(ShapeCreator.rectangle(3, 1, width - 7, height * 2 / 3)), Vector.ZERO, PolygonCreator.rectangle(0, 0, width, height), Color.BLACK);
		obstacles = new ArrayList<>();
		int obstacleSide = (int) player.getCollider().getWidth();
		
		// 1 figure
		// 1 row
		obstacles.add(ObstacleCreator.staticCyanRectangleObstacle(width / 6 + 50, (int) ground.getCollider().getBottom() - obstacleSide, obstacleSide));
		obstacles.add(ObstacleCreator.staticCyanRectangleObstacle(width / 6 + 80, (int) ground.getCollider().getBottom() - obstacleSide, obstacleSide));
		obstacles.add(ObstacleCreator.staticCyanRectangleObstacle(width / 6 + 110, (int) ground.getCollider().getBottom() - obstacleSide, obstacleSide));
		obstacles.add(ObstacleCreator.staticCyanRectangleObstacle(width / 6 + 140, (int) ground.getCollider().getBottom() - obstacleSide, obstacleSide));
		obstacles.add(ObstacleCreator.staticCyanRectangleObstacle(width / 6 + 170, (int) ground.getCollider().getBottom() - obstacleSide, obstacleSide));
		// 2 row
		obstacles.add(ObstacleCreator.staticCyanRectangleObstacle(width / 6 + 80, (int) ground.getCollider().getBottom() - obstacleSide - 30, obstacleSide));
		obstacles.add(ObstacleCreator.staticCyanRectangleObstacle(width / 6 + 110, (int) ground.getCollider().getBottom() - obstacleSide - 30, obstacleSide));
		obstacles.add(ObstacleCreator.staticCyanRectangleObstacle(width / 6 + 140, (int) ground.getCollider().getBottom() - obstacleSide - 30, obstacleSide));
		obstacles.add(ObstacleCreator.staticCyanRectangleObstacle(width / 6 + 170, (int) ground.getCollider().getBottom() - obstacleSide - 30, obstacleSide));
		// 3 row
		obstacles.add(ObstacleCreator.staticCyanRectangleObstacle(width / 6 + 110, (int) ground.getCollider().getBottom() - obstacleSide - 60, obstacleSide));
		obstacles.add(ObstacleCreator.staticCyanRectangleObstacle(width / 6 + 140, (int) ground.getCollider().getBottom() - obstacleSide - 60, obstacleSide));
		obstacles.add(ObstacleCreator.staticCyanRectangleObstacle(width / 6 + 170, (int) ground.getCollider().getBottom() - obstacleSide - 60, obstacleSide));
		obstacles.add(ObstacleCreator.staticCyanRectangleObstacle(width / 6 + 200, (int) ground.getCollider().getBottom() - obstacleSide - 60, obstacleSide));
		obstacles.add(ObstacleCreator.staticCyanRectangleObstacle(width / 6 + 230, (int) ground.getCollider().getBottom() - obstacleSide - 60, obstacleSide));
		obstacles.add(ObstacleCreator.staticCyanRectangleObstacle(width / 6 + 260, (int) ground.getCollider().getBottom() - obstacleSide - 60, obstacleSide));
		// 4 row
		obstacles.add(ObstacleCreator.staticCyanRectangleObstacle(width / 6 + 140, (int) ground.getCollider().getBottom() - obstacleSide - 90, obstacleSide));
		obstacles.add(ObstacleCreator.staticCyanRectangleObstacle(width / 6 + 170, (int) ground.getCollider().getBottom() - obstacleSide - 90, obstacleSide));
		// 5 row
		obstacles.add(ObstacleCreator.staticCyanRectangleObstacle(width / 6 + 170, (int) ground.getCollider().getBottom() - obstacleSide - 120, obstacleSide));
	
		// 2 figure
		// 1 row
		obstacles.add(ObstacleCreator.staticCyanRectangleObstacle(width / 6 + 400, (int) ground.getCollider().getBottom() - obstacleSide, obstacleSide));
		obstacles.add(ObstacleCreator.staticCyanRectangleObstacle(width / 6 + 430, (int) ground.getCollider().getBottom() - obstacleSide, obstacleSide));
		obstacles.add(ObstacleCreator.staticCyanRectangleObstacle(width / 6 + 460, (int) ground.getCollider().getBottom() - obstacleSide, obstacleSide));
		obstacles.add(ObstacleCreator.staticCyanRectangleObstacle(width / 6 + 490, (int) ground.getCollider().getBottom() - obstacleSide, obstacleSide));
		
		// 2 row
		obstacles.add(ObstacleCreator.staticCyanRectangleObstacle(width / 6 + 400, (int) ground.getCollider().getBottom() - obstacleSide - 30, obstacleSide));
		obstacles.add(ObstacleCreator.staticCyanRectangleObstacle(width / 6 + 490, (int) ground.getCollider().getBottom() - obstacleSide - 30, obstacleSide));
	
		// 3 row
		// 2 row
		obstacles.add(ObstacleCreator.staticCyanRectangleObstacle(width / 6 + 400, (int) ground.getCollider().getBottom() - obstacleSide - 60, obstacleSide));
		obstacles.add(ObstacleCreator.staticCyanRectangleObstacle(width / 6 + 490, (int) ground.getCollider().getBottom() - obstacleSide - 60, obstacleSide));
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
	
	private Vector getMaxShift(CollidableGameObject toCollide, CollidableGameObject collideWith) {
		
		boolean insideType = (collideWith instanceof Ground);
		CollisionState2D colState = insideType ? collideWith.doesCollide(toCollide) : toCollide.doesCollide(collideWith);
		Vector maxNewPos = toCollide.getPosition().copyOf();
		if (colState.getX() != CollisionState.NONE) {
			if (colState.getX() == CollisionState.RIGHT) {
				if (insideType) {
					maxNewPos.setX(collideWith.getCollider().getRight() - toCollide.getCollider().getWidth());
				} else {
					maxNewPos.setX(collideWith.getCollider().getLeft() - toCollide.getCollider().getWidth());
				}
			} else {
				if (insideType) {
					maxNewPos.setX(collideWith.getCollider().getLeft());
				} else {
					maxNewPos.setX(collideWith.getCollider().getRight());
				}
			}
		} else {
			maxNewPos.setX(maxNewPos.getX() + toCollide.getSpeed().getX());
		}
		
		if (colState.getY() != CollisionState.NONE) {
			if (colState.getY() == CollisionState.BOTTOM) {
				if (insideType) {
					maxNewPos.setY(collideWith.getCollider().getBottom() - toCollide.getCollider().getHeight());
				} else {
					maxNewPos.setY(collideWith.getCollider().getTop() - toCollide.getCollider().getHeight());
				}
				if (toCollide instanceof Player) {
					((Player) toCollide).hitBottom();
				} else {
					toCollide.makeYSpeedZero();
				}
			} else {
				if (insideType) {
					maxNewPos.setY(collideWith.getCollider().getTop() + 1);
				} else {
					maxNewPos.setY(collideWith.getCollider().getBottom() + 1);
				}
				if (toCollide instanceof Player) {
					// doesnt work
					((Player) toCollide).hitTop(G, deltaTime);
				} else {
					toCollide.makeYSpeedZero();
				}
			}
		} else {
			maxNewPos.setY(maxNewPos.getY() + toCollide.getSpeed().getY());
		}
		return new Vector(maxNewPos.getX() - toCollide.getPosition().getX(),
				maxNewPos.getY() - toCollide.getPosition().getY());
	}
	
	private void moveObject(CollidableGameObject gameObject) {
		if (gameObject instanceof Player) {
			player.applyG(G, deltaTime);
		}
		Vector maxShift = getMaxShift(gameObject, ground); // max ground shift
		for (Obstacle obstacle : obstacles) {
			Vector curMaxShift = getMaxShift(gameObject, obstacle);
			if (Math.abs(maxShift.getX()) > Math.abs(curMaxShift.getX())) {
				maxShift.setX(curMaxShift.getX());
			}
			if (Math.abs(maxShift.getY()) > Math.abs(curMaxShift.getY())) {
				maxShift.setY(curMaxShift.getY());
			}
		}
		player.setPosition(new Vector(player.getPosition().getX() + maxShift.getX(),
				player.getPosition().getY() + maxShift.getY()));
	}
	
	void jump() {
		player.jump();
	}
	
	void update() {
		movePlayer();
	}
}
