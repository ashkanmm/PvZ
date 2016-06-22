import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by ashkanmehrkar on 6/22/16.
 */
public class Intro extends JFrame {
    int state = 0;
    public Intro() {
        super("JPvZ");
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if(state == 0 || state == 1 && 249 < e.getX() && e.getX() < 549 && 548 < e.getY() && e.getY() < 579) {
                    state = 1;//مقدمه قرمز
                }
                else if(state == 0 || state == 1)
                    state = 0;//مقدمه سیاه
            }
        });
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(state == 1 || state == 1 && 249 < e.getX() && e.getX() < 549 && 548 < e.getY() && e.getY() < 579) {
                    state = 3;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        new Thread(){
            @Override
            public void run() {
                while(true){
                    Intro.this.repaint();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
    public void paint(Graphics g){
        switch (state) {
            case 0 :
                ImageIcon image = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/intro.jpg");
                //System.out.println(image.getIconHeight());
                //System.out.println(image.getIconWidth());
                g.drawImage(image.getImage(),0, 0, null);
                break;

            case 1 :
                ImageIcon image1 = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/intro1.jpg");
                //System.out.println(image.getIconHeight());
                //System.out.println(image.getIconWidth());
                g.drawImage(image1.getImage(),0, 0, null);
                break;

            case 3 :
                ImageIcon image2 = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/menu1.jpg");
                g.drawImage(image2.getImage(),0, 0, null);
                break;


        }
    }

    public static void main(String[] args) {
        Intro intro = new Intro();
        intro.setSize(800, 600);
        intro.setVisible(true);
        intro.setResizable(false);
        intro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
