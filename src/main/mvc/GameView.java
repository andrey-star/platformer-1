package main.mvc;

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
	private Vector playerStart;
	private Rectangle playerBounds;
	
	GameView(GameModel model) {
		this.model = model;
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		getContentPane().setLayout(null);
		setVisible(true);
		setResizable(false);
		image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics();
		playerStart = new Vector(getWidth() / 2.0, getHeight() / 2.0);
		playerBounds = model.player.getCollider().getRectangle();
	}
	
	@Override
	public void paint(Graphics g2) {
		g.clearRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		drawPlayer();
		drawGround();
		drawCollider();
		g2.drawImage(image, 0, 0, null);
	}
	
	private void drawCollider() {
		Color prev = g.getColor();
		
		g.setColor(Color.RED);
		
		Rectangle rec = model.player.getCollider().getRectangle();
		g.drawRect((int) (playerStart.getX() + rec.getX1()), (int) (playerStart.getY() + rec.getY1()), (int) rec.getWidth(), (int) rec.getHeight());
		
		g.setColor(prev);
	}
	
	private void drawPlayer() {
		Color prev = g.getColor();
		
		g.setColor(model.player.getColor());
		Polygon p = model.player.getPolygon();
		Polygon p2 = new Polygon(Arrays.copyOf(p.xpoints, p.npoints), Arrays.copyOf(p.ypoints, p.npoints), p.npoints);
		p2.translate((int) playerStart.getX(), (int) playerStart.getY());
		g.fillPolygon(p2);
		
		g.setColor(prev);
	}
	
	private void drawGround() {
		Color prev = g.getColor();
		
		g.setColor(model.ground.getColor());
		Polygon p = model.ground.getPolygon();
		Polygon p2 = new Polygon(Arrays.copyOf(p.xpoints, p.npoints), Arrays.copyOf(p.ypoints, p.npoints), p.npoints);
		p2.translate((int) playerStart.getX(), (int) playerStart.getY());
		g.fillPolygon(p2);
		
		g.setColor(prev);
	}
	
}
