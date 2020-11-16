package object;

import java.awt.Image;

import javax.swing.ImageIcon;

import game.Actor;

/**
 * 빈공간 오브젝트 클래스
 */
public class Space extends Actor  {

	private final boolean crushed = false;
	
	public Space(int x, int y) {
		super(x, y);
		setCrushed(crushed);
		initSpace();
	}
	
	private void initSpace() {
		ImageIcon icon = new ImageIcon("image/Space.png");
		Image image = icon.getImage();
		setImage(image);
	}

}
