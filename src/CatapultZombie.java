import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by leilaaz on 7/1/16.
 */
public class CatapultZombie extends Zombie {
    public CatapultZombie(int a){
        bullets = new ArrayList<CatapultZombieBullet>();
        imageIcon = new ImageIcon("Images/catapultZombie.png");
        health = 15;
        speed = 4;
        row = a;
        bulletNumber = 0;
        switch (a){
            case 2:
                x = 800;
                y = 266 - imageIcon.getIconHeight();
                break;
            case 3:
                x = 800;
                y = 369 - imageIcon.getIconHeight();
                break;
            case 4:
                x = 800;
                y = 462 - imageIcon.getIconHeight();
                break;
            case 5:
                x = 800;
                y = 571 - imageIcon.getIconHeight();
                break;
            case 1:
                x = 800;
                y = 184 - imageIcon.getIconHeight();
                break;
        }
    }
}
