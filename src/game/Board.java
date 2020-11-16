package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import object.Bomb;
import object.Door;
import object.Exit;
import object.Key;
import object.Monster;
import object.Obstacle;
import object.Player;
import object.Space;
import object.Wall;

public class Board extends JPanel {
	private static final long serialVersionUID = 1L;
	
	/**
     * 그래픽을 처리하기 위한 변수
     */
	private final int OFFSET = 0;
    public final int SPACE = 50;
    
    /**
     * 없어진 오브젝트들의 그래픽을 없애기 위한 좌표
     */
    public final int EMPTY_SPACE = -60;
    
    /**
     * 충돌 처리를 위한 변수
     */
    public final int LEFT_COLLISION = 1;
    public final int RIGHT_COLLISION = 2;
    public final int TOP_COLLISION = 3;
    public final int BOTTOM_COLLISION = 4;
    
    /**
     * 
     */
    public final int KEY_COUNT_ORI = 2;

    /**
     * 오브젝트를 저장하기 위한 변수
     */
	public ArrayList<Wall> walls;
	public ArrayList<Monster> monsters;
	public ArrayList<Key> keys;
	public ArrayList<Obstacle> obstacles;
	public ArrayList<Space> spaces;
	public ArrayList<Actor> world;
	
	/**
     * 오브젝트를 저장하기 위한 변수
     */
	private Bomb bomb;
	public Door door;
	public Exit exit;
	public Player player;
	private Timer timer;
	
	Image image;
	
	private int w = 0, h = 0, keyCount = 2, rand = -1, level, bombCount = 1, life = 3, point = 0;
	private String lifeStr = String.valueOf(life);
	private String pointStr = String.valueOf(point);
	private String keyCountStr = "key : " + keyCount;
	private String timeStr = "TIME : 04:00";
	private String levelStr;
	
	public String[] difficulty = {"*", "**", "*", "***", "***"};
	
	/**
	 * 맵
	 */
	public String[] map
			= {"                 \n"
			+ " ############### \n"	
			+ " # P *  M * * *# \n"
			+ " # #*#*#*# # #*# \n"
			+ " #  **** **MK *# \n"
			+ " # # # ###*#*#*# \n"
			+ " # *** #E# *  *# \n"
			+ " #*#*#*#X#*# # # \n"
			+ " #   *      * *# \n"
			+ " #*# #*# # # #*# \n"
			+ " #* KM***** *  # \n"
			+ " # # # # # #*# # \n"
			+ " #* *   M *****# \n"
			+ " ############### \n"
			+ "                 @",
			  "                 \n"
			+ " ############### \n"	
			+ " # P *    * * *# \n"
			+ " # #*#*#*#####*# \n"
			+ " #  **** **  #K# \n"
			+ " # # # ###*#*#*# \n"
			+ " # *** #E# * M*# \n"
			+ " #*#M#*#X#*# # # \n"
			+ " # # *      M *# \n"
			+ " #*###*# # # #*# \n"
			+ " #*   ***** *# # \n"
			+ " # # # # ##### # \n"
			+ " #* *   M **K**# \n"
			+ " ############### \n"
			+ "                 @",
			  "                 \n"
			+ " ############### \n"	
			+ " #   *    *  XE# \n"
			+ " # #*#*#*####### \n"
			+ " #M **** **    # \n"
			+ " #K# # # #*#*#*# \n"
			+ " # ***  K  *  *# \n"
			+ " #*# #*# #*# # # \n"
			+ " # # *      M *# \n"
			+ " #####*# # # #*# \n"
			+ " #*   ***** *# # \n"
			+ " # ##### ##### # \n"
			+ " # P*     ** **# \n"
			+ " ############### \n"
			+ "                 @",
			  "                 \n"
			+ " ############### \n"	
			+ " #   *    *  XE# \n"
			+ " # #*#*#*####### \n"
			+ " # #**** **    # \n"
			+ " # # # #######*# \n"
			+ " # #**  # M*K#*# \n"
			+ " #*# ####### # # \n"
			+ " # #K*M #   M *# \n"
			+ " # ###*# # # #*# \n"
			+ " #* M ***** *# # \n"
			+ " #####*# ##### # \n"
			+ " # P      ** **# \n"
			+ " ############### \n"
			+ "                 @",
			  "                 \n"
			+ " ############### \n"	
			+ " #   *  P *    # \n"
			+ " # #*#*#*# # # # \n"
			+ " #  **** **    # \n"
			+ " # # ########### \n"
			+ " #  **    M* #K# \n"
			+ " ##########  # # \n"
			+ " # K *M     M *# \n"
			+ " # #  *######### \n"
			+ " #* M ***** *  # \n"
			+ " ########### # # \n"
			+ " #EX      ** **# \n"
			+ " ############### \n"
			+ "                 @"
			};

