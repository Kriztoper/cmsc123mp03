package cmsc123.mp03.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DialogPanel extends Panel {
	
	private JPanel labelsPanel;
	private JLabel dialogText;
	private JButton okButton;
	
	public DialogPanel(String dialog, int width, int height) {
		
		super.setSize(width, height);
		
		// NOTE: Don't rearrange the order of adding the components. It will affect the output!
		
		okButton = new JButton("OK");
		okButton.setBackground(Color.WHITE);
		okButton.setForeground(new Color(255, 128, 0));
		okButton.setBounds(300, 200, 55, 30);
		super.add(okButton);

		dialogText = new JLabel(dialog, SwingConstants.CENTER);
		dialogText.setVerticalAlignment(SwingConstants.TOP);
		dialogText.setFont(new Font(dialogText.getName(), Font.BOLD, 40));
		dialogText.setOpaque(true);
		dialogText.setBackground(Color.WHITE);
		dialogText.setForeground(new Color(255, 128, 0));
		dialogText.setBounds(150, 150, 350, 90);
		super.add(dialogText);
		
		labelsPanel = new JPanel();
		labelsPanel.setLayout(new BorderLayout());
		labelsPanel.setBackground(new Color(255, 128, 0));
		labelsPanel.setBounds(150, 130, 350, 110);
		super.add(labelsPanel);
	}

	public JButton getOkButton() {
	    return okButton;
    }

	public void setOkButton(JButton okButton) {
	    this.okButton = okButton;
    }
}
