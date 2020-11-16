package game;

import java.awt.EventQueue;

import javax.swing.JFrame;


/**
 * Main 클래스
 */
public class Bomberman extends JFrame {
	private static final long serialVersionUID = 1L;
	private final int OFFSET = 20;

	public Bomberman() {
		initUI();
	}

	/**
	 *  프레임 생성 및 크기 설정 등등..
	 */
	private void initUI() {
		setTitle("Bomberman");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		Board board = new Board();

		add(board);

		setSize(board.getBoardWidth() + OFFSET, board.getBoardHeight() + 2 * OFFSET);
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			Bomberman game = new Bomberman();
			game.setVisible(true);
		});
	}
}
