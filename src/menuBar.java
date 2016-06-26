import javax.swing.*;
import java.awt.*;

/**
 * Created by ashkanmehrkar on 6/24/16.
 */
public class MenuBar {
    public ImageIcon menuBarIcon;
    public ImageIcon peaShooterIcon;
    public ImageIcon sunflowerIcon;
    public MenuBar() {
        menuBarIcon = new ImageIcon("/Users/ashkanmehrkar/IntelliJ/jPvZ/src/Images/menuBar.png");
        peaShooterIcon= new ImageIcon("/Users/ashkanmehrkar/IntelliJ/jPvZ/src/Images/peaShooterIcon.png");
        sunflowerIcon = new ImageIcon("/Users/ashkanmehrkar/IntelliJ/jPvZ/src/Images/sunflowerIcon.png");
    }
    public void paint(Graphics g) {
        g.drawImage(menuBarIcon.getImage(), 10, 20, null);
        g.drawImage(sunflowerIcon.getImage(), 90, 25, null);
        g.drawImage(peaShooterIcon.getImage(), 90+ sunflowerIcon.getIconWidth()+ 5, 25, null);
    }
}
