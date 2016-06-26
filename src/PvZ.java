import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

/**
 * Created by ashkanmehrkar on 6/22/16.
 */
public class PvZ extends JFrame {
    GameState gameState;
    public PvZ() {
        super("گیاهان در برابر زامبی ها");
        gameState = new GameState(this);
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                switch (gameState.getLevel()) {
                    case -3:
                        if(250 < e.getX() && e.getX() < 548 && 547 < e.getY() && e.getY() < 579)
                            gameState.setLevel(-2);
                        else
                            gameState.setLevel(-3);
                        break;
                    case -2:
                        if(250 < e.getX() && e.getX() < 548 && 547 < e.getY() && e.getY() < 579)
                            gameState.setLevel(-2);
                        else
                            gameState.setLevel(-3);
                        break;
                    case -1:
                        if(407 < e.getX() && e.getX() < 729 && 97 < e.getY() && e.getY() < 203)
                            gameState.setLevel(0);
                        else
                            gameState.setLevel(-1);
                        break;
                    case 0:
                        if(407 < e.getX() && e.getX() < 729 && 97 < e.getY() && e.getY() < 203)
                            gameState.setLevel(0);
                        else
                            gameState.setLevel(-1);
                        break;
                }
                //System.out.println(state + "   " + stateState);
            }

        });
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (gameState.getLevel()) {
                    case -2 :
                        if(250 < e.getX() && e.getX() < 548 && 547 < e.getY() && e.getY() < 579)
                            gameState.setLevel(-1);
                        break;

                    case 0 :
                        if(407 < e.getX() && e.getX() < 729 && 97 < e.getY() && e.getY() < 203) {
                            gameState.setLevel(1);
                        }
                        break;
                    case 1:
                        if(gameState.sun.getSun(e))
                            System.out.println("got it");
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
        Thread thread = new Thread() {
            @Override
            public void run() {
                while(true) {
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

    @Override
    public void paint(Graphics g) {
        BufferedImage buf = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D gg = buf.createGraphics();
        gameState.paint(gg);
        g.drawImage(buf, 0, 0, null);

    }

    /*public void paint(Graphics g) {
        switch (gameState.getLevel()) {
            case -3:
                ImageIcon image = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/firstPage.jpg");
                System.err.println(image);
                g.drawImage(image.getImage(),0, 0, null);
                break;
            case -2:
                ImageIcon image1 = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/firstPage1.jpg");
                g.drawImage(image1.getImage(),0, 0, null);
                break;
            case -1:
                ImageIcon image3 = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/menu.jpg");
                g.drawImage(image3.getImage(),0, 0, null);
                break;
            case 0:
                ImageIcon image4 = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/menu1.jpg");
                g.drawImage(image4.getImage(),0, 0, null);
                break;
            case 1:
                ImageIcon image5 = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/backGround1.jpg");
                g.drawImage(image5.getImage(),0, 10, null);
                menuBar.paint(g);
                for(int i= 0 ; i< 5; i++)
                    chamanZanArray[i].paint(g);

        }
    }*/


    public static void main(String[] args) {
        PvZ intro = new PvZ();
        intro.setSize(800, 600);
        intro.setVisible(true);
        intro.setResizable(false);
        intro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
