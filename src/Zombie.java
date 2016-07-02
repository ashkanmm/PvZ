import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by ashkanmehrkar on 6/26/16.
 */
public abstract class Zombie {
    protected ImageIcon imageIcon;
    protected int x;
    protected int y;
    protected int row;
    protected int health;
    protected int speed;
    protected boolean jumping;
    protected ArrayList <CatapultZombieBullet> bullets;
    protected int bulletNumber;
    public Zombie() {

    }
}
