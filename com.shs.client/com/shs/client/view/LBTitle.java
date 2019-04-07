package com.shs.client.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class LBTitle extends JLabel{

	public LBTitle(String title) {
		super();
		//title
		this.setText(title);
		this.setOpaque(true);
		this.setBackground(Color.decode("#afabab"));
		this.setForeground(ColorsApp .getBgApp());
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setFont(new Font("Arial", Font.BOLD, 30));
		this.setBorder(new LineBorder(ColorsApp.getBgTitle(), 2));
	}
}
