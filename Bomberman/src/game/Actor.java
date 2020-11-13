package game;

import java.awt.Image;

/**
 * 모든 오브젝트가 상속함
 * 좌표, 충돌, 이미지를 담당
 */
public class Actor {

	private final int SPACE = 50;

	private int x;
	private int y;
	
	private boolean crushed = true;
	
	private Image image;
	
	public Actor(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public boolean isCrushed() {
		return crushed;
	}

	public void setCrushed(boolean crushed) {
		this.crushed = crushed;
	}

	public boolean isLeftCollision(Actor actor) {
		return getX() - SPACE == actor.getX() && getY() == actor.getY();
	}

	public boolean isRightCollision(Actor actor) {
		return getX() + SPACE == actor.getX() && getY() == actor.getY();
	}

	public boolean isTopCollision(Actor actor) {
		return getY() - SPACE == actor.getY() && getX() == actor.getX();
	}

	public boolean isBottomCollision(Actor actor) {
		return getY() + SPACE == actor.getY() && getX() == actor.getX();
	}

}
