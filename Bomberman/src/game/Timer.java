package game;

import object.Player;


/**
 * �ð� ������ ����ϴ� Ŭ����
 */
public class Timer extends Thread {

	private final int TIME_ORI = 240;
	private int time = TIME_ORI;

	private Player player;
	private Board board;

	public Timer(Player player, Board board) {
		this.player = player;
		this.board = board;
	}

	public void setTime() {
		this.time = TIME_ORI;
	}

	public String getTime() {
		return String.format(" %02d:%02d", time / 60, time % 60);
	}

	/**
	 * �ð����� �� ����
	 */
	@Override
	public void run() {
		try {
			while (true) {
				//�ð� ���� 
				while (time >= 0) {
					sleep(1000);
					board.setTimeStr(board.getTimeStr().substring(0, 6) + getTime());
					time--;
				}
				player.reduceLife();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
