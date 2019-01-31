package main.mvc;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements ActionListener, KeyListener {
	
	private GameModel model;
	private GameView view;
	private final int FPS = 60;
	private Timer timer = new Timer(1000 / FPS, this);
	boolean moveRight, moveLeft, moveUp, moveDown;
	
	private GameController(GameModel model, GameView view) {
		this.model = model;
		this.view = view;
	}
	
	private void run() {
		view.addKeyListener(this);
		timer.start();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			GameModel model = new GameModel();
			GameView view = new GameView(model);
			GameController controller = new GameController(model, view);
			controller.run();
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (moveRight) {
			model.moveRight();
		} else if (moveLeft) {
			model.moveLeft();
		}
		if (moveUp) {
			model.moveUp();
		} else if (moveDown) {
			model.moveDown();
		}
		model.update();
		view.repaint();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moveRight = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			moveLeft = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			moveUp = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			moveDown = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			model.jump();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moveRight = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			moveLeft = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			moveUp = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			moveDown = false;
		}
	}
}
