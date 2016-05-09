package cmsc123.mp03.game;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cmsc123.mp03.framework.GamePanel;
//import cmsc123.mp03.ui.CreditsButton;
import cmsc123.mp03.ui.ImageButton;
import cmsc123.mp03.ui.StartGameButton;
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
//    private CreditsButton creditsButton;
    private StartGameButton startGameButton;
    
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
        menuPanel.setLayout(null);
        menuPanel.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        // Set background image for menu panel
//        try {
//            JLabel bg = new JLabel(new ImageIcon(ImageIO.read(new File("assets/images/menu-bg.png"))));
//            bg.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
//            menuPanel.add(bg);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        
		try {
			startGameButton = new StartGameButton();
//			creditsButton   = new CreditsButton();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        startGameButton.setBounds(250, 400, 150, 30);
//        creditsButton.setBounds(250, 450, 150, 30);
        
        menuPanel.add(startGameButton);
//        menuPanel.add(creditsButton);
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
    
//    /**
//     * Returns the credits button.
//     * 
//     * @return JButton
//     */
//    public JButton getCreditsButton() {
//		return creditsButton;
//	}

	public UtilityFrame getFrame() {
        return frame;
    }
}
