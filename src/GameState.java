import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class holds the state of the game and all of its elements.
 * This class also handles user inputs, which affect the game state.
 * 
 * @author Ashkan Mehrkar
 */
public class GameState {
    public int condition;
    public int score;
	public ImageIcon backGround;
    public ImageIcon draggedImage;
    public int tmpX;
    public int tmpY;
    public GamePanel menuBar;
	public ArrayList<LawnMower> lawnMowers;
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
    private long last_walnut = 0;
    private long last_snowPea = 0;
    private long last_cherryBomb = 0;
    private int cnt1 = 0;
    private int cnt2 = 0;
    private int wave = 0;
	
	public GameState() {
		//
		// Initialize the game state and all elements ...
		//
        draggedImage = null;
        tmpY = 0;
        tmpX = 0;
		score = 400;
		suns = new ArrayList<Sun>();
		plants = new ArrayList<Plant>();
		zombies = new ArrayList<Zombie>();
		lawnMowers = new ArrayList<LawnMower>();
        for(int i = 0; i < 5; i++) {
            lawnMowers.add(new LawnMower(i+1));
        }
		condition = 3;
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
				backGround = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/backGround1.png");
				menuBar = new GamePanel();

                sunCreating();

                sunMovement();
                /**
                 * here we randomly create new zombies and check the limitation of zombie's number for the level.
                 */
                double b = Math.random();
                if(b > 0.999 - ((System.currentTimeMillis() - last_Zombie) / 1000) * 0.00005 && zombieNumber < 7) {
                    zombies.add(new NormalZombie(3));
                    last_Zombie = System.currentTimeMillis();
                    zombieNumber++;
                }

                zombieMovement();

                lawnMoverCheck();

                bulletMovement();

                bulletShooting();

				sunFlowerProduction();
                /**
                 * WaVe!!!! :D
                 */
                if(zombieNumber == 7 && zombies.size() == 0 )
                    wave = 1;

                if(wave == 1){
                    cnt2++;
                    if(cnt2 % 100 == 0 && zombieNumber < 12) {
                        randomZombieCreator(condition);
                    }
                }
                if(zombieNumber == 12 && zombies.size()== 0){
                    infoReset();
                    condition = 2;
                }

                break;
            case 2:
                backGround = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/backGround3.png");

                menuBar = new GamePanel();

                sunCreating();

                sunMovement();
                /**
                 * randomly creating zombies.
                 * checking zombies limitation.
                 * checking which kind of zombie to create.
                 */
                double b1 = Math.random();
                if(b1 > 0.997 - ((System.currentTimeMillis() - last_Zombie) / 1000) * 0.00005 && zombieNumber < 10) {
                    Random random = new Random();
                    int row = random.nextInt(4) + 2;
                    int kind = random.nextInt(2) + 1;
                    if(2 <= row && row <=4 && 1<=kind && kind<= 2){
                        if(kind ==1) {
                            zombies.add(new NormalZombie(row));
                            last_Zombie = System.currentTimeMillis();
                            zombieNumber++;
                        }
                        else if(kind ==2) {
                            zombies.add(new BucketHeadZombie(row));
                            last_Zombie = System.currentTimeMillis();
                            zombieNumber++;
                        }
                    }
                }

                zombieMovement();

                lawnMoverCheck();

                bulletMovement();

                bulletShooting();

                sunFlowerProduction();

                /**
                 * WaVe!!!! :D
                 */
                if(zombieNumber == 10 && zombies.size() == 0) {
                    wave = 1;
                    System.out.println("wave 1");
                }

                if(wave == 1){
                    cnt2++;
                    if(cnt2 % 100 == 0 && zombieNumber < 14)
                        randomZombieCreator(condition);
                }
                if(zombieNumber == 14 && zombies.size() == 0) {
                    wave = 2;
                    System.out.println("wave 2");
                }

                if(wave ==2) {
                    cnt2++;
                    if(cnt2 % 100 == 0 && zombieNumber < 18)
                        randomZombieCreator(2);
                }
                if(zombieNumber == 18 && zombies.size() == 0) {
                    infoReset();
                    condition = 3;
                }
                break;
            case 3:
                backGround = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/backGround3.png");

                menuBar = new GamePanel();

                sunCreating();

                sunMovement();
                /**
                 * randomly creating zombies.
                 * checking zombies limitation.
                 * checking which kind of zombie to create.
                 */
                double b3 = Math.random();
                if(b3 > 0.997 - ((System.currentTimeMillis() - last_Zombie) / 1000) * 0.00005 && zombieNumber < 13) {
                    Random random = new Random();
                    int row = random.nextInt(4) + 2;
                    int kind = random.nextInt(3) + 1;
                    System.out.println("row:  " + row + "  kind:  "  +kind);
                    if(2 <= row && row <=4 && 1<=kind && kind<= 3){
                        if(kind ==1) {
                            zombies.add(new NormalZombie(row));
                            last_Zombie = System.currentTimeMillis();
                            zombieNumber++;
                        }
                        else if(kind == 2) {
                            zombies.add(new BucketHeadZombie(row));
                            last_Zombie = System.currentTimeMillis();
                            zombieNumber++;
                        }
                        else if(kind == 3) {
                            zombies.add(new PoleVaultingZombie(row));
                            last_Zombie = System.currentTimeMillis();
                            zombieNumber++;
                        }
                    }
                }
                zombieMovement();

                lawnMoverCheck();

                bulletMovement();

                bulletShooting();

                sunFlowerProduction();
                /**
                 * WaVe!!! :D
                 */
                /**
                 * WaVe!!!! :D
                 */
                if(zombieNumber == 13 && zombies.size() == 0) {
                    wave = 1;
                    System.out.println("wave 1");
                }

                if(wave == 1){
                    cnt2++;
                    if(cnt2 % 100 == 0 && zombieNumber < 14)
                        randomZombieCreator(condition);
                }
                if(zombieNumber == 14 && zombies.size() == 0) {
                    wave = 2;
                    System.out.println("wave 2");
                }

                if(wave ==2) {
                    cnt2++;
                    if(cnt2 % 100 == 0 && zombieNumber < 18)
                        randomZombieCreator(2);
                }
                if(zombieNumber == 18 && zombies.size() == 0)
                    wave = 3;
                if(wave == 3) {
                    cnt2++;
                    if(cnt2 % 100 == 0 && zombieNumber < 23)
                        randomZombieCreator(condition);
                }
                if(zombieNumber == 23 && zombies.size() == 0) {
                    infoReset();
                    condition = 4;
                }
                break;
            case 4:
                backGround = new ImageIcon("/Images/backGround5.jpg");

                menuBar = new GamePanel();

                sunCreating();

                sunMovement();

                /**
                 * randomly creating zombies.
                 * checking zombies limitation.
                 * checking which kind of zombie to create.
                 */
                double b4 = Math.random();
                if(b4 > 0.996 - ((System.currentTimeMillis() - last_Zombie) / 1000) * 0.00005 && zombieNumber < 15) {
                    Random random = new Random();
                    int row = random.nextInt(5) + 1;
                    int kind = random.nextInt(3) + 1;
                    System.out.println("row:  " + row + "  kind:  "  +kind);
                    if(1 <= row && row <=5 && 1<=kind && kind<= 3){
                        if(kind ==1) {
                            zombies.add(new NormalZombie(row));
                            last_Zombie = System.currentTimeMillis();
                            zombieNumber++;
                        }
                        else if(kind == 2) {
                            zombies.add(new BucketHeadZombie(row));
                            last_Zombie = System.currentTimeMillis();
                            zombieNumber++;
                        }
                        else if(kind == 3) {
                            zombies.add(new PoleVaultingZombie(row));
                            last_Zombie = System.currentTimeMillis();
                            zombieNumber++;
                        }
                    }
                }
                zombieMovement();

                lawnMoverCheck();

                bulletMovement();

                bulletShooting();

                sunFlowerProduction();







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
                case 4:
                case 3:
                case 2:
                case 1:
					for(int i = 0; i < suns.size(); i++) {
						suns.get(i).getSun(e);
						if(suns.get(i).existence == false) {
							suns.remove(i);
                            score = score + 25;
                            i--;
                            continue;
						}
					}
					System.out.println(e.getX() + "   " + e.getY());
					break;

			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
            switch (condition)  {
                case 4:
                    if(323 <= e.getX() && e.getX() <=376 && 29 <= e.getY() && e.getY() <=102 && score>=175 && System.currentTimeMillis() - last_cherryBomb > 15000) {
                        draggedImage = new ImageIcon("/Images/cherryBombIcon.png");
                        tmpX = e.getX() - draggedImage.getIconWidth()/2;
                        tmpY = e.getY() - draggedImage.getIconHeight();
                        last_cherryBomb = System.currentTimeMillis();
                    }
                case 3:
                    if(265 <= e.getX() && e.getX() <=318 && 29 <= e.getY() && e.getY() <=102 && score>=175 && System.currentTimeMillis() - last_snowPea > 5000) {
                        draggedImage = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/snowPeaShooter.png");
                        tmpX = e.getX() - draggedImage.getIconWidth()/2;
                        tmpY = e.getY() - draggedImage.getIconHeight();
                        last_snowPea = System.currentTimeMillis();
                    }
                case 2:
                    if(207<=e.getX() && e.getX()<=260 && 27<e.getY() && e.getY()<=101 && score>=50 && System.currentTimeMillis() - last_walnut > 7000) {
                        draggedImage = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/walnut.png");
                        tmpX = e.getX() - draggedImage.getIconWidth()/2;
                        tmpY = e.getY() - draggedImage.getIconHeight();
                        last_walnut = System.currentTimeMillis();
                    }
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
                    break;
            }
		}

