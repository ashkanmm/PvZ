/**
 * Created by ashkanmehrkar on 7/1/16.
 */
public class PoleVaultingZombie extends Zombie {
    public PoleVaultingZombie(int a) {
        row =a;
        health = 3;
        speed = 2;
        switch (a) {
            case 2:
                x = 800;
                y = 250 - imageIcon.getIconHeight();
                break;
            case 3:
                x = 800;
                y = 369 - imageIcon.getIconHeight();
                break;
            case 4:
                x = 800;
                y = 462 - imageIcon.getIconHeight();
                break;

        }
    }
}
