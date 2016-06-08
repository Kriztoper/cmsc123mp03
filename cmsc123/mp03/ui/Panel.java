package cmsc123.mp03.ui;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/***
 * JPanel Component that draws the background
 * using paintComponent method
 * 
 * @author Kriztoper Urmeneta
 *
 */
public class Panel extends JPanel {
	
	private ImageIcon bg;
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		bg.paintIcon(this, g, 0,0);
	}

	public ImageIcon getBackGround() {
	    return bg;
    }

	public void setBackGround(ImageIcon bg) {
	    this.bg = bg;
    }
}
