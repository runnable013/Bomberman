package object;

import java.awt.Image;

import javax.swing.ImageIcon;

import game.Actor;
import game.Board;

public class Monster extends Actor implements Runnable {

	private final boolean crushed = true;

	private boolean stop;

	/**
	 * 죽였을 때 점수를 처리하기 위한 변수
	 */
	private final int point = 200;

	Board board;

	public Monster(int x, int y, Board board) {
		super(x, y);
		setCrushed(crushed);
		this.board = board;
		initMonster();
		setStop(false);
	}

	private void initMonster() {
		ImageIcon icon = new ImageIcon("image/Monster.png");
		Image image = icon.getImage();
		setImage(image);
	}

	public void move(int x, int y) {
		int dx = getX() + x;
		int dy = getY() + y;

		setX(dx);
		setY(dy);
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public int getPoint() {
		return point;
	}

	/**
	 * 몬스터 움직임
	 */
	@Override
	public void run() {
		boolean directionX = true;
		boolean directionY = true;
		try {
			while (!isStop()) {
				while (!isStop()) {
					Thread.sleep(300);
					int dir = (int) (Math.random() * 2);
					if (dir == 0) {
						if (directionY == true) {
							if (board.checkWallCollision(this, board.LEFT_COLLISION)) {
								directionY = !directionY;
								break;
							}
							if (board.checkObstacleCollision(this, board.LEFT_COLLISION)) {
								directionY = !directionY;
								break;
							}
							if (board.checkKeyCollision(this, board.LEFT_COLLISION)) {
								directionY = !directionY;
								break;
							}
							if (board.checkDoorCollision(this, board.LEFT_COLLISION)) {
								directionY = !directionY;
								break;
							}
							if (board.checkBombCollision(this, board.LEFT_COLLISION)) {
								directionY = !directionY;
								break;
							}
							move(-board.SPACE, 0);
						} else {
							if (board.checkWallCollision(this, board.RIGHT_COLLISION)) {
								directionY = !directionY;
								break;
							}
							if (board.checkObstacleCollision(this, board.RIGHT_COLLISION)) {
								directionY = !directionY;
								break;
							}
							if (board.checkKeyCollision(this, board.RIGHT_COLLISION)) {
								directionY = !directionY;
								break;
							}
							if (board.checkDoorCollision(this, board.RIGHT_COLLISION)) {
								directionY = !directionY;
								break;
							}
							if (board.checkBombCollision(this, board.RIGHT_COLLISION)) {
								directionY = !directionY;
								break;
							}
							move(board.SPACE, 0);
						}
					} else {
						if (directionX == true) {
							if (board.checkWallCollision(this, board.TOP_COLLISION)) {
								directionX = !directionX;
								break;
							}
							if (board.checkObstacleCollision(this, board.TOP_COLLISION)) {
								directionX = !directionX;
								break;
							}
							if (board.checkKeyCollision(this, board.TOP_COLLISION)) {
								directionX = !directionX;
								break;
							}
							if (board.checkDoorCollision(this, board.TOP_COLLISION)) {
								directionX = !directionX;
								break;
							}
							if (board.checkBombCollision(this, board.TOP_COLLISION)) {
								directionX = !directionX;
								break;
							}
							move(0, -board.SPACE);
						} else {
							if (board.checkWallCollision(this, board.BOTTOM_COLLISION)) {
								directionX = !directionX;
								break;
							}
							if (board.checkObstacleCollision(this, board.BOTTOM_COLLISION)) {
								directionX = !directionX;
								break;
							}
							if (board.checkKeyCollision(this, board.BOTTOM_COLLISION)) {
								directionX = !directionX;
								break;
							}
							if (board.checkDoorCollision(this, board.BOTTOM_COLLISION)) {
								directionX = !directionX;
								break;
							}
							if (board.checkBombCollision(this, board.BOTTOM_COLLISION)) {
								directionX = !directionX;
								break;
							}
							move(0, board.SPACE);
						}
					}
					if (getX() == board.player.getX() && getY() == board.player.getY()) {
						board.player.reduceLife();
					}
					board.repaint();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
