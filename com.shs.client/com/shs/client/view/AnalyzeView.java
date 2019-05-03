package com.shs.client.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class AnalyzeView extends JPanel {
	
	private JPanel pCenter;
	private JPanel pTopB;

	public AnalyzeView() {
		super();
		
		this.setLayout(new BorderLayout());	
		pCenter = new JPanel();
		
		//pTop
		JPanel pTop = new JPanel();
		pTop.setLayout(new BorderLayout());
		JLabel title = new JLabel("Analyze indicators");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 35));
		title.setBorder(new LineBorder(ColorsApp.getBgThem()));
		title.setForeground(ColorsApp.getBgThem());
		title.setOpaque(true);
		title.setBackground(ColorsApp.getBgApp());
		//pTopb
		pTopB = new JPanel();
		pTopB.setLayout(new GridLayout(1, 3));
		addTopBarButton("Floor");
		addTopBarButton("Wing");
		addTopBarButton("Total");
		pTop.add(title, BorderLayout.NORTH);
		pTop.add(pTopB, BorderLayout.SOUTH);
		
		//pCenter
		pCenter.setBackground(ColorsApp.getBgApp());
		CardLayout cd = new CardLayout();
		pCenter.setLayout(cd);
		
		AnalyzeFloorView pFloor = new AnalyzeFloorView();
		pCenter.add("floor", pFloor);
		AnalyzeWingView pWing = new AnalyzeWingView();
		pCenter.add("wing", pWing);
		AnalyzeTotalView pTotal = new AnalyzeTotalView();
		pCenter.add("total", pTotal);
		//End
		this.add(pTop, BorderLayout.NORTH);
		this.add(pCenter, BorderLayout.CENTER);
		
	}

	private void addTopBarButton(String buttonTitle) {
		JButton jbAlerts = new JButton(buttonTitle);
		jbAlerts.setBackground(ColorsApp.getBgThem());
		jbAlerts.setForeground(ColorsApp.getBgApp());
		jbAlerts.setFont(new Font("Arial", Font.BOLD, 25));
		jbAlerts.setBorder(new LineBorder(ColorsApp.getBgTitle(),2));

		pTopB.add(jbAlerts);
	}
	
}
