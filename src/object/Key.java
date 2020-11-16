package object;

import java.awt.Image;

import javax.swing.ImageIcon;

import game.Actor;

/**
 * ���� ���� ���� ������Ʈ Ŭ����
 */
public class Key extends Actor{

	private boolean breaked;
	
	/**
	 * �μ��� �� ������ ó���ϱ� ���� ����
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
