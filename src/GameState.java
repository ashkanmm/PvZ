import javax.swing.*;
import java.awt.*;

/**
 * Created by ashkanmehrkar on 6/25/16.
 */
public class GameState {
    private int level;
    private ChamanZan[] chamanZans;
    private Zombie[] zombies;
    private MenuBar menuBar;
    private Plant[] plants;
    private Player player;
    public Sun sun;


    public GameState(JFrame jFrame) {
        sun = new Sun();
        level = -3;
        menuBar = new MenuBar();
        chamanZans = new ChamanZan[5];
        for(int i = 0; i < 5; i++)
            chamanZans[i] = new ChamanZan(-50, (i+1) * 100 - 25);
        player = new Player();
        plants = new Plant[45];
        zombies = new Zombie[100];
    }

    public void paint(Graphics g) {
        int a = this.getLevel();
        switch (a) {
            case -3:
                ImageIcon image = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/firstPage.jpg");
                g.drawImage(image.getImage(),0, 0, null);
                break;
            case -2:
                ImageIcon image1 = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/firstPage1.jpg");
                g.drawImage(image1.getImage(),0, 0, null);
                break;
            case -1:
                ImageIcon image3 = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/menu.jpg");
                g.drawImage(image3.getImage(),0, 0, null);
                break;
            case 0:
                ImageIcon image4 = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/menu1.jpg");
                g.drawImage(image4.getImage(),0, 0, null);
                break;
            case 1:
                ImageIcon image5 = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/backGround1.jpg");
                g.drawImage(image5.getImage(),0, 10, null);
                ImageIcon image6 = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/chaman1.png");
                g.drawImage(image6.getImage(), 25, 281, null);
                menuBar.paint(g);
                chamanZans[2].paint(g);
                sun.paint(g);

        }
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int a) {
        level = a;
    }
}
