import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by ashkanmehrkar on 6/22/16.
 */
public class PvZ extends JFrame {
    Thread thread;
    int state = 0;
    int stateState = 0;
    ChamanZan[] chamanZanArray;
    MenuBar menuBar;
    public PvZ() {
        super("گیاهان در برابر زامبی ها");
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                switch (state) {
                    case 0 :
                        if(250 < e.getX() && e.getX() < 548 && 547 < e.getY() && e.getY() < 579)
                            stateState = 1;
                        else
                            stateState = 0;
                        break;
                    case 1 :
                        if(407 < e.getX() && e.getX() < 729 && 97 < e.getY() && e.getY() < 203)
                            stateState = 1;
                        else
                            stateState = 0;
                        break;
                }
                //System.out.println(state + "   " + stateState);
            }

        });
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (state) {
                    case 0 :
                        if(250 < e.getX() && e.getX() < 548 && 547 < e.getY() && e.getY() < 579) {
                            state = 1;
                            stateState = 0;
                        }

                    case 1 :
                        if(407 < e.getX() && e.getX() < 729 && 97 < e.getY() && e.getY() < 203) {
                            state  = 2;
                        }
                    case 2 :
                        if(720 < e.getX() && e.getX() < 775 && 520 < e.getY() && e.getY() < 540)
                            setVisible(false);
                }
                System.out.println(e.getX() + "   " + e.getY());
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
        chamanZanArray = new ChamanZan[5];
        for(int i= 0; i< 5; i++)
            chamanZanArray[i] = new ChamanZan(-50, (i+1) * 100 - 25);
        menuBar = new MenuBar();
        thread = new Thread(){
            @Override
            public void run() {
                while(true){
                    PvZ.this.repaint();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }
    public void paint(Graphics g){
        switch (state) {
            case 0 :
                if(stateState == 0) {
                    ImageIcon image = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/firstPage.jpg");
                    g.drawImage(image.getImage(),0, 0, null);
                }
                else if(stateState == 1){
                    ImageIcon image1 = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/firstPage1.jpg");
                    g.drawImage(image1.getImage(),0, 0, null);
                }
                break;

            case 1 :
                if(stateState == 0) {
                    ImageIcon image2 = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/menu.jpg");
                    g.drawImage(image2.getImage(),0, 0, null);
                }
                else if(stateState == 1) {
                    ImageIcon image3 = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/menu1.jpg");
                    g.drawImage(image3.getImage(),0, 0, null);
                }

                break;

            case 2 :
                ImageIcon image2 = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/backGround.jpg");
                g.drawImage(image2.getImage(),0, 10, this);
                menuBar.paint(this.getGraphics());
                for(int i= 0 ; i< 5; i++)
                    chamanZanArray[i].paint(this.getGraphics());
        }
    }

    public static void main(String[] args) {
        PvZ intro = new PvZ();
        intro.setSize(800, 600);
        intro.setVisible(true);
        intro.setResizable(false);
        intro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
