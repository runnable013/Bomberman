package object;

import java.awt.Image;

import javax.swing.ImageIcon;

import game.Actor;

/**
 * Obstacle Object Class
 */
public class Obstacle extends Actor {

	private final boolean crushed = true;
	
	public Obstacle(int x, int y) {
		super(x, y);
		setCrushed(crushed);
		initObstacle();
	}
	
	private void initObstacle() {
		ImageIcon icon = new ImageIcon("image/Obstacle.png");
		Image image = icon.getImage();
		setImage(image);
	}
	
}
