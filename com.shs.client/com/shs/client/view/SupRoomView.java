package com.shs.client.view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class SupRoomView extends JPanel{
	private ColorsDimApp cdApp;
	public SupRoomView() {
		super();
		cdApp = new ColorsDimApp();
		this.setLayout(new BorderLayout());
		JPanel pWelcom = new JPanel();
		JLabel lbWelcom = new JLabel("Suppervised Rooms");
		lbWelcom.setHorizontalAlignment(JLabel.CENTER);
		lbWelcom.setFont(new Font("Arial", Font.BOLD, 45));
		
		pWelcom.add(lbWelcom, BorderLayout.CENTER);
		pWelcom.setBorder(new LineBorder(cdApp.getBgTitle()));
		this.add(pWelcom);
		
	}
}
