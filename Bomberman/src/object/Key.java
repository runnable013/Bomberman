package object;

import java.awt.Image;

import javax.swing.ImageIcon;

import game.Actor;

/**
 * 문을 여는 열쇠 오브젝트 클래스
 */
public class Key extends Actor{

	private boolean breaked;
	
	/**
	 * 부셨을 때 점수를 처리하기 위한 변수
	 */
	private final int point = 800;
	
	private final boolean crushed = true;
	
	public Key(int x, int y) {
		super(x, y);
		setCrushed(crushed);
		initKey();
		setBreaked(false);
	}
	
	public boolean isBreaked() {
		return breaked;
	}
	
	public int getPoint() {
		return point;
	}

	public synchronized void setBreaked(boolean breaked) {
		this.breaked = breaked;
	}

	private void initKey() {
		ImageIcon icon = new ImageIcon("image/Key.jpg");
		Image image = icon.getImage();
		setImage(image);
	}
	
}
