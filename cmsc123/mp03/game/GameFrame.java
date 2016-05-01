package cmsc123.mp03.game;

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
    }
    
    /**
     * Shows frame.
     */
    public void show() {
        frame.setVisible(true);
    }
    
}
