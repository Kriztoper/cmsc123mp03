package cmsc123.mp03.game;

import javax.swing.JButton;
import javax.swing.JPanel;

import cmsc123.mp03.ui.UtilityFrame;

/**
 * Container for the main JFrame and
 * other UI components used for the game.
 * 
 * @author John Paul Ada
 *
 */
public class GameFrame {

    private UtilityFrame frame;
    private JPanel menuPanel;
    private JButton startGameButton;
    
    /**
     * Initializes this UI container.
     */
    public GameFrame() {
        frame = new UtilityFrame();
        
        initMenuPanel();
    }
    
    /**
     * Initializes the menu panel.
     */
    private void initMenuPanel() {
        menuPanel = new JPanel();
        
        startGameButton = new JButton("Start Game");
        
        menuPanel.add(startGameButton);
    }
    
    /**
     * Shows frame.
     */
    public void show() {
        frame.setVisible(true);
    }
    
}