	public Board() {
		ImageIcon icon = new ImageIcon("image/Background.png");
		image = icon.getImage();
		initBoard();
		timer = new Timer(player, this);
		timer.start();
	}
	
	/**
	 *  보드 초기화
	 */
	private void initBoard() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		initWorld();
		monsterStart();
	}
	
    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }
    
    public void reduceKeyCount() {
    	if (keyCount != 0) {
			keyCount--;
    	}
    	setKeyCountStr(getKeyCountStr().substring(0, 6) + getKeyCount());
    }
    
    public int getKeyCount() {
		return keyCount;
	}
    
    public void setKeyCount(int keyCount) {
		this.keyCount = keyCount;
	}
    
    public String getLifeStr() {
		return lifeStr;
	}
    
    public void setLifeStr(String lifeStr) {
		this.lifeStr = lifeStr;
	}
    
    public String getPointStr() {
		return pointStr;
	}
    
    public void setPointStr(String pointStr) {
		this.pointStr = pointStr;
	}
    
    public int getLife() {
		return life;
	}
    
    public void setLife(int life) {
		this.life = life;
	}
    
    public int getPoint() {
		return point;
	}
    
    public void setPoint(int point) {
		this.point = point;
	}
    
    public int getBombCount() {
		return bombCount;
	}
    
    public void setBombCount(int bombCount) {
		this.bombCount = bombCount;
	}
    
    public String getKeyCountStr() {
		return keyCountStr;
	}
    
    public void setKeyCountStr(String keyCountStr) {
		this.keyCountStr = keyCountStr;
	}
    
    public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
    
    public String getTimeStr() {
		return timeStr;
	}
    
    public void openDoor() {
    	door.setX(EMPTY_SPACE);
    	door.setY(EMPTY_SPACE);
    	repaint();
    }
	
    /**
     * 오브젝트 초기 설정 및 보드 초기화
     */
	private void initWorld() {
		walls = new ArrayList<>();
		monsters = new ArrayList<>();
		keys = new ArrayList<>();
		obstacles = new ArrayList<>();
		spaces = new ArrayList<>();
		
		
		int x = OFFSET;
		int y = OFFSET;
		
		Wall wall;
		Monster monster;
		Key key;
		Obstacle obstacle;
		Space space;
		
		if (rand == -1) {
			rand = (int)(Math.random()*4);
		}
		
		level = rand;
		
		levelStr = "LEVEL : " + difficulty[level];		
		
		for (int i = 0; i < map[rand].length(); i++) {
			char item = map[rand].charAt(i);
			switch (item) {
			case '\n':
				y += SPACE;
				if (this.w < x) {
					this.w = x;
				}
				x = OFFSET;
				break;
			case '#':
				wall = new Wall(x, y);
				walls.add(wall);
				x += SPACE;
				break;
			case '*':
				obstacle = new Obstacle(x, y);
				obstacles.add(obstacle);
				x += SPACE;
				break;
			case 'M':
				monster = new Monster(x, y, this);
				monsters.add(monster);
				x += SPACE;
				break;
			case 'K':
				key = new Key(x, y);
				keys.add(key);
				x += SPACE;
				break;
			case 'P':
				player = new Player(x, y, this);
				x += SPACE;
				break;
			case 'E':
				exit = new Exit(x, y);
				x += SPACE;
				break;
			case 'X':
				door = new Door(x, y);
				x += SPACE;
				break;
			case '@':
				bomb = new Bomb(x, y, this);
				break;
			case ' ':
				space = new Space(x, y);
				spaces.add(space);
				x += SPACE;
				break;
			}
			h = y;
		}
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		buildWorld(g);
		buildGUI(g);
	}
	
	/**
	 *  이미지 그리기
	 */
	private void buildWorld(Graphics g) {
		g.drawImage(image, 0, 0, this);
		
		world = new ArrayList<>();
		world.addAll(spaces);
		world.addAll(walls);
		world.addAll(obstacles);
		world.addAll(monsters);
		world.addAll(keys);
		world.add(door);
		world.add(exit);
		world.add(bomb);
		world.add(player);
		
		for (int i = 0; i < world.size(); i++) {
			Actor item = world.get(i);
			if (item instanceof Player || item instanceof Monster ||  item instanceof Key) {
				g.drawImage(item.getImage(), item.getX()+2, item.getY()+2, this);
			} else {
				g.drawImage(item.getImage(), item.getX(), item.getY(), this);
			}
		}
	}
	
	
	/**
	 * gui 그리기
	 */
	private void buildGUI(Graphics g) {
		g.setColor(Color.WHITE);
		Font font = new Font("Arial", Font.BOLD, 24);
		g.setFont(font);
		
		ImageIcon icon = new ImageIcon("image/gui.png");
		Image image = icon.getImage();
		g.drawImage(image, 0, 0, this);
		
		g.drawString(levelStr, 200, 25);
		g.drawString(lifeStr, 777, 40);
		g.drawString(pointStr, 600, 40);
		g.drawString(keyCountStr, 200, 50);
		g.drawString(timeStr, 10, 40);
	}
	
	/**
	 * 키 이벤트
	 */
	private class TAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_LEFT:
				//충돌시 못 움직이게
				if (checkWallCollision(player, LEFT_COLLISION)) {
					return;
				}
				if (checkObstacleCollision(player, LEFT_COLLISION)) {
					return;
				}
				if (checkKeyCollision(player, LEFT_COLLISION)) {
					return;
				}
				if (checkDoorCollision(player, LEFT_COLLISION)) {
					return;
				}
				if (checkBombCollision(player, LEFT_COLLISION)) {
					return;
				}
				player.move(-SPACE, 0);
				break;
			case KeyEvent.VK_RIGHT:
				if (checkWallCollision(player, RIGHT_COLLISION)) {
					return;
				}
				if (checkObstacleCollision(player, RIGHT_COLLISION)) {
					return;
				}
				if (checkKeyCollision(player, RIGHT_COLLISION)) {
					return;
				}
				if (checkDoorCollision(player, RIGHT_COLLISION)) {
					return;
				}
				if (checkBombCollision(player, RIGHT_COLLISION)) {
					return;
				}
				player.move(SPACE, 0);
				break;
			case KeyEvent.VK_UP:
				if (checkWallCollision(player, TOP_COLLISION)) {
					return;
				}
				if (checkObstacleCollision(player, TOP_COLLISION)) {
					return;
				}
				if (checkKeyCollision(player, TOP_COLLISION)) {
					return;
				}
				if (checkDoorCollision(player, TOP_COLLISION)) {
					return;
				}
				if (checkBombCollision(player, TOP_COLLISION)) {
					return;
				}
				player.move(0, -SPACE);
				break;
			case KeyEvent.VK_DOWN:
				if (checkWallCollision(player, BOTTOM_COLLISION)) {
					return;
				}
				if (checkObstacleCollision(player, BOTTOM_COLLISION)) {
					return;
				}
				if (checkKeyCollision(player, BOTTOM_COLLISION)) {
					return;
				}
				if (checkDoorCollision(player, BOTTOM_COLLISION)) {
					return;
				}
				if (checkBombCollision(player, BOTTOM_COLLISION)) {
					return;
				}
				player.move(0, SPACE);
				break;
			case KeyEvent.VK_SPACE:
				// 폭탄 설치
				if (getBombCount() > 0) {
					bombStart();
					setBombCount(getBombCount()-1);
				}
				break;
			case KeyEvent.VK_T:
				player.increaseLife();
				break;
			}
			
			repaint();
		}
	}
	
	private void monsterStart() {
		for (int i = 0; i < monsters.size(); i++) {
			Thread thread = new Thread(monsters.get(i));
			thread.start();
		}
	}
	
	private void bombStart() {
		bomb.move(player.getX(), player.getY());
		Thread thread = new Thread(bomb);
		thread.start();
	}
	
	/**
	 *  벽과 충돌했는지 검사
	 * @param actor 오브젝튼
	 * @param type 방향
	 * @return 충돌 여부
	 */
	public boolean checkWallCollision(Actor actor, int type) {
		switch (type) {
		case LEFT_COLLISION:
			for (int i = 0; i < walls.size(); i++) {
				Wall wall = walls.get(i);
				if (actor.isLeftCollision(wall)) {
					return true;
				}
			}
			return false;
		case RIGHT_COLLISION:
			for (int i = 0; i < walls.size(); i++) {
				Wall wall = walls.get(i);
				if (actor.isRightCollision(wall)) {
					return true;
				}
			}
			return false;
		case TOP_COLLISION:
			for (int i = 0; i < walls.size(); i++) {
				Wall wall = walls.get(i);
				if (actor.isTopCollision(wall)) {
					return true;
				}
			}
			return false;
		case BOTTOM_COLLISION:
			for (int i = 0; i < walls.size(); i++) {
				Wall wall = walls.get(i);
				if (actor.isBottomCollision(wall)) {
					return true;
				}
			}
			return false;
		}
		return false;
	}
	
	/**
	 *  장애물과 충돌 했는지 검사
	 * @param actor 오브젝튼
	 * @param type 방향
	 * @return 충돌 여부
	 */
	public boolean checkObstacleCollision(Actor actor, int type) {
		switch (type) {
		case LEFT_COLLISION:
			for (int i = 0; i < obstacles.size(); i++) {
				Obstacle obstacle = obstacles.get(i);
				if (actor.isLeftCollision(obstacle)) {
					return true;
				}
			}
			return false;
		case RIGHT_COLLISION:
			for (int i = 0; i < obstacles.size(); i++) {
				Obstacle obstacle = obstacles.get(i);
				if (actor.isRightCollision(obstacle)) {
					return true;
				}
			}
			return false;
		case TOP_COLLISION:
			for (int i = 0; i < obstacles.size(); i++) {
				Obstacle obstacle = obstacles.get(i);
				if (actor.isTopCollision(obstacle)) {
					return true;
				}
			}
			return false;
		case BOTTOM_COLLISION:
			for (int i = 0; i < obstacles.size(); i++) {
				Obstacle obstacle = obstacles.get(i);
				if (actor.isBottomCollision(obstacle)) {
					return true;
				}
			}
			return false;
		}
		return false;
	}
	
	/**
	 *  키와 충돌 했는지 검사
	 * @param actor 오브젝튼
	 * @param type 방향
	 * @return 충돌 여부
	 */
	public boolean checkKeyCollision(Actor actor, int type) {
		switch (type) {
		case LEFT_COLLISION:
			for (int i = 0; i < keys.size(); i++) {
				Key key = keys.get(i);
				if (actor.isLeftCollision(key)) {
					return true;
				}
			}
			return false;
		case RIGHT_COLLISION:
			for (int i = 0; i < keys.size(); i++) {
				Key key = keys.get(i);
				if (actor.isRightCollision(key)) {
					return true;
				}
			}
			return false;
		case TOP_COLLISION:
			for (int i = 0; i < keys.size(); i++) {
				Key key = keys.get(i);
				if (actor.isTopCollision(key)) {
					return true;
				}
			}
			return false;
		case BOTTOM_COLLISION:
			for (int i = 0; i < keys.size(); i++) {
				Key key = keys.get(i);
				if (actor.isBottomCollision(key)) {
					return true;
				}
			}
			return false;
		}
		return false;
	}
	
	/**
	 *  문과 충돌했는지 검사
	 * @param actor 오브젝튼
	 * @param type 방향
	 * @return 충돌 여부
	 */
	public boolean checkDoorCollision(Actor actor, int type) {
		switch (type) {
		case LEFT_COLLISION:
			if (actor.isLeftCollision(door)) {
				return true;
			}
			return false;
		case RIGHT_COLLISION:
			if (actor.isRightCollision(door)) {
				return true;
			}
			return false;
		case TOP_COLLISION:
			if (actor.isTopCollision(door)) {
				return true;
			}
			return false;
		case BOTTOM_COLLISION:
			if (actor.isBottomCollision(door)) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	/**
	 *  폭탄과 충돌 했는지 검사
	 * @param actor 오브젝튼
	 * @param type 방향
	 * @return 충돌 여부
	 */
	public boolean checkBombCollision(Actor actor, int type) {
		switch (type) {
		case LEFT_COLLISION:
			if (actor.isLeftCollision(bomb)) {
				return true;
			}
			return false;
		case RIGHT_COLLISION:
			if (actor.isRightCollision(bomb)) {
				return true;
			}
			return false;
		case TOP_COLLISION:
			if (actor.isTopCollision(bomb)) {
				return true;
			}
			return false;
		case BOTTOM_COLLISION:
			if (actor.isBottomCollision(bomb)) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	private void resetPoint() {
		setPoint(0);
		setPointStr(String.valueOf(getPoint()));
	}
	
	private void resetKeyCount() {
		setKeyCount(KEY_COUNT_ORI);
		setKeyCountStr(getKeyCountStr().substring(0, 6) + getKeyCount());
	}
	
	public void restartLevel() {
		walls.clear();
		obstacles.clear();
		monsters.clear();
		door = null;
		
		timer.setTime();
		resetKeyCount();
		resetPoint();
		initWorld();
		monsterStart();
	}
}
