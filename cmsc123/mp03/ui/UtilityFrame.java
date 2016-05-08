package cmsc123.mp03.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * This is a JFrame that contains one panel
 * at a time.
 * 
 * @author John Paul Ada
 *
 */
@SuppressWarnings("serial")
public class UtilityFrame extends JFrame {

    private static final int DEFAULT_WIDTH  = 640;
    private static final int DEFAULT_HEIGHT = 640;
    
    private JPanel currentPanel;
    
    /**
     * Initializes this frame.
     */
    public UtilityFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    /**
     * Returns the active panel.
     * 
     * @return JPanel
     */
    public JPanel getCurrentPanel() {
        return currentPanel;
    }
    
    /**
     * Sets the active panel.
     * 
     * @param panel
     */
    public void setCurrentPanel(JPanel panel) {
        if (currentPanel != null) {
            remove(currentPanel);
        }

        currentPanel = panel;
        add(currentPanel);

        this.repaint();
    }
    
}
