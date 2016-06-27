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
 * @author Ashkan Mehrkar
 */
public class GameState {
	int condition;
	int score;
	ImageIcon backGround;
	ImageIcon grass;
    ImageIcon draggedImage = null;
    public int tmpX = 0;
    public int tmpY = 0;
	MenuBar menuBar;
	LawnMower[] lawnMowers;
	private KeyHandler keyHandler;
	private MouseHandler mouseHandler;
	public ArrayList<Sun> suns;
	public ArrayList<Plant> plants;
	public ArrayList<Zombie> zombies;
    protected int zombieNumber = 0;
	private long last_sun = 0;
	{
		last_sun = System.currentTimeMillis();
	}
	private long last_Zombie = 0;
	{
		last_Zombie = System.currentTimeMillis();
	}
	private int cnt = 0;
	private long last_sunFlower = 0;
	private long last_peaShooter = 0;
	
	public GameState() {
		//
		// Initialize the game state and all elements ...
		//
		score = 200;
		suns = new ArrayList<Sun>();
		plants = new ArrayList<Plant>();
		zombies = new ArrayList<Zombie>();
		lawnMowers = new LawnMower[5];
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
                lawnMowers[0] = new LawnMower(3);
                /**
                 * here we randomly create new sun.
                 */
                double a = Math.random();
                if(a > 0.9999 - ((System.currentTimeMillis() - last_sun) / 1000) * 0.0005) {
                    suns.add(new Sun());
                    last_sun = System.currentTimeMillis();
                }
                /**
                 * this loop, changes the suns' Y.
                 */
                for(int i = 0; i < suns.size(); i++) {
                    if(suns.get(i).y < 600) {
                        suns.get(i).y = suns.get(i).y + 1;
                    }
                }
                /**
                 * here we randomly create new zombies and check the limitation of zombie's number for the level.
                 */
                double b = Math.random();
                if(b > 0.999 - ((System.currentTimeMillis() - last_Zombie) / 1000) * 0.00005 && zombieNumber < 9) {
                    zombies.add(new NormalZombie(3));
                    last_Zombie = System.currentTimeMillis();
                    zombieNumber++;
                }
                /**
                 * here we change the position of zombies which we have.
                 * at the same time we check if any striking is happening and then remove the bullet and decrease the zombie's health.
                 */
                cnt++;
                for(int i = 0; i < zombies.size(); i++) {
                    if(zombies.get(i).x + zombies.get(i).imageIcon.getIconWidth() > 0 && cnt % 3 == 0) {
                        zombies.get(i).x = zombies.get(i).x - 2;
                    }
                    for(int j = 0; j < plants.size(); j++) {
                        if(plants.get(j).getClass().equals(PeaShooter.class)) {
                            for(int k = 0; k < plants.get(j).bullets.size(); k++) {
                                if(plants.get(j).bullets.get(k).x >= zombies.get(i).x) {
                                    zombies.get(i).health--;
                                    plants.get(j).bullets.remove(k);
                                    if(zombies.get(i).health < 1)
                                        zombies.remove(i);
                                }
                            }
                        }
                    }

                }
                /**
                 * we update the bullets' position.
                 * at the same time we check if any striking is happening or not.
                 */
                for(Plant plant : plants) {
                    if(plant.getClass().equals(PeaShooter.class)) {
                        for(int i = 0; i < plant.bullets.size(); i++) {
                            if(plant.bullets.get(i).x > 800)
                                plant.bullets.remove(i);
                            else
                                plant.bullets.get(i).x = plant.bullets.get(i).x + 7;
                            for(int j = 0; j < zombies.size(); j++) {
                                if(plant.bullets.get(i).x >= zombies.get(j).x) {
                                    plant.bullets.remove(i);
                                    zombies.get(j).health--;
                                    if(zombies.get(j).health < 1)
                                        zombies.remove(j);
                                }
                            }
                        }
                    }
                }
                /**
                 * here we check if we have any attacking plants for the row at which the zombies are walking through so plants shoots bullets.
                 */
                for(int i = 0; i < zombies.size(); i++) {
                    for(Plant plant : plants) {
                        if(plant.getClass().equals(PeaShooter.class) && plant.row == zombies.get(i).row) {
                            if(System.currentTimeMillis() - plant.last_bullet > 2000 && plant.x < zombies.get(i).x && zombies.get(i).x < 770) {
                                plant.bullets.add(new PeaShooterBullet(plant.x, plant.y));
                                plant.last_bullet = System.currentTimeMillis();
                            }
                        }
                    }
                }
                /**
                 * WaVe!!! :D
                 */
                if(zombieNumber == 8) {
                    for(int i = 0; i < 5; i++)
                        zombies.add(new NormalZombie(3));
                }
                break;
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
						}
					}
					System.out.println(e.getX() + "   " + e.getY());
					break;
			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
            switch (condition)  {
                case 1:
                    if(92 < e.getX() && e.getX() < 143 && 28 < e.getY() && e.getY() < 100 && score >= 50 && System.currentTimeMillis() - last_sunFlower > 3000) {
                        draggedImage = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/sunFlower.png");
                        tmpX = e.getX() - draggedImage.getIconWidth()/2;
                        tmpY = e.getY() - draggedImage.getIconHeight();
						last_sunFlower = System.currentTimeMillis();
                    }
                    else if(150 < e.getX() && e.getX() < 200 && 30 < e.getY() && e.getY() < 100 && score >=100 && System.currentTimeMillis() - last_peaShooter > 5000) {
                        draggedImage = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/peaShooter.png");
                        tmpX = e.getX() - draggedImage.getIconWidth()/2;
                        tmpY = e.getY() - draggedImage.getIconHeight();
						last_peaShooter = System.currentTimeMillis();
                    }
            }
		}

		@Override
		public void mouseReleased(MouseEvent e) {
            int column = 0, row = 0;
            ImageIcon peaShooter = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/peaShooter.png");
            ImageIcon sunFlower = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/sunFlower.png");
            switch (condition) {
                case 1:
                    if(draggedImage != null) {
                        if(274 < e.getY() && e.getY() < 378) {
                            row = 3;
                            if(32 < e.getX() && e.getX() < 108)
                                column = 1;
                            else if(108 <= e.getX() && e.getX() < 183)
                                column = 2;
                            else if(183 <= e.getX() && e.getX() < 265)
                                column = 3;
                            else if(265 <= e.getX() && e.getX() < 352)
                                column = 4;
                            else if(352 <= e.getX() && e.getX() < 431)
                                column = 5;
                            else if(431 <= e.getX() && e.getX() < 517)
                                column = 6;
                            else if(517 <= e.getX() && e.getX() < 593)
                                column = 7;
                            else if(593 <= e.getX() && e.getX() < 667)
                                column = 8;
                            else if(667 <= e.getX() && e.getX() <= 756)
                                column = 9;
                            else
                                draggedImage = null;

                        }
                        else
                            draggedImage = null;
                        if(draggedImage != null) {
                            if(draggedImage.getImage() == peaShooter.getImage()) {
                                plants.add(new PeaShooter(column, row));
                                draggedImage = null;
								score = score - 100;
                            }
                            else if(draggedImage.getImage() == sunFlower.getImage()) {
                                plants.add(new SunFlower(column, row));
                                draggedImage = null;
								score = score - 50;
                            }
                        }

                    }
                    /*if(draggedImage != null) {
                        if(32 < e.getX() && e.getX() < 108)
                            x = 1;
                        else if(108 <= e.getX() && e.getX() < 183)
                            x = 2;
                        else if(183 <= e.getX() && e.getX() < 265)
                            x = 3;
                        else if(265 <= e.getX() && e.getX() < 352)
                            x = 4;
                        else if(352 <= e.getX() && e.getX() < 431)
                            x = 5;
                        else if(431 <= e.getX() && e.getX() < 517)
                            x = 6;
                        else if(517 <= e.getX() && e.getX() < 593)
                            x = 7;
                        else if(593 <= e.getX() && e.getX() < 667)
                            x = 8;
                        else if(667 <= e.getX() && e.getX() <= 756)
                            x = 9;
                        else
                            draggedImage = null;
                        if(110 < e.getY() && e.getY() < 181)
                            y = 1;
                        else if(181 < e.getY() && e.getY() < 274)
                            y = 2;
                        else if(274 < e.getY() && e.getY() < 378)
                            y = 3;
                        else if(378 < e.getY() && e.getY() < 468)
                            y = 4;
                        else if(468 < e.getY() && e.getY() < 571)
                            y = 5;
                        else
                            draggedImage = null;
                        if(draggedImage != null)
                            plants.add(new PeaShooter(x, y));
                        draggedImage = null;
                    }*/
            }
        }

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void mouseDragged(MouseEvent e) {
            switch (condition)  {
                case 1:
                    if(draggedImage != null) {
                        tmpX = e.getX() - draggedImage.getIconWidth()/2;
                        tmpY = e.getY() - draggedImage.getIconHeight();
                    }
            }
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

