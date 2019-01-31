package main.mvc;

import main.util.Rectangle;
import main.util.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

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
		playerStart = new Vector(getWidth() / 2, getHeight() / 2);
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
		Rectangle col = model.player.getCollider().getRectangle();
		g.drawRect((int) (playerStart.getX() + col.getX1()), (int) (playerStart.getY() + col.getY1()), (int) col.getWidth(), (int) col.getHeight());
		g.setColor(prev);
	}
	
	private void drawPlayer() {
		Color prev = g.getColor();
		g.setColor(Color.BLACK);
		Vector playerPos = model.player.getPosition();
		g.fillRect((int) (playerPos.getX() + playerStart.getX()), (int) (playerPos.getY() + playerStart.getY()),
				(int) playerBounds.getWidth(), (int) playerBounds.getHeight()/2);
		g.setColor(prev);
	}
	
	private void drawGround() {
		Color prev = g.getColor();
		g.setColor(Color.BLACK);
		g.drawLine(0, (int) (playerStart.getY() + model.ground.getVerticalPosition()), getWidth(), (int) (playerStart.getY() + model.ground.getVerticalPosition()));
		g.setColor(prev);
	}
	
}
