package main.mvc;

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
		drawPlayer();
		g2.drawImage(image, 0, 0, null);
	}
	
	private void drawObstacles() {
		for (Obstacle obstacle : model.obstacles) {
			Color prev = g.getColor();
			fillPolygon(obstacle);
			drawCollider(obstacle);
			g.setColor(prev);
		}
	}
	
	private void drawCollider(CollidableGameObject gameObject) {
		Color prev = g.getColor();
		g.setColor(Color.RED);
		Rectangle rec = gameObject.getCollider().getRectangle();
		g.drawRect((int) (startAnchor.getX() + rec.getX1()), (int) (startAnchor.getY() + rec.getY1()), (int) rec.getWidth(), (int) rec.getHeight());
		g.setColor(prev);
	}
	
	private void drawPlayer() {
		Color prev = g.getColor();
		fillPolygon(model.player);
		drawCollider(model.player);
		g.setColor(prev);
	}
	
	private void fillPolygon(CollidableGameObject gameObject) {
		g.setColor(gameObject.getColor());
		Polygon p = gameObject.getPolygon();
		Polygon p2 = new Polygon(Arrays.copyOf(p.xpoints, p.npoints), Arrays.copyOf(p.ypoints, p.npoints), p.npoints);
		p2.translate((int) startAnchor.getX(), (int) startAnchor.getY());
		g.fillPolygon(p2);
	}
	
	private void drawPolygon(CollidableGameObject gameObject) {
		g.setColor(gameObject.getColor());
		Polygon p = gameObject.getPolygon();
		Polygon p2 = new Polygon(Arrays.copyOf(p.xpoints, p.npoints), Arrays.copyOf(p.ypoints, p.npoints), p.npoints);
		p2.translate((int) startAnchor.getX(), (int) startAnchor.getY());
		g.drawPolygon(p2);
	}
	
	private void drawGround() {
		Color prev = g.getColor();
		drawPolygon(model.ground);
		drawCollider(model.ground);
		g.setColor(prev);
	}
	
}
