import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by ashkanmehrkar on 6/26/16.
 */
public class PeaShooter extends Plant {
    public PeaShooter(int a) {
        imageIcon = new ImageIcon("/Users/ashkanmehrkar/IntelliJ/jPvZ/src/Images/peaShooter.png");
        switch (a) {
            case 3:
                x = 30;
                y = 350 - imageIcon.getIconHeight();
                row = 3;
                bullets = new ArrayList<Bullet>();
                break;
        }


    }
}
