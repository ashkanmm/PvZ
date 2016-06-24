import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by ashkanmehrkar on 6/24/16.
 */
public class ChamanZan {
    ImageIcon chamanZanIcon;
    int x;
    int y;
    public ChamanZan(int a, int b) {
        x= a;
        y= b;
        chamanZanIcon = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/chamanZan.png");
    }
    public void move(final Graphics g) {
        new Thread(){
            @Override
            public void run() {
                while(x< 800){
                    x= x+ 1;
                    paint(g);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
    public void paint(Graphics g) {
        g.drawImage(chamanZanIcon.getImage(), x, y, null);
    }

}
