package com.shs.client.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class AnalyzeTotalView extends JPanel {
	public AnalyzeTotalView() {
		super();
		this.setLayout(new BorderLayout());
		LBTitle lbTitle = new LBTitle("Analyze Wing");
		this.add(lbTitle, BorderLayout.NORTH);
	}
}
