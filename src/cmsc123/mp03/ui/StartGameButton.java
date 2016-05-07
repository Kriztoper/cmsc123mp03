package cmsc123.mp03.ui;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class StartGameButton extends ImageButton {
	
	public StartGameButton() throws IOException {

		super(new ImageIcon(ImageIO.read(new File("assets/images/start-normal.png"))));
	}
}
