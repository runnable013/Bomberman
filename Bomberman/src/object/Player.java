package object;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import game.Actor;
import game.Board;

public class Player extends Actor{
	private final boolean crushed = false;
	
	private Board board;
	
	public Player(int x, int y, Board board) {
		super(x, y);
		setCrushed(crushed);
		initPlayer();
		this.board = board;
	}
	
	private void initPlayer() {
		ImageIcon icon = new ImageIcon("image/Player.png");
		Image image = icon.getImage();
		setImage(image);
	}
	
	/**
	 * 게임 오버이면 못움직이게 하고 출구로 이동하면 문자를 띄움
	 * @param x x좌표
	 * @param y y좌표
	 */
	public void move(int x, int y) {
		if (!isGameOver()) {
			int dx = getX() + x;
			int dy = getY() + y;
			
			setX(dx);
			setY(dy);
		}
		for (int i = 0; i < board.monsters.size(); i++) {
			if (getX() == board.monsters.get(i).getX() && getY() == board.monsters.get(i).getY()) {
				board.player.reduceLife();
			}
		}
		if (getX() == board.exit.getX() && getY() == board.exit.getY()) {
			JOptionPane.showMessageDialog(board, "Winner");
			System.exit(0);
		}
		
	}
	
	/**
	 * 체력을 줄이고 체력이 다 줄면 게임 오버
	 */
	public void reduceLife() {
		board.setLife(board.getLife()-1);
		board.setLifeStr(String.valueOf(board.getLife()));
		board.restartLevel();
		if (isGameOver()) {
			board.player.setX(board.EMPTY_SPACE);
			board.player.setY(board.EMPTY_SPACE);
			board.repaint();
			JOptionPane.showMessageDialog(board, "Game Over");
			System.exit(0);
		}
	}
	
	/**
	 * 체력을 늘림 (코인을 넣을때)
	 */
	public void increaseLife() {
		if (board.getLife() < 9) {
			board.setLife(board.getLife()+1);
			board.setLifeStr(String.valueOf(board.getLife()));
		}
	}
	
	public boolean isGameOver() {
		if (board.getLife() <= 0) {
			return true;
		}
		return false;
	}
	
}
