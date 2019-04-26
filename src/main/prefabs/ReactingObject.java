package main.prefabs;

import main.util.Vector;

import java.awt.*;


public abstract class ReactingObject extends CollidableGameObject {
	
	private Vector obstacleSpeed;
	private Vector verSpeed;
	private Vector realShift;
	
	private double jumpForce = 7.5;
	
	public ReactingObject(BoxCollider collider, Vector speed, Polygon polygon, Color color){
		super(collider, speed, polygon, color);
		verSpeed = Vector.zero();
		obstacleSpeed = Vector.zero();
	}
	
	public void jump() {
		verSpeed.setY(-jumpForce);
		applySpeeds();
		applyBottomObjectSpeed(Vector.zero());
	}
	
	public Vector getVerSpeed(){
		return verSpeed;
	}
	
	public  Vector getObstacleSpeed(){
		return obstacleSpeed;
	}
	
	public Vector getRealShift() {
		return realShift;
	}
	
	public void setRealShift(Vector realShift) {
		this.realShift = realShift;
	}
	
	public void applyG(double g, double deltaTime) {
		double deltaSpeed = g * (1 / (1000.0 / deltaTime));
		verSpeed.setY(verSpeed.getY() + deltaSpeed);
		applySpeeds();
	}
	
	
	public void hitBottom(CollidableGameObject gameObject) {
		verSpeed = Vector.zero();
		applyBottomObjectSpeed(gameObject.getSpeed());
		applySpeeds();
	}
	
	public void hitTop(double g, double deltaTime) {
		verSpeed = Vector.zero();
		applyG(g, deltaTime);
		applySpeeds();
	}
	
	private void applyBottomObjectSpeed(Vector v) {
		obstacleSpeed.setX(v.getX());
		obstacleSpeed.setY(v.getY());
		applySpeeds();
	}
	
	protected abstract void applySpeeds();
}
