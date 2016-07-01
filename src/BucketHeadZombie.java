import javax.swing.*;

/**
 * Created by ashkanmehrkar on 6/30/16.
 */
public class BucketHeadZombie extends Zombie {
    public BucketHeadZombie(int a) {
        row = a;
        imageIcon = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/bucketHeadZombie.png");
        health = 8;
        speed = 6;
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
