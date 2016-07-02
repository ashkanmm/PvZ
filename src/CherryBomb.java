import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by ashkanmehrkar on 7/1/16.
 */
public class CherryBomb extends Plant {
    public CherryBomb(int a, int b) {
        playSound("CherryBombExplode");
        health = 100;
        imageIcon = new ImageIcon("Images/cherryBomb.png");
        column = a;
        row = b;
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
        time_created = System.currentTimeMillis();
    }
    protected void playSound(String fName) {
        FileInputStream in;
        try {
            in = new FileInputStream("Sound/" + fName + ".wav");
            AudioStream audioStream;
            audioStream = new AudioStream(in);
            AudioPlayer.player.start(audioStream);
        } catch (FileNotFoundException e) {
            System.out.println("Sound Not Found!");
        } catch (IOException e) {
            System.out.println("Play Sound Exception");
        }
    }
}
