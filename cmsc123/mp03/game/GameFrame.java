package cmsc123.mp03.game;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import cmsc123.mp03.framework.GamePanel;
import cmsc123.mp03.ui.ImageButton;
import cmsc123.mp03.ui.Panel;
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
    private Panel menuPanel;
    private Panel creditsPanel;
    private Panel howToPlayPanel;
    
    private GamePanel gamePanel;
    private ImageButton creditsButton;
    private ImageButton startGameButton;
    private ImageButton howToPlayButton;
    private JButton backToMainButton;
    private JButton backToMainHTPButton;
    
    /**
     * Initializes this UI container.
     */
    public GameFrame() {
        frame = new UtilityFrame();
        
        initMenuPanel();
        initCreditsPanel();
        initHowToPlayPanel();
        initGamePanel();
    }
    
    /**
     * Initializes the credits panel.
     * */
    private void initCreditsPanel() {
	    creditsPanel = new Panel();
	    creditsPanel.setLayout(null);
	    creditsPanel.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	    
	    // set background image for credits panel
	    try {
	    	creditsPanel.setBackGround(new ImageIcon(this.getClass().getResource("/images/howtobg.png")));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    // set back to main button
        backToMainButton = new JButton("BACK");
        backToMainButton.setBackground(Color.BLACK);
        backToMainButton.setForeground(Color.WHITE);
        
        backToMainButton.setBounds(270, 500, 100, 30);
        creditsPanel.add(backToMainButton);
	    
    }
    
    /**
     * Initializes the menu panel.
     */
    private void initMenuPanel() {
        menuPanel = new Panel();
        menuPanel.setLayout(null);
        menuPanel.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        // Set background image for menu panel
        try {
        	menuPanel.setBackGround(new ImageIcon(this.getClass().getResource("/images/menu-bg.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // set start game and credits button
		try {
			startGameButton = new ImageButton(new ImageIcon(this.getClass().getResource("/images/start-normal.png")));
			creditsButton   = new ImageButton(new ImageIcon(this.getClass().getResource("/images/credits-normal.png")));
			howToPlayButton = new ImageButton(new ImageIcon(this.getClass().getResource("/images/how-to-play-normal.png")));
		} catch (Exception e) {
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
    	howToPlayPanel = new Panel();
    	howToPlayPanel.setLayout(null);
        howToPlayPanel.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
    	// Set background image for how to play panel
        try {
        	howToPlayPanel.setBackGround(new ImageIcon(this.getClass().getResource("/images/howtobg.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // set back to main button
        backToMainHTPButton = new JButton("BACK");
        backToMainHTPButton.setBackground(Color.BLACK);
        backToMainHTPButton.setForeground(Color.WHITE);
        
        backToMainHTPButton.setBounds(270, 500, 100, 30);
        howToPlayPanel.add(backToMainHTPButton);      
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
        frame.getCurrentPanel().setVisible(true);
    }
    
    public UtilityFrame getUtilFrame(){   	
    	return frame;
    }
 
    public GamePanel getGamePanel() {
        return gamePanel;
    }
    
    public JPanel getMenuPanel() {
        return menuPanel;
    }
    
    public JPanel getCreditsPanel() {
    	return creditsPanel;
    }
    
    public JPanel getHowToPlayPanel() {
    	return howToPlayPanel;
    }
    
    public JButton getStartGameButton() {
        return startGameButton;
    }
    
    public JButton getCreditsButton() {
   	 return creditsButton;
   }
    
    public JButton getHowToPlayButton() {
    	 return howToPlayButton;
    }
    
    public JButton getBackToMainButton() {
    	return backToMainButton;
    }
    
    public JButton getBackToMainHTPButton() {
    	return backToMainHTPButton;
    }

	public UtilityFrame getFrame() {
        return frame;
    }
}
