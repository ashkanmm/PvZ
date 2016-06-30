import javax.swing.*;

/**
 * Created by ashkanmehrkar on 6/26/16.
 */
public class NormalZombie extends Zombie {
    public NormalZombie(int a) {
        imageIcon = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/normalZombie.png");
        row = a;
        health = 5;
        speed = 3;
        switch (a) {
            case 3:
                x = 800;
                y = 369 - imageIcon.getIconHeight();
                break;

            case 2:
                x = 800;
                y = 250 - imageIcon.getIconHeight();
                break;
            case 4:
                x = 800;
                y = 462 - imageIcon.getIconHeight();
                break;
        }

    }
}
