package object;

import java.awt.Image;

import javax.swing.ImageIcon;

import game.Actor;

/**
 * 출구 오브젝트 클래스
 */
public class Exit extends Actor{
	
	private final boolean crushed = false;
	
	public Exit(int x, int y) {
		super(x, y);
		initExit();
		setCrushed(crushed);
	}
	
	private void initExit() {
		ImageIcon icon = new ImageIcon("image/Exit.png");
		Image image = icon.getImage();
		setImage(image);
	}
	
}
