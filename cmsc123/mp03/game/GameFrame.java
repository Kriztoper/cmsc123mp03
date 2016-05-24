package cmsc123.mp03.game;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cmsc123.mp03.framework.GamePanel;
import cmsc123.mp03.ui.ImageButton;
import cmsc123.mp03.ui.UtilityFrame;

/**
 * Container for the main JFrame and
 * other UI components used for the game.
 * 
 * @author John Paul Ada
 *
 */
public class GameFrame {

    private static int DEFAULT_WIDTH  = 640;
    private static int DEFAULT_HEIGHT = 640;
    
    private UtilityFrame frame;
    private JPanel menuPanel, howToPlayPanel;
    
    private GamePanel gamePanel;
    private ImageButton creditsButton, startGameButton, howToPlayButton;
    private ImageButton backToMainButton;

    /**
     * Initializes this UI container.
     */
    public GameFrame() {
        frame = new UtilityFrame();
        
        initMenuPanel();
        initHowToPlayPanel();
        initGamePanel();
    }
    
    /**
     * Initializes the menu panel.
     */
    private void initMenuPanel() {
        menuPanel = new JPanel();
        menuPanel.setLayout(null);
        menuPanel.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        // Set background image for menu panel
        try {
            JLabel bg = new JLabel(new ImageIcon(ImageIO.read(new File("assets/images/menu-bg.png"))));
            bg.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
            menuPanel.add(bg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // set start game and credits button
		try {
			startGameButton = new ImageButton(new ImageIcon(ImageIO.read(new File("assets/images/start-normal.png"))));
			creditsButton   = new ImageButton(new ImageIcon(ImageIO.read(new File("assets/images/credits-normal.png"))));
			howToPlayButton = new ImageButton(new ImageIcon(ImageIO.read(new File("assets/images/how-to-play-normal.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        startGameButton.setBounds(250, 400, 150, 30);
        creditsButton.setBounds(250, 450, 150, 30);
        howToPlayButton.setBounds(250, 500, 150, 30);
        
        menuPanel.add(startGameButton);
        menuPanel.add(creditsButton);
        menuPanel.add(howToPlayButton);
    }
    
    /**
     * Initializes how to play panel
     */
    public void initHowToPlayPanel() {
    	howToPlayPanel = new JPanel();
    	howToPlayPanel.setLayout(null);
        howToPlayPanel.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
    	// Set background image for how to play panel
        try {
            JLabel bg = new JLabel(new ImageIcon(ImageIO.read(new File("assets/images/howtobg.png"))));
            bg.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
            howToPlayPanel.add(bg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // set back to main button
		try {
			backToMainButton = new ImageButton(new ImageIcon(ImageIO.read(new File("assets/images/start-normal.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        backToMainButton.setBounds(250, 500, 150, 30);
        howToPlayPanel.add(backToMainButton);      
    }
    
    /**
     * Initializes game panel.
     */
    private void initGamePanel() {
        gamePanel = new GamePanel();
    }
    
    /**
     * Shows frame.
     */
    public void show() {
        frame.setVisible(true);
    }
    
    public GamePanel getGamePanel() {
        return gamePanel;
    }
    
    public JPanel getMenuPanel() {
        return menuPanel;
    }
    
    public JPanel getHowToPlayPanel() {
    	return howToPlayPanel;
    }
    
    public JButton getStartGameButton() {
        return startGameButton;
    }
    
    public JButton getHowToPlayButton() {
    	 return howToPlayButton;
    }
    
    public JButton getBackToMainButton() {
    	return backToMainButton;
    }

	public UtilityFrame getFrame() {
        return frame;
    }
}
