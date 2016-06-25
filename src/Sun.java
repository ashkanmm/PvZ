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
    public Sun(final Graphics graphics) {
        sunIcon= new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/sun.png");
        final Random random = new Random();
        x = random.nextInt(765)+ 20;
        y = random.nextInt(585)+ 105;
        randomTime= random.nextInt() + 1000;

        new Thread(){
            @Override
            public void run() {
                while(true){
                    paint(graphics);
                    try {
                        Thread.sleep(random.nextInt());
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
