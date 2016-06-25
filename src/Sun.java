import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by ashkanmehrkar on 6/25/16.
 */
public class Sun {
    public ImageIcon sunIcon;
    public int x;
    public int y;
    public int randomTime;
    public Sun() {
        sunIcon= new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/sun.png");
        final Random random = new Random();
        x = 100;
        y = 0;
        randomTime= random.nextInt() + 1000;
        System.out.println(x  +"  " + y);

        new Thread(){
            @Override
            public void run() {
                while(true){
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
}
