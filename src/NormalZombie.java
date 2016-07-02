import javax.swing.*;

/**
 * Created by ashkanmehrkar on 6/26/16.
 */
public class NormalZombie extends Zombie {
    public NormalZombie(int a) {
        imageIcon = new ImageIcon("Images/normalZombie.png");
        row = a;
        health = 5;
        speed = 4;
        switch (a) {
            case 3:
                x = 800;
                y = 369 - imageIcon.getIconHeight();
                break;

            case 2:
                x = 800;
                y = 266 - imageIcon.getIconHeight();
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
