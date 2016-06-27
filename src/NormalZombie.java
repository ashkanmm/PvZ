import javax.swing.*;

/**
 * Created by ashkanmehrkar on 6/26/16.
 */
public class NormalZombie extends Zombie {
    public NormalZombie(int a) {
        imageIcon = new ImageIcon("/Users/ashkanmehrkar/IntelliJ/jPvZ/src/Images/normalZombie.png");
        row = a;
        health = 5;
        switch (a) {
            case 3:
                x = 800;
                y = 395 - imageIcon.getIconHeight();
                break;

        }


    }
}
