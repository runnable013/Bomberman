package object;

import java.awt.Image;

import javax.swing.ImageIcon;

import game.Actor;

/**
 * 출구 오브젝트 클래스
 */
public class Exit extends Actor{

	public Exit(int x, int y) {
		super(x, y);
		initExit();
	}
	
	private void initExit() {
		ImageIcon icon = new ImageIcon("image/Exit.png");
		Image image = icon.getImage();
		setImage(image);
	}
	
}
