import javax.swing.*;

/**
 * Created by ashkanmehrkar on 6/30/16.
 */
public class Walnut extends Plant {
    public Walnut(int a, int b) {
        imageIcon = new ImageIcon("Images/walnut.png");
        column = a;
        row = b;
        health = 30;
        if(a == 1)
            x = 70 - imageIcon.getIconWidth()/2;
        else if(a == 2)
            x = 150 - imageIcon.getIconWidth()/2;
        else if(a == 3)
            x = 232 - imageIcon.getIconWidth()/2;
        else if(a == 4)
            x = 313 - imageIcon.getIconWidth()/2;
        else if(a == 5)
            x = 389 - imageIcon.getIconWidth()/2;
        else if(a == 6)
            x = 473 - imageIcon.getIconWidth()/2;
        else if(a == 7)
            x = 561 - imageIcon.getIconWidth()/2;
        else if(a == 8)
            x = 626 - imageIcon.getIconWidth()/2;
        else if(a == 9)
            x = 714 - imageIcon.getIconWidth()/2;
        if(b ==1)
            y = 140 - imageIcon.getIconHeight();
        else if(b== 2)
            y = 232 - imageIcon.getIconHeight();
        else if(b== 3)
            y = 329 - imageIcon.getIconHeight();
        else if(b== 4)
            y = 428 - imageIcon.getIconHeight();
        else if(b== 5)
            y = 523 - imageIcon.getIconHeight();
    }
    public void checkImage(int health) {
        if(health > 20){

        }
        else if(health > 10){
            imageIcon = new ImageIcon("Images/gerdoo1.png");
        }
        else{
            imageIcon = new ImageIcon("Images/gerdoo2.png");
        }


    }
}
