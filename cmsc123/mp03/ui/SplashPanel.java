package cmsc123.mp03.ui;

import java.awt.BorderLayout;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SplashPanel extends Panel {
	
	private JLabel splashLabel;
	
	public SplashPanel() {
		setLayout(new BorderLayout());
		setSize(640, 640);
		try {
	        setBackGround(new ImageIcon(this.getClass().getResource("/images/loading.png")));
        	splashLabel = new JLabel(new ImageIcon(this.getClass().getResource("/images/hourglass.gif")));
        } catch (Exception e) {
	        e.printStackTrace();
        }
    	add(splashLabel, BorderLayout.CENTER);
	}
}
