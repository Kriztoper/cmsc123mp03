package cmsc123.mp03.framework;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {

    private BufferedImage gameImage; 
    
    public GamePanel() {
        setSize(640, 640);
        gameImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(gameImage, 0, 0, this);
    }
    
    /**
     * Sets the image to be drawn in the panel.
     * 
     * @param BufferedImage gameImage
     */
    public void setGameImage(BufferedImage gameImage) {
        this.gameImage = gameImage;
    }
}
