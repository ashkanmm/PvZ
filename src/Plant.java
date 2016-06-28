import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by ashkanmehrkar on 6/26/16.
 */
public abstract class Plant {
    protected ImageIcon imageIcon;
    protected int x;
    protected int y;
    protected int row;
    protected int column;
    public long last_bullet;
    public long last_sun;
    protected ArrayList<Bullet> bullets;
    protected int price;
    public Plant() {

    }
}
