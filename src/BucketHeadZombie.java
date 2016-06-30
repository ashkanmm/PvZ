import javax.swing.*;

/**
 * Created by ashkanmehrkar on 6/30/16.
 */
public class BucketHeadZombie extends Zombie {
    public BucketHeadZombie(int a) {
        row = a;
        imageIcon = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/bucketHeadZombie.png");
        health = 8;
        speed = 4;
        switch (a) {
            case 2:
                x = 800;
                y = 250 - imageIcon.getIconHeight();
                break;
            case 3:
                x = 800;
                y = 369 - imageIcon.getIconHeight();
                break;
            case 4:
                x = 800;
                y = 462 - imageIcon.getIconHeight();
                break;

        }


    }
}
