import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by ashkanmehrkar on 6/22/16.
 */
public class MainWindow extends JFrame {
    int state = 0;
    int stateState = 0;
    public MainWindow() {
        super("JPvZ");
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                switch (state) {
                    case 0 :
                        if(249 < e.getX() && e.getX() < 549 && 548 < e.getY() && e.getY() < 579)
                            stateState = 1;//مقدمه قرمز
                        else
                            stateState = 0;
                    case 1:
                        if(409 < e.getX() && e.getX() < 730 && 96 < e.getY() && e.getY() < 204)
                            stateState = 1;
                        else
                            stateState = 0;
                }
            }

        });
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(state == 0 && 249 < e.getX() && e.getX() < 549 && 548 < e.getY() && e.getY() < 579) {
                    state = 1;
                    stateState = 0;
                }
                System.out.println(e.getX() +"  " + e.getY());
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
                    MainWindow.this.repaint();
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
                if(stateState == 0) {
                    ImageIcon image = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/intro-1.jpg");
                    g.drawImage(image.getImage(),0, 0, null);
                }
                else if(stateState == 1){
                    ImageIcon image1 = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/intro-2.jpg");
                    g.drawImage(image1.getImage(),0, 0, null);
                }
                break;

            case 1 :
                if(stateState == 0) {
                    ImageIcon image2 = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/menu-1.jpg");
                    g.drawImage(image2.getImage(),0, 0, null);
                }
                else if(stateState == 1) {
                    ImageIcon image3 = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/menu-2.jpg");
                    g.drawImage(image3.getImage(),0, 0, null);
                }

                break;
        }
    }

    public static void main(String[] args) {
        System.out.println("ashkan mehrkar");
        MainWindow intro = new MainWindow();
        intro.setSize(800, 600);
        intro.setVisible(true);
        intro.setResizable(false);
        intro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
