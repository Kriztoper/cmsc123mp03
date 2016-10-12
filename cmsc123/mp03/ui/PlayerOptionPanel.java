package cmsc123.mp03.ui;

import javax.swing.JButton;

/**
 * Description: Gives the player an option to play PvP (Player vs Player) or
 * 				PvC (Player vs CPU)
 * Author: Kriztoper Urmeneta
 * 
 * */

public class PlayerOptionPanel extends Panel {
	
	private JButton pvpButton; /* Player vs Player button */
	private JButton pvcButton; /* Player vs CPU button */
	
	public PlayerOptionPanel() {
		
	}

	public JButton getPvpButton() {
	    return pvpButton;
    }

	public void setPvpButton(JButton pvpButton) {
	    this.pvpButton = pvpButton;
    }

	public JButton getPvcButton() {
	    return pvcButton;
    }

	public void setPvcButton(JButton pvcButton) {
	    this.pvcButton = pvcButton;
    }
	
	
	
}
