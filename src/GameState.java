/*** In The Name of Allah ***/

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * This class holds the state of the game and all of its elements.
 * This class also handles user inputs, which affect the game state.
 * 
 * @author Seyed Mohammad Ghaffarian
 */
public class GameState {
	int condition;
	int score;
	ImageIcon backGround;
	ImageIcon grass;
	MenuBar menuBar;
	ChamanZan[] chamanZans;
	private KeyHandler keyHandler;
	private MouseHandler mouseHandler;
	public ArrayList<Sun> suns;
	public ArrayList<Plant> plants;
	public ArrayList<Zombie> zombies;
	private long last_sun = 0;
	{
		last_sun = System.currentTimeMillis();
	}
	private long last_Zombie = 0;
	{
		last_Zombie = System.currentTimeMillis();
	}
	private long last_bullet = 0;
	private int cnt = 0;
	
	public GameState() {
		//
		// Initialize the game state and all elements ...
		//
		score = 50;
		suns = new ArrayList<Sun>();
		//suns.add(new Sun());
		plants = new ArrayList<Plant>();
		plants.add(new PeaShooter(3));
		zombies = new ArrayList<Zombie>();
		chamanZans = new ChamanZan[5];
		condition = -3;
		keyHandler = new KeyHandler();
		mouseHandler = new MouseHandler();
	}
	
	/**
	 * The method which updates the game state.
	 */
	public void update(GameFrame gameFrame) {
		//
		// Update the state of all game elements
		//  based on user input and elapsed time ...
		//
		switch (condition) {
			case -3:
				backGround = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/firstPage.jpg");
				break;
			case -2:
				backGround = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/firstPage1.jpg");
				break;
			case -1:
				backGround = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/menu.jpg");
				break;
			case 0:
				backGround = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/menu1.jpg");
				break;
			case 1:
				backGround = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/backGround1.jpg");
				grass = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/chaman1.png");
				menuBar = new MenuBar();
				chamanZans[0] = new ChamanZan(3);
				for(int i = 0; i < suns.size(); i++) {
					if(suns.get(i).y < 600)
						suns.get(i).y = suns.get(i).y + 1;
				}
				double a = Math.random();
				if(a > 0.9999 - ((System.currentTimeMillis() - last_sun) / 1000) * 0.0005) {
					suns.add(new Sun());
					last_sun = System.currentTimeMillis();
				}
				cnt++;
				for(int i = 0; i < zombies.size(); i++) {
					if(zombies.get(i).x + zombies.get(i).imageIcon.getIconWidth() > 0 && cnt % 3 == 0)
						zombies.get(i).x = zombies.get(i).x - 1;
				}
				double b = Math.random();
				if(b > 0.999 - ((System.currentTimeMillis() - last_Zombie) / 1000) * 0.00005 && zombies.size() < 21) {
					zombies.add(new NormalZombie(3));
					last_Zombie = System.currentTimeMillis();
				}
				for(int i = 0; i < zombies.size(); i++) {
					if(zombies.get(i).row ==3) {
						for(int j = 0; j < plants.size(); j++) {
							if(plants.get(j).row == 3) {
								if(System.currentTimeMillis() - last_bullet > 2000) {
									plants.get(j).bullets.add(new PeaShooterBullet(plants.get(j).x, plants.get(j).y));
									last_bullet = System.currentTimeMillis();
								}
								for (int k = 0; k < plants.get(j).bullets.size(); k++)
									plants.get(j).bullets.get(k).x = plants.get(j).bullets.get(k).x + 3;
							}
						}
					}
				}
		}
	}
	
	
	public KeyListener getKeyListener() {
		return keyHandler;
	}
	public MouseListener getMouseListener() {
		return mouseHandler;
	}
	public MouseMotionListener getMouseMotionListener() {
		return mouseHandler;
	}



	/**
	 * The keyboard handler.
	 */
	class KeyHandler implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

	}

	/**
	 * The mouse handler.
	 */
	class MouseHandler implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			switch (condition) {
				case -2:
					if(250 < e.getX() && e.getX() < 548 && 547 < e.getY() && e.getY() < 579)
						condition = -1;
				case 0:
					if(407 < e.getX() && e.getX() < 729 && 97 < e.getY() && e.getY() < 203)
						condition = 1;
					break;
				case 1:
					for(int i = 0; i < suns.size(); i++) {
						suns.get(i).getSun(e);
						if(suns.get(i).existence == false) {
							suns.remove(i);
							score = score + 25;
							System.out.println(score);
						}
					}
					break;
			}
			System.out.println(e.getX() + "   " + e.getY());
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void mouseDragged(MouseEvent e) {
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			switch (condition) {
				case -3 :
					if(250 < e.getX() && e.getX() < 548 && 547 < e.getY() && e.getY() < 579)
						condition = -2;
					else
						condition = -3;
					break;
				case -2:
					if(250 < e.getX() && e.getX() < 548 && 547 < e.getY() && e.getY() < 579)
						condition = -2;
					else
						condition = -3;
					break;
				case -1:
					if(407 < e.getX() && e.getX() < 729 && 97 < e.getY() && e.getY() < 203)
						condition = 0;
					else
						condition = -1;
					break;
				case 0:
					if(407 < e.getX() && e.getX() < 729 && 97 < e.getY() && e.getY() < 203)
						condition = 0;
					else
						condition = -1;
					break;
			}
		}
	}
}

