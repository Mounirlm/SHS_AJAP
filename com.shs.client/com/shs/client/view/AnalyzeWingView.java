package com.shs.client.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class AnalyzeWingView extends JPanel {
	public AnalyzeWingView() {
		super();
		this.setLayout(new BorderLayout());
		LBTitle lbTitle = new LBTitle("Analyze Total");
		this.add(lbTitle, BorderLayout.NORTH);
	}
}
