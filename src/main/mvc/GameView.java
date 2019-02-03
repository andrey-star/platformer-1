package main.mvc;

import main.prefabs.Camera;
import main.prefabs.CollidableGameObject;
import main.prefabs.Obstacle;
import main.util.Rectangle;
import main.util.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class GameView extends JFrame {
	
	private GameModel model;
	private BufferedImage image;
	private Graphics g;
	private Vector startAnchor;
	
	GameView(GameModel model, int width, int height, int shift) {
		this.model = model;
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, width, height);
		getContentPane().setLayout(null);
		setVisible(true);
		setResizable(false);
		image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics();
		startAnchor = new Vector(0, shift);
	}
	
	@Override
	public void paint(Graphics g2) {
		g.clearRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		drawGround();
		drawObstacles();
		drawCamera();
		drawPlayer();
		g2.drawImage(image, 0, 0, null);
	}
	
	private void drawCamera() {
		Color prev = g.getColor();
		g.setColor(Color.MAGENTA);
		double shift = -(model.camera.getPosition().getX());
		drawRectWithShift(model.camera.getBox(), new Vector(shift, 0));
		g.setColor(prev);
	}
	
	private void drawObstacles() {
		for (Obstacle obstacle : model.obstacles) {
			if (!model.camera.isInView(obstacle.getCollider().getRectangle())) {
				continue;
			}
			Color prev = g.getColor();
			double shift = -(model.camera.getPosition().getX());
			fillPolygonWithShift(obstacle, new Vector(shift, 0));
			drawColliderWithShift(obstacle, new Vector(shift, 0));
			g.setColor(prev);
		}
	}
	
	private void drawGround() {
		Color prev = g.getColor();
		double shift = -(model.camera.getPosition().getX());
		drawPolygonWithShift(model.ground, new Vector(shift, 0));
		drawColliderWithShift(model.ground, new Vector(shift, 0));
		g.setColor(prev);
	}
	
	private void drawPlayer() {
		Color prev = g.getColor();
		double shift = -(model.camera.getPosition().getX());
		fillPolygonWithShift(model.player, new Vector(shift, 0));
		drawColliderWithShift(model.player, new Vector(shift, 0));
		g.setColor(prev);
	}
	
	private void fillPolygon(CollidableGameObject gameObject) {
		fillPolygonWithShift(gameObject, new Vector(0, 0));
	}
	
	private void fillPolygonWithShift(CollidableGameObject gameObject, Vector shift) {
		g.setColor(gameObject.getColor());
		Polygon p = gameObject.getPolygon();
		Polygon p2 = new Polygon(Arrays.copyOf(p.xpoints, p.npoints), Arrays.copyOf(p.ypoints, p.npoints), p.npoints);
		p2.translate((int) (startAnchor.getX() + shift.getX()), (int) (startAnchor.getY() + shift.getY()));
		g.fillPolygon(p2);
	}
	
	private void drawPolygonWithShift(CollidableGameObject gameObject, Vector shift) {
		g.setColor(gameObject.getColor());
		Polygon p = gameObject.getPolygon();
		Polygon p2 = new Polygon(Arrays.copyOf(p.xpoints, p.npoints), Arrays.copyOf(p.ypoints, p.npoints), p.npoints);
		p2.translate((int) (startAnchor.getX() + shift.getX()), (int) (startAnchor.getY() + shift.getY()));
		g.drawPolygon(p2);
	}
	
	private void drawColliderWithShift(CollidableGameObject gameObject, Vector shift) {
		Color prev = g.getColor();
		g.setColor(Color.RED);
		Rectangle rec = gameObject.getCollider().getRectangle();
		g.drawRect((int) (startAnchor.getX() + rec.getX1() + shift.getX()), (int) (startAnchor.getY() + rec.getY1() + shift.getY()), (int) rec.getWidth(), (int) rec.getHeight());
		g.setColor(prev);
	}
	
	private void drawCollider(CollidableGameObject gameObject) {
		drawColliderWithShift(gameObject, new Vector(0, 0));
	}
	
	private void drawRectWithShift(Rectangle rec, Vector shift) {
		g.drawRect((int) (startAnchor.getX() + rec.getX1() + shift.getX()), (int) (startAnchor.getY() + rec.getY1() + shift.getY()), (int) rec.getWidth(), (int) rec.getHeight());
	}
}
