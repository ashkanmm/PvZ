import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A very simple structure for the main game loop.
 * THIS IS NOT PERFECT, but works for most situations.
 * Note that to make this work, none of the 2 methods 
 * in the while loop (update() and render()) should be 
 * long running! Both must execute very quickly, without 
 * any waiting and blocking!
 * 
 * Detailed discussion on different game loop design
 * patterns is available in the following link:
 *    http://gameprogrammingpatterns.com/game-loop.html
 * 
 * @author Ashkan Mehrkar
 */
public class GameLoop implements Runnable {
	
	/**
	 * Frame Per Second.
	 * Higher is better, but any value above 24 is fine.
	 */
	public static final int FPS = 30;
	
	private GameFrame canvas;
	private GameState state;

	public GameLoop(GameFrame frame) {
		canvas = frame;
	}
	
	/**
	 * This must be called before the game loop starts.
	 */
	public void init() {
		// Perform all initializations ...
		state = new GameState();
		canvas.addKeyListener(state.getKeyListener());
		canvas.addMouseListener(state.getMouseListener());
		canvas.addMouseMotionListener(state.getMouseMotionListener());
	}

	@Override
	public void run() {
		boolean gameOver = false;
		while (!gameOver) {
			try {
				long start = System.currentTimeMillis();
				//
				state.update(canvas);
				canvas.render(state);
				//
				if(state.gameOver()) {
					gameOver = true;
					playSound("GameOver1");
				}

				long delay = (1000 / FPS) - (System.currentTimeMillis() - start);
				if (delay > 0)
					Thread.sleep(delay);
			} catch (InterruptedException ex) {
			}
		}
		canvas.getGraphics().drawImage(new ImageIcon("Images/gameOver.png").getImage(), 0, 0, null);
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
