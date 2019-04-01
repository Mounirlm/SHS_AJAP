package com.shs.client.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class SupRoomView extends JPanel{
	private ColorsDimApp cdApp;
	private JButton jbCreate;
	private JButton jbRUD;
	private CreateView pCreate;
	
	public SupRoomView() {
		super();
		cdApp = new ColorsDimApp();
		this.setLayout(new BorderLayout());
		JPanel pTop = new JPanel();
		JPanel pCenter = new JPanel();
		
		//pTop
		pTop.setLayout(new BorderLayout());
		JPanel pTopB = new JPanel();
		pTopB.setLayout(new GridLayout(1, 2));
		JLabel title = new JLabel("Suppervised Rooms");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 35));
		title.setBorder(new LineBorder(cdApp.getBgThem()));
		title.setForeground(cdApp.getBgThem());
		title.setOpaque(true);
		title.setBackground(cdApp.getBgApp());
		//pTopb
		jbCreate = new JButton("CREATE");
		jbCreate.setBackground(cdApp.getBgThem());
		jbCreate.setForeground(cdApp.getBgApp());
		jbCreate.setFont(new Font("Arial", Font.BOLD, 25));
		jbCreate.setBorder(new LineBorder(cdApp.getBgTitle(),2));
		
		jbRUD = new JButton("READ - UPDATE - DELETE");
		jbRUD.setBackground(cdApp.getBgThem());
		jbRUD.setForeground(cdApp.getBgApp());
		jbRUD.setFont(new Font("Arial", Font.BOLD, 25));
		jbRUD.setBorder(new LineBorder(cdApp.getBgTitle(), 2));
		
		pTopB.add(jbCreate);
		pTopB.add(jbRUD);
		pTop.add(title, BorderLayout.NORTH);
		pTop.add(pTopB, BorderLayout.SOUTH);
		
		//pCenter
		pCenter.setBackground(cdApp.getBgApp());
		pCenter.setLayout(new CardLayout());
		
		pCreate = new CreateView();
		
		pCenter.add("create", pCreate);
		
		
		this.add(pTop, BorderLayout.NORTH);
		this.add(pCenter, BorderLayout.CENTER);
		
		
	}
}
