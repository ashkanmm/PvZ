import javax.swing.*;

/**
 * Created by ashkanmehrkar on 7/1/16.
 */
public class SnowPeaShooterBullet extends Bullet {
    public SnowPeaShooterBullet(int a, int b) {
        imageIcon = new ImageIcon("Images/snowPeaShooterBullet.png");
        x = a + 45;
        y = b + 15;
    }
}
