package object;

import java.awt.Image;

import javax.swing.ImageIcon;

import game.Actor;

/**
 * Ű�� �� �μ��� ������� �� Ŭ����
 */
public class Door extends Actor {

	private final boolean crushed = false;
	
	public Door(int x, int y) {
		super(x, y);
		setCrushed(crushed);
		initDoor();
	}
	
	private void initDoor() {
		ImageIcon icon = new ImageIcon("image/Door.png");
		Image image = icon.getImage();
		setImage(image);
	}
	
}
