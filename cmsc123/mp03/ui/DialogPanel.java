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
		
		int midX = width / 4;
		int midY = height / 4;
		
		setSize(width, height);
		
		okButton = new JButton("OK");
		okButton.setBackground(Color.WHITE);
		okButton.setForeground(new Color(255, 128, 0));
		okButton.setBounds(midX + 145, midY + 50, 60, 30);
		add(okButton);

		dialogText = new JLabel(dialog, SwingConstants.CENTER);
		dialogText.setVerticalAlignment(SwingConstants.TOP);
		dialogText.setFont(new Font(dialogText.getName(), Font.BOLD, 40));
		dialogText.setOpaque(true);
		dialogText.setBackground(Color.WHITE);
		dialogText.setForeground(new Color(255, 128, 0));
		dialogText.setBounds(midX, midY, 350, 90);
		add(dialogText);
		
		labelsPanel = new JPanel();
		labelsPanel.setLayout(new BorderLayout());
		labelsPanel.setBackground(new Color(255, 128, 0));
		labelsPanel.setBounds(midX, midY - 20, 350, 110);
		add(labelsPanel);
	}

	public JButton getOkButton() {
	    return okButton;
    }

	public void setOkButton(JButton okButton) {
	    this.okButton = okButton;
    }
}
