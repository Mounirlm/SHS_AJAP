package com.shs.client.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ConnectionView extends JPanel{
	private FormView formView;
	private JPanel pCon;
	private ColorsDimApp cdApp= new ColorsDimApp();
	private JLabel lImage;
	private JLabel message;
	public ConnectionView() {
		super();
		this.setLayout(new GridBagLayout());
	
		pCon=new JPanel(new BorderLayout());
		pCon.setBackground(cdApp.getBgApp());
		//Image
		lImage = new JLabel(new ImageIcon("ressources\\shs-logo.png"));
		
		//FormConstruction
		Map<String, String> cols = new LinkedHashMap<>();
		cols.put("Email","");cols.put("Password","");
		
		ArrayList<String> buttons = new ArrayList<>();
		buttons.add("LOGIN");
		
		formView = new FormView("Connection", cols, buttons, new ArrayList<String>(), "v", true, 20,20,20);
		//formView.setBackground(cdApp.getBgApp());
		//add in view
		pCon.add(lImage, BorderLayout.NORTH);
		pCon.add(formView, BorderLayout.CENTER);
		pCon.setPreferredSize(new Dimension(cdApp.getWIDTH()-1200, cdApp.getHEIGHT()-500));
		pCon.setBorder(new LineBorder(cdApp.getBgTitle(), 2));
		
		//message
		message=new JLabel("Enter your email and password");
		message.setHorizontalAlignment(JLabel.CENTER);
		message.setForeground(cdApp.getBgTitle());
		pCon.add(message, BorderLayout.SOUTH);
		this.add(pCon);
		
	}
}
