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
	 * When the game is over, it is disabled, and when moving to the exit, a character is displayed.
	 * @param x x location
	 * @param y y location
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
	 * Game over when stamina decreases and stamina is insufficient
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
	 * Increases HP (when inserting coins)
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
