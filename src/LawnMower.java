import javax.swing.*;
import java.awt.*;

/**
 * Created by ashkanmehrkar on 6/24/16.
 */
public class LawnMower {
    ImageIcon imageIcon;
    public int x;
    int y;
    public int row;
    public boolean turnOn;
    public LawnMower(int a) {
        turnOn = false;
        imageIcon = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/chamanZan.png");
        switch (a) {
            case 1:
                x = -50;
                y = 178 - imageIcon.getIconHeight();
                row = 1;
                break;
            case 2:
                x = -50;
                y = 268 - imageIcon.getIconHeight();
                row = 2;
                break;
            case 3:
                x = -50;
                y = 357 - imageIcon.getIconHeight();
                row = 3;
                break;
            case 4:
                x = -50;
                y = 465 - imageIcon.getIconHeight();
                row = 4;
                break;
            case 5:
                x = -50;
                y = 568 - imageIcon.getIconHeight();
                row = 5;
                break;

        }
    }
    public void move() {
        this.x = this.x + 5;
    }
    public void paint(Graphics g) {
        g.drawImage(imageIcon.getImage(), x, y, null);
    }

}
