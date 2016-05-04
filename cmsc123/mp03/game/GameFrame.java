package cmsc123.mp03.game;

import javax.swing.JButton;
import javax.swing.JPanel;

import cmsc123.mp03.framework.GamePanel;
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
    private JPanel menuPanel;
    
    private GamePanel gamePanel;
    private JButton startGameButton, creditsButton;
    
    /**
     * Initializes this UI container.
     */
    public GameFrame() {
        frame = new UtilityFrame();
        
        initMenuPanel();
        initGamePanel();
    }
    
    /**
     * Initializes the menu panel.
     */
    private void initMenuPanel() {
        menuPanel = new JPanel();
        menuPanel.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        startGameButton = new JButton("Start Game");
        creditsButton   = new JButton("Credits");
        
        menuPanel.add(startGameButton);
        menuPanel.add(creditsButton);
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
    
    public JButton getStartGameButton() {
        return startGameButton;
    }
    
    /**
     * Returns the credits button.
     * 
     * @return JButton
     */
    public JButton getCreditsButton() {
		return creditsButton;
	}

	public UtilityFrame getFrame() {
        return frame;
    }
}
