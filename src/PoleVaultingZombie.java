import javax.swing.*;

/**
 * Created by ashkanmehrkar on 7/1/16.
 */
public class PoleVaultingZombie extends Zombie {
    public PoleVaultingZombie(int a) {
        jumping = true;
        row =a;
        health = 3;
        speed = 2;
        imageIcon = new ImageIcon("Images/poleVaultingZombie.png");
        switch (a) {
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
