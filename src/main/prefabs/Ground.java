package main.prefabs;

import main.util.CollisionState2D;
import main.util.Vector;

import java.awt.*;

public class Ground extends CollidableGameObject {
	
	public Ground(Vector pos, double colliderWidth, double colliderHeight, Vector speed, Polygon polygon, Color color) {
		super(pos, colliderWidth, colliderHeight, speed, polygon, color);
	}
	
	public CollisionState2D doesCollide(CollidableGameObject collideWith) {
	
	}
	
	public boolean doesCollideHor(CollidableGameObject collideWith) {
		// todo
		return false;
	}
	
	public boolean doesCollideVer(CollidableGameObject collideWith) {
		if (collideWith.getSpeed().getY() >= 0)
		return collideWith.getCollider().getBottom() + collideWith.getCollider().getHeight() + collideWith.getSpeed().getY() >=
				this.getCollider().getBottom();
		else {
			return collideWith.getCollider().getTop() + collideWith.getSpeed().getY() <=
					this.getCollider().getTop();
		}
	}
}