import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

/**
 * Created by ashkanmehrkar on 6/25/16.
 */
public class Sun {
    public ImageIcon sunIcon;
    public int x;
    public int y;
    public Sun() {
        sunIcon= new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/sun.png");
        Random random = new Random();
        x = random.nextInt(800) + 0;
        y = 0;
        System.out.println(x  +" and " + y);

        new Thread(){
            @Override
            public void run() {
                while(y < 600){
                    y = y + 3;
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
    public void paint(Graphics g) {
        g.drawImage(sunIcon.getImage(), x, y, null);
    }
    public boolean getSun(MouseEvent event) {
        if(x < event.getX() && event.getX() < x + sunIcon.getIconWidth() && y < event.getY() && event.getY() < y + sunIcon.getIconHeight())
            return true;
        else
            return false;
    }


}
