import javax.swing.*;
import java.awt.*;

/**
 * Created by ashkanmehrkar on 6/24/16.
 */
public class MenuBar {
    private ImageIcon menuBarIcon;
    private ImageIcon peaShooterIcon;
    private ImageIcon sunflowerIcon;
    public MenuBar() {
        menuBarIcon = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/menuBar.png");
        peaShooterIcon= new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/peaShooterIcon.jpg");
        sunflowerIcon = new ImageIcon("/Users/ashkanmehrkar/Desktop/PvZ/src/Images/sunflowerIcon.jpg");
    }
    public void paint(Graphics g) {
        g.drawImage(menuBarIcon.getImage(), 10, 20, null);
        g.drawImage(sunflowerIcon.getImage(), 90, 25, null);
        g.drawImage(peaShooterIcon.getImage(), 90+ sunflowerIcon.getIconWidth()+ 5, 25, null);
    }
}
