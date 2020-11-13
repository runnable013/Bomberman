package object;

import java.awt.Image;

import javax.swing.ImageIcon;

import game.Actor;

/**
 * 부술 수 없는 벽 오브젝트 클래스
 */

public class Wall extends Actor {
	
	private final boolean crushed = false;
	
	public Wall(int x, int y) {
		super(x, y);
		setCrushed(crushed);
		initWall();
	}
	
	private void initWall(){
		ImageIcon icon = new ImageIcon("image/Wall.png");
		Image image = icon.getImage();
		setImage(image);
	}
	
}
