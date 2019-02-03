package main.prefabs;

import main.util.CollisionState;
import main.util.CollisionState2D;
import main.util.Vector;

import java.awt.*;

public class Ground extends CollidableGameObject {
	
	public Ground(BoxCollider collider, Vector speed, Polygon polygon, Color color) {
		super(collider, speed, polygon, color);
	}
	
	public CollisionState2D doesCollide(CollidableGameObject collideWith) {
		return new CollisionState2D(doesCollideHor(collideWith), doesCollideVer(collideWith));
	}
	
	private CollisionState doesCollideHor(CollidableGameObject collideWith) {
		if (collideWith.getSpeed().getX() >= 0) {
			if (collideWith.getCollider().getRight() + collideWith.getSpeed().getX() >=
					this.getCollider().getRight()) {
				return CollisionState.RIGHT;
			}
		}
		
		if (collideWith.getSpeed().getX() <= 0) {
			if (collideWith.getCollider().getLeft() + collideWith.getSpeed().getX() <=
					this.getCollider().getLeft()) {
				return CollisionState.LEFT;
			}
		}
		return CollisionState.NONE;
	}
	
	private CollisionState doesCollideVer(CollidableGameObject collideWith) {
		if (collideWith.getSpeed().getY() >= 0) {
			if (collideWith.getCollider().getBottom() + collideWith.getSpeed().getY() >=
					this.getCollider().getBottom()) {
				return CollisionState.BOTTOM;
			}
		}
		if (collideWith.getSpeed().getY() <= 0){
			if (collideWith.getCollider().getTop() + collideWith.getSpeed().getY() <=
					this.getCollider().getTop()) {
				return CollisionState.TOP;
			}
		}
		return CollisionState.NONE;
	}
}