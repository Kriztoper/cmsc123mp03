package cmsc123.mp03.ui;

import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * 
 * This is a special button that 
 * wraps around the image without boarders
 * 
 * @author Joshua Marinacci
 *
 */
@SuppressWarnings("serial")
public class ImageButton extends JButton{

	public ImageButton(ImageIcon icon) {
		setSize(icon.getImage().getWidth(null),
			icon.getImage().getHeight(null));
		setIcon(icon);
		setMargin(new Insets(0,0,0,0));
		setIconTextGap(0);
		setBorderPainted(false);
		setBorder(null);
		setText(null);

	}

}
