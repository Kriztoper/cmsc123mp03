package cmsc123.mp03.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {

    private static final int DEFAULT_WIDTH  = 640;
    private static final int DEFAULT_HEIGHT = 640;
    
    private JPanel currentPanel;
    
    public GameFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
    
    public JPanel getCurrentPanel() {
        return currentPanel;
    }
    
    public void setCurrentPanel(JPanel panel) {
        remove(currentPanel);
        currentPanel = panel;
        add(currentPanel);
    }
    
}