		@Override
		public void mouseReleased(MouseEvent e) {
            int column = 0, row = 0;
            ImageIcon peaShooter = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/peaShooter.png");
            ImageIcon sunFlower = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/sunFlower.png");
            ImageIcon walnut = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/walnut.png");
            ImageIcon snowPeaShooter = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/snowPeaShooter.png");
            ImageIcon cherryBomb = new ImageIcon("/Images/cherryBombIcon.png");
            switch (condition) {
                case 4:
                    if(draggedImage != null) {
                        row = rowIdentifier(e);
                        column = columnIdentifier(e);
                        if(row == 0 || column == 0)
                            draggedImage = null;
                    }
                    else
                        draggedImage = null;
                    if(draggedImage != null) {
                        if(draggedImage.getImage() == peaShooter.getImage() && freeSpace(column, row) && 2 <= row && row <= 4) {
                            plants.add(new PeaShooter(column, row));
                            draggedImage = null;
                            score = score - 100;
                        }
                        else if(draggedImage.getImage() == sunFlower.getImage() && freeSpace(column, row) && 2 <= row && row <= 4) {
                            plants.add(new SunFlower(column, row));
                            draggedImage = null;
                            score = score - 50;
                        }
                        else if(draggedImage.getImage() == walnut.getImage() && freeSpace(column, row) && 2 <= row && row <= 4) {
                            plants.add(new Walnut(column, row));
                            draggedImage = null;
                            score = score - 50;
                        }
                        else if(draggedImage.getImage() == snowPeaShooter.getImage() && freeSpace(column, row) && 2 <= row && row <= 4) {
                            plants.add(new SnowPeaShooter(column, row));
                            draggedImage = null;
                            score = score - 175;
                        }
                        else if(draggedImage.getImage().equals(cherryBomb.getImage()) && freeSpace(column, row) && 1<=row && row<=5) {
                            plants.add(new CherryBomb(column, row));
                            draggedImage = null;
                            score = score - 175;
                        }
                        else
                            draggedImage = null;
                    }
                case 3:
                    if(draggedImage != null) {
                        row = rowIdentifier(e);
                        column = columnIdentifier(e);
                        if(row == 0 || column == 0)
                            draggedImage = null;
                    }
                    else
                        draggedImage = null;
                    if(draggedImage != null) {
                        if(draggedImage.getImage() == peaShooter.getImage() && freeSpace(column, row) && 2 <= row && row <= 4) {
                            plants.add(new PeaShooter(column, row));
                            draggedImage = null;
                            score = score - 100;
                        }
                        else if(draggedImage.getImage() == sunFlower.getImage() && freeSpace(column, row) && 2 <= row && row <= 4) {
                            plants.add(new SunFlower(column, row));
                            draggedImage = null;
                            score = score - 50;
                        }
                        else if(draggedImage.getImage() == walnut.getImage() && freeSpace(column, row) && 2 <= row && row <= 4) {
                            plants.add(new Walnut(column, row));
                            draggedImage = null;
                            score = score - 50;
                        }
                        else if(draggedImage.getImage() == snowPeaShooter.getImage() && freeSpace(column, row) && 2 <= row && row <= 4) {
                            plants.add(new SnowPeaShooter(column, row));
                            draggedImage = null;
                            score = score - 175;
                        }
                        else
                            draggedImage = null;
                    }
                    break;
                case 2:
                    if(draggedImage != null) {
                        row = rowIdentifier(e);
                        column = columnIdentifier(e);
                        if(row == 0 || column == 0)
                            draggedImage = null;
                    }
                    else
                        draggedImage = null;
                    if(draggedImage != null) {
                        if(draggedImage.getImage() == peaShooter.getImage() && freeSpace(column, row) && 2 <= row && row <= 4) {
                            plants.add(new PeaShooter(column, row));
                            draggedImage = null;
                            score = score - 100;
                        }
                        else if(draggedImage.getImage() == sunFlower.getImage() && freeSpace(column, row) && 2 <= row && row <= 4) {
                            plants.add(new SunFlower(column, row));
                            draggedImage = null;
                            score = score - 50;
                        }
                        else if(draggedImage.getImage() == walnut.getImage() && freeSpace(column, row) && 2 <= row && row <= 4) {
                            plants.add(new Walnut(column, row));
                            draggedImage = null;
                            score = score - 50;
                        }
                        else
                            draggedImage = null;
                    }
                    break;
                case 1:
                    if(draggedImage != null) {
                        row = rowIdentifier(e);
                        column = columnIdentifier(e);
                        if(row == 0 || column == 0)
                            draggedImage = null;

                        }
                        else
                            draggedImage = null;
                        if(draggedImage != null) {
                            if(draggedImage.getImage() == peaShooter.getImage() && freeSpace(column, row) && row ==3) {
                                plants.add(new PeaShooter(column, row));
                                draggedImage = null;
								score = score - 100;
                            }
                            else if(draggedImage.getImage() == sunFlower.getImage() && freeSpace(column, row) && row ==3) {
                                plants.add(new SunFlower(column, row));
                                draggedImage = null;
								score = score - 50;
                            }
                            else
                                draggedImage = null;
                        }

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
                case 4:
                case 3:
                case 2:
                case 1:
                    if(draggedImage != null) {
                        tmpX = e.getX() - draggedImage.getIconWidth()/2;
                        tmpY = e.getY() - draggedImage.getIconHeight();
                    }
                    break;
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
    public boolean freeSpace(int column, int row) {
        for(Plant plant : plants) {
            if(plant.row == row && plant.column == column)
                return false;
        }
        return true;
    }
    public boolean availableMoving(Zombie zombie) {
        for(int i  =0; i < plants.size(); i++) {
            if(plants.get(i).row == zombie.row && plants.get(i).x + plants.get(i).imageIcon.getIconWidth() >= zombie.x - 2 && plants.get(i).x <= zombie.x)
                return false;
        }
        return true;
    }
    private void infoReset() {
        draggedImage = null;
        score = 50;
        tmpY = 0;
        tmpX = 0;
        last_sun = System.currentTimeMillis();
        last_Zombie = System.currentTimeMillis();
        cnt = 0;
        last_sunFlower = 0;
        last_peaShooter = 0;
        cnt1 = 0;
        cnt2 = 0;
        wave = 0;
    }
    /**
     * updating zombies' position.
     * at the same time we check if any striking is happening and then remove the bullet and decrease the zombie's health.
     * at the same time we check if there is any plants in front  of the zombie so it attacks the plants.
     */
    private void zombieMovement() {
        cnt++;
        zombieLoop:
        for(int i = 0; i < zombies.size(); i++) {
            if(zombies.get(i).x + zombies.get(i).imageIcon.getIconWidth() > 0 && cnt % zombies.get(i).speed == 0 && availableMoving(zombies.get(i))) {
                zombies.get(i).x = zombies.get(i).x - 2;
                if(zombies.get(i).x <= 53) {
                    for(int z = 0; z < lawnMowers.size(); z++) {
                        if(zombies.get(i).row ==lawnMowers.get(z).row && !lawnMowers.get(z).turnOn) {
                            lawnMowers.get(z).turnOn = true;
                        }
                    }
                }
            }
            else if(!availableMoving(zombies.get(i))) {
                if(zombies.get(i).getClass().equals(PoleVaultingZombie.class) && zombies.get(i).jumping) {
                    zombies.get(i).x = zombies.get(i).x - 82 - zombies.get(i).imageIcon.getIconWidth();
                    zombies.get(i).jumping = false;
                }
                else {
                    cnt1++;
                    if(cnt1 % 20 == 0) {
                        for(int s = 0; s < plants.size(); s++) {
                            if(plants.get(s).row == zombies.get(i).row && plants.get(s).x + plants.get(s).imageIcon.getIconWidth() >= zombies.get(i).x - 2 && plants.get(s).x <= zombies.get(i).x) {
                                plants.get(s).health--;
                                if(plants.get(s).health < 0) {
                                    plants.remove(s);
                                    s--;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            for(int j = 0; j < plants.size(); j++) {
                if(plants.get(j).getClass().equals(PeaShooter.class)) {
                    for(int k = 0; k < plants.get(j).bullets.size(); k++) {
                        if(plants.get(j).bullets.get(k).x >= zombies.get(i).x - 7 && plants.get(j).bullets.get(k).x <= zombies.get(i).x && plants.get(j).row == zombies.get(i).row) {
                            plants.get(j).bullets.remove(k);
                            k--;
                            if(plants.get(j).getClass().equals(SnowPeaShooter.class))
                                zombies.get(i).health = zombies.get(i).health - 2;
                            else
                                zombies.get(i).health--;
                            if(zombies.get(i).health < 1) {
                                zombies.remove(i);
                                i--;
                                continue zombieLoop;
                            }
                        }
                    }
                }
            }

        }
    }
    /**
     * here we randomly create new sun.
     */
    private void sunCreating() {
        double a1 = Math.random();
        if(a1 > 0.9999 - ((System.currentTimeMillis() - last_sun) / 1000) * 0.0005) {
            suns.add(new Sun());
            last_sun = System.currentTimeMillis();
        }
    }
    /**
     * this loop, changes the suns' Y.
     * also checks if we reached end of the frame and removes the sun.
     */
    private void sunMovement() {
        for(int i = 0; i < suns.size(); i++) {
            if(suns.get(i).y < 600) {
                suns.get(i).y = suns.get(i).y + 1;
            }
            else {
                suns.remove(i);
                i--;
                if(suns == null)
                    continue;
            }
        }
    }
    /**
     * checking lawnmower states and activating the if necessary.
     */
    private void lawnMoverCheck() {
        for(int i = 0 ; i < lawnMowers.size(); i++) {
            if(lawnMowers.get(i).turnOn) {
                if(lawnMowers.get(i).x < 800)
                    lawnMowers.get(i).move();
                else {
                    lawnMowers.remove(i);
                    i--;
                    continue;
                }
                for(int j = 0; j < zombies.size(); j++) {
                    if(zombies.get(j).row == lawnMowers.get(i).row && lawnMowers.get(i).x >= zombies.get(j).x - 5 && lawnMowers.get(i).x <= zombies.get(j).x) {
                        zombies.remove(j);
                        j--;
                    }
                }
            }
        }
    }
    /**
     * we update the bullets' position.
     * at the same time we check if any striking is happening or not.
     */
    private void bulletMovement() {
        for(int i = 0; i < plants.size(); i++) {
            if(plants.get(i).getClass().equals(PeaShooter.class) || plants.get(i).getClass().equals(SnowPeaShooter.class)) {
                for(int j = 0; j < plants.get(i).bullets.size(); j++) {
                    if(plants.get(i).bullets.get(j).x > 800) {
                        plants.get(i).bullets.remove(j);
                        j--;
                        continue;
                    }
                    else
                        plants.get(i).bullets.get(j).x = plants.get(i).bullets.get(j).x + 7;
                    for(int k = 0; k < zombies.size(); k++) {
                        if(zombies.get(k).row == plants.get(i).row) {
                            if(zombies.get(k).x - 7 <= plants.get(i).bullets.get(j).x && plants.get(i).bullets.get(j).x <= zombies.get(k).x) {
                                if(plants.get(i).getClass().equals(SnowPeaShooter.class))
                                    zombies.get(k).health = zombies.get(k).health - 2;
                                else
                                    zombies.get(k).health--;
                                if(zombies.get(k).health < 1) {
                                    zombies.remove(k);
                                    k--;
                                }
                                plants.get(i).bullets.remove(j);
                                j--;
                                break;
                            }
                        }
                    }

                }
            }
        }
    }
    /**
     * here we check if we have any attacking plants for the row at which the zombies are walking through so plants shoots bullets.
     */
    private void bulletShooting() {
        for(int i = 0; i < zombies.size(); i++) {
            for(Plant plant : plants) {
                if(plant.getClass().equals(PeaShooter.class) && plant.row == zombies.get(i).row) {
                    if(System.currentTimeMillis() - plant.last_bullet > 2000 && plant.x < zombies.get(i).x && zombies.get(i).x < 770) {
                        plant.bullets.add(new PeaShooterBullet(plant.x, plant.y));
                        plant.last_bullet = System.currentTimeMillis();
                    }
                }
                else if(plant.getClass().equals(SnowPeaShooter.class) && plant.row == zombies.get(i).row) {
                    if(System.currentTimeMillis() - plant.last_bullet > 2000 && plant.x < zombies.get(i).x && zombies.get(i).x < 770) {
                        System.out.println("shoooot");
                        plant.bullets.add(new SnowPeaShooterBullet(plant.x, plant.y));
                        plant.last_bullet = System.currentTimeMillis();
                    }
                }
            }
        }
    }
    /**
     * in this loop we check the state of all sunFlowers and produce sun.
     */
    private void sunFlowerProduction() {
        for(Plant plant : plants) {
            if(plant.getClass().equals(SunFlower.class)) {
                if(System.currentTimeMillis() - plant.last_sun > 25000) {
                    suns.add(new Sun(plant.x, plant.y));
                    plant.last_sun = System.currentTimeMillis();
                }
            }
        }
    }
    private int columnIdentifier(MouseEvent e) {
        if(32 < e.getX() && e.getX() < 108)
            return  1;
        else if(108 <= e.getX() && e.getX() < 183)
            return  2;
        else if(183 <= e.getX() && e.getX() < 265)
            return  3;
        else if(265 <= e.getX() && e.getX() < 352)
            return  4;
        else if(352 <= e.getX() && e.getX() < 431)
            return  5;
        else if(431 <= e.getX() && e.getX() < 517)
            return  6;
        else if(517 <= e.getX() && e.getX() < 593)
            return  7;
        else if(593 <= e.getX() && e.getX() < 667)
            return  8;
        else if(667 <= e.getX() && e.getX() <= 756)
            return  9;
        else
            return 0;
    }
    private int rowIdentifier(MouseEvent e) {
        if(110 < e.getY() && e.getY() < 181)
            return 1;
        else if(181 < e.getY() && e.getY() < 274)
            return 2;
        else if(274 < e.getY() && e.getY() < 378)
            return 3;
        else if(378 < e.getY() && e.getY() < 468)
            return 4;
        else if(468 < e.getY() && e.getY() < 571)
            return 5;
        else
            return 0;
    }
    private void randomZombieCreator(int condition) {
        Random random = new Random();
        int row, kind;
        switch (condition) {
            case 3:
                row = random.nextInt(4) + 2;
                kind = random.nextInt(2) + 1;
                if(2 <= row && row <=4 && 1<=kind && kind<= 3){
                    if(kind == 1) {
                        zombies.add(new NormalZombie(row));
                        zombieNumber++;
                    }
                    else if(kind == 2) {
                        zombies.add(new BucketHeadZombie(row));
                        zombieNumber++;
                    }
                    else if(kind == 3){
                        zombies.add(new PoleVaultingZombie(row));
                        zombieNumber++;
                    }
                }
                break;
            case 2:
                row = random.nextInt(4) + 2;
                kind = random.nextInt(2) + 1;
                if(2 <= row && row <=4 && 1<=kind && kind<= 2){
                    if(kind == 1) {
                        zombies.add(new NormalZombie(row));
                        zombieNumber++;
                    }
                    else if(kind == 2) {
                        zombies.add(new BucketHeadZombie(row));
                        zombieNumber++;
                    }
                }
                break;
            case 1:
                zombies.add(new NormalZombie(3));
                zombieNumber++;
                break;
        }
    }

}

