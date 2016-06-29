import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import javax.swing.*;

/**
 * The window on which the rendering is performed.
 * This structure uses the modern BufferStrategy approach for 
 * double-buffering; actually, it performs triple-buffering!
 * For more information on BufferStrategy check out:
 *    http://docs.oracle.com/javase/tutorial/extra/fullscreen/bufferstrategy.html
 *    http://docs.oracle.com/javase/8/docs/api/java/awt/image/BufferStrategy.html
 * 
 * @author Ashkan Mehrkar
 */
public class GameFrame extends JFrame {
	
	public static final int GAME_HEIGHT = 600;                  // 720p game resolution
	public static final int GAME_WIDTH = 800;  // wide aspect ratio
	private int cnt = 0;
	private BufferStrategy bufferStrategy;
	private long last_sun = 0;
	{
		last_sun = System.currentTimeMillis();
	}
	private long last_zombie = 0;
	{
		last_zombie = System.currentTimeMillis();
	}

	public GameFrame(String title) {
		super(title);
		setResizable(false);
		setSize(GAME_WIDTH, GAME_HEIGHT);
		//
		// Initialize the JFrame ...
		//
	}

	/**
	 * This must be called once after the JFrame is shown:
	 *    frame.setVisible(true);
	 * and before any rendering is started.
	 */
	public void initBufferStrategy() {
		// Triple-buffering
		createBufferStrategy(3);
		bufferStrategy = getBufferStrategy();
	}

	
	/**
	 * Game rendering with triple-buffering using BufferStrategy.
	 */
	public void render(GameState state) {
		// Get a new graphics context to render the current frame

		Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
		try {
			// Do the rendering
			doRendering(graphics, state);
		} finally {
			// Dispose the graphics, because it is no more needed
			graphics.dispose();
		}
		// Display the buffer
		bufferStrategy.show();
		// Tell the system to do the drawing NOW;
		// otherwise it can take a few extra ms and will feel jerky!
		Toolkit.getDefaultToolkit().sync();
	}
	
	/**
	 * Rendering all game elements based on the game state.
	 */
	private void doRendering(Graphics2D g2d, GameState state) {
		//
		// Draw all game elements according 
		//  to the game 'state' using 'g2d' ...
		//
		switch (state.condition) {
			case -3:
				g2d.drawImage(state.backGround.getImage(),0, 0, null);
				break;
			case -2:
				g2d.drawImage(state.backGround.getImage(),0, 0, null);
				break;
			case -1:
				g2d.drawImage(state.backGround.getImage(),0 ,0, null);
				break;
			case 0:
				g2d.drawImage(state.backGround.getImage(), 0, 0, null);
				break;
			case 1:
				g2d.drawImage(state.backGround.getImage(), 0, 0, null);
				g2d.drawImage(state.grass.getImage(),25, 281, null);
				g2d.drawImage(state.menuBar.menuBarIcon.getImage(), 10, 20, null);
				g2d.drawImage(state.menuBar.sunflowerIcon.getImage(), 90, 25, null);
				g2d.drawImage(state.menuBar.peaShooterIcon.getImage(), 90+ state.menuBar.sunflowerIcon.getIconWidth()+ 5, 25, null);
				for(int i = 0; i < state.zombies.size(); i++)
					g2d.drawImage(state.zombies.get(i).imageIcon.getImage(), state.zombies.get(i).x, state.zombies.get(i).y, null);
                for(Plant plant : state.plants) {
                    g2d.drawImage(plant.imageIcon.getImage(), plant.x, plant.y, null);
                    if(plant.getClass().equals(PeaShooter.class)) {
                        for(int j = 0; j < plant.bullets.size(); j++) {
                            g2d.drawImage(plant.bullets.get(j).imageIcon.getImage(), plant.bullets.get(j).x, plant.bullets.get(j).y, null);
                        }

                    }
                }
                if(state.draggedImage != null) {
                    g2d.drawImage(state.draggedImage.getImage(), state.tmpX, state.tmpY, null);
                }
				g2d.drawString(String.valueOf(state.score), 40, 100);
				for(int i = 0; i < state.suns.size(); i++)
					g2d.drawImage(state.suns.get(i).sunIcon.getImage(), state.suns.get(i).x, state.suns.get(i).y, null);
                for(int i = 0; i < state.lawnMowers.size(); i++) {
                    g2d.drawImage(state.lawnMowers.get(i).imageIcon.getImage(), state.lawnMowers.get(i).x, state.lawnMowers.get(i).y, null);
                }
                break;
		}

	}
	
}
