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
            case 3:
                x = -50;
                y = 275;
                row = 3;
                break;
            case 2:
                x = -50;
                y = 175;
                row = 2;
                break;
            case 4:
                x = -50;
                y = 375;
                row = 4;
                break;
            case 1:
                x = -50;
                y = 175;
                row = 1;
                break;
            case 5:
                x = -50;
                y = 475;
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
