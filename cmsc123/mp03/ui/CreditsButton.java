package cmsc123.mp03.ui;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * This is an image button that
 * shows the credit of the game when clicked.
 * 
 * @author Kriztoper Urmeneta
 *
 */
public class CreditsButton extends ImageButton {
	
	public CreditsButton() throws IOException {

		super(new ImageIcon(ImageIO.read(new File("assets/images/credits-normal.png"))));
	}
}
