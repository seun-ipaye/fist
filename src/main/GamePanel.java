package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable{
	final int originalTilesize = 16; // character sizes
	final int scale = 3;
	
	public final int tileSize = originalTilesize * scale; //48 pixel size
	final int maxScreenCol = 12;
	final int maxScreenRow = 16;
	final int screenWidth = tileSize * maxScreenRow;//768 pixel
	final int screenHeight = tileSize * maxScreenCol; //576 pixel
	
	// FPS
	int FPS = 60;
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	
	Player player = new Player(this, keyH);
	
	//player position and speed
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		long lastTime = System.nanoTime();
		long currentTime;

				
		while(gameThread != null) {
			currentTime = System.nanoTime();
			
			if (currentTime - lastTime >= drawInterval) {
				update();
				repaint();
				lastTime = currentTime;
			}
			
		}
		
	}
		
	
	public void update() {
		player.update();
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		player.draw(g2);
		g2.dispose();
	}
}
