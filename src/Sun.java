import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * Created by ashkanmehrkar on 6/25/16.
 */
public class Sun {
    public ImageIcon sunIcon;
    public int x;
    public int y;
    boolean existence;
    public Sun() {
        sunIcon= new ImageIcon("Images/sun.png");
        existence = true;
        Random random = new Random();
        x = random.nextInt(800 - sunIcon.getIconWidth()) + 0;
        y = 0;
    }
    public Sun(int a, int b) {
        sunIcon= new ImageIcon("Images/sun.png");
        existence = true;
        x = a + 10;
        y = b + 10;
    }

    public boolean getSun(MouseEvent event) {
        if(x < event.getX() && event.getX() < x + sunIcon.getIconWidth() && y < event.getY() && event.getY() < y + sunIcon.getIconHeight()) {
            existence = false;
            return true;
        }
        else
            return false;
    }

}
