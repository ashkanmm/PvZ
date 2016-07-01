import javax.swing.*;

/**
 * Created by ashkanmehrkar on 6/27/16.
 */
public class PeaShooterBullet extends Bullet {
    public PeaShooterBullet(int a, int b) {
        imageIcon = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/peaShooterBullet.png");
        x = a + 45;
        y = b + 15;
    }
}
