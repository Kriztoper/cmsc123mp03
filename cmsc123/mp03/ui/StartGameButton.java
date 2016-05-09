package cmsc123.mp03.ui;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * This is an image button that
 * starts the game when clicked.
 * 
 * @author Kriztoper Urmeneta
 *
 */
public class StartGameButton extends ImageButton {
	
	public StartGameButton() throws IOException {

		super(new ImageIcon(ImageIO.read(new File("assets/images/start-normal.png"))));
	}
}
