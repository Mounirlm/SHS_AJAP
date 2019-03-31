package com.shs.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class IndexView extends JPanel {
	private ColorsDimApp cdApp;
	public IndexView() {
		super();
		cdApp = new ColorsDimApp();
		this.setLayout(new BorderLayout());
		JPanel pWelcom = new JPanel();
		JLabel lbWelcom = new JLabel("Welcom to the dashboard !");
		lbWelcom.setHorizontalAlignment(JLabel.CENTER);
		lbWelcom.setFont(new Font("Arial", Font.BOLD, 45));
		
		pWelcom.add(lbWelcom, BorderLayout.SOUTH);
		pWelcom.setBorder(new LineBorder(cdApp.getBgTitle()));
		this.add(pWelcom);
		
	}
}
