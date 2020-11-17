package object;

import java.awt.Image;

import javax.swing.ImageIcon;

import game.Actor;
import game.Board;

/**
 * The player's bomb object class
 */
public class Bomb extends Actor implements Runnable {
	private final int bombCount = 3000, bombMax = 1;

	private final boolean crushed = false;

	private Board board;

	public Bomb(int x, int y, Board board) {
		super(x, y);
		this.board = board;
		setCrushed(crushed);
		initBomb();
	}

	private void initBomb() {
		ImageIcon icon = new ImageIcon("image/Bomb.png");
		Image image = icon.getImage();
		setImage(image);
	}

	public void move(int x, int y) {
		int dx = x;
		int dy = y;

		setX(dx);
		setY(dy);
	}

	@Override
	public void run() {
		try {
			Thread.sleep(bombCount);
			explode();
			// Hide the bomb if it explodes
			move(board.EMPTY_SPACE, board.EMPTY_SPACE);
			board.setBombCount(bombMax);
			board.repaint();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 *  Bomb explosion
	 */
	private void explode() {
		boolean left = true, right = true, up = true, down = true;
		Actor leftActor = null;
		Actor rightActor = null;
		Actor upActor = null;
		Actor downActor = null;
		Actor centerActor = board.player;

		if (getX() == centerActor.getX() && getY() == centerActor.getY()) {
			((Player) centerActor).reduceLife();
		}
		
		//Up, left and right object search
		for (int i = 0; i < board.world.size(); i++) {
			Actor actor = board.world.get(i);

			if (left && actor.isLeftCollision(this)) {
				leftActor = actor;
			}
			if (right && actor.isRightCollision(this)) {
				rightActor = actor;
			}
			if (up && actor.isTopCollision(this)) {
				upActor = actor;
			}
			if (down && actor.isBottomCollision(this)) {
				downActor = actor;
			}
		}

		//Hide fragile objects
		if (leftActor != null && leftActor.isCrushed()) {
			leftActor.setX(board.EMPTY_SPACE);
			leftActor.setY(board.EMPTY_SPACE);
			left = false;
		}

		if (rightActor != null && rightActor.isCrushed()) {
			rightActor.setX(board.EMPTY_SPACE);
			rightActor.setY(board.EMPTY_SPACE);
			right = false;
		}

		if (upActor != null && upActor.isCrushed()) {
			upActor.setX(board.EMPTY_SPACE);
			upActor.setY(board.EMPTY_SPACE);
			up = false;
		}

		if (downActor != null && downActor.isCrushed()) {
			downActor.setX(board.EMPTY_SPACE);
			downActor.setY(board.EMPTY_SPACE);
			down = false;
		}

		// If it's a monster, hide it, stop, and score count
		if (leftActor instanceof Monster) {
			((Monster) leftActor).setStop(true);
			board.setPoint(board.getPoint() + ((Monster) leftActor).getPoint());
			board.setPointStr(String.valueOf(board.getPoint()));
		}
		if (rightActor instanceof Monster) {
			((Monster) rightActor).setStop(true);
			board.setPoint(board.getPoint() + ((Monster) rightActor).getPoint());
			board.setPointStr(String.valueOf(board.getPoint()));
		}
		if (upActor instanceof Monster) {
			((Monster) upActor).setStop(true);
			board.setPoint(board.getPoint() + ((Monster) upActor).getPoint());
			board.setPointStr(String.valueOf(board.getPoint()));
		}
		if (downActor instanceof Monster) {
			((Monster) downActor).setStop(true);
			board.setPoint(board.getPoint() + ((Monster) downActor).getPoint());
			board.setPointStr(String.valueOf(board.getPoint()));
		}

		// Key to count down and score count
		if (leftActor instanceof Key) {
			((Key) leftActor).setBreaked(true);
			board.reduceKeyCount();
			board.setPoint(board.getPoint() + ((Key) leftActor).getPoint());
			board.setPointStr(String.valueOf(board.getPoint()));
		}
		if (rightActor instanceof Key) {
			((Key) rightActor).setBreaked(true);
			board.reduceKeyCount();
			board.setPoint(board.getPoint() + ((Key) rightActor).getPoint());
			board.setPointStr(String.valueOf(board.getPoint()));
		}
		if (upActor instanceof Key) {
			((Key) upActor).setBreaked(true);
			board.reduceKeyCount();
			board.setPoint(board.getPoint() + ((Key) upActor).getPoint());
			board.setPointStr(String.valueOf(board.getPoint()));
		}
		if (downActor instanceof Key) {
			((Key) downActor).setBreaked(true);
			board.reduceKeyCount();
			board.setPoint(board.getPoint() + ((Key) downActor).getPoint());
			board.setPointStr(String.valueOf(board.getPoint()));
		}
		
		// Player side health decrease
		if (leftActor instanceof Player) {
			((Player) leftActor).reduceLife();
		}
		if (rightActor instanceof Player) {
			((Player) rightActor).reduceLife();
		}
		if (upActor instanceof Player) {
			((Player) upActor).reduceLife();
		}
		if (downActor instanceof Player) {
			((Player) downActor).reduceLife();
		}

		// Open the door when all keys are broken
		if (board.getKeyCount() == 0) {
			board.openDoor();
			board.reduceKeyCount();
		}

		left = true;
		right = true;
		up = true;
		down = true;
	}
}