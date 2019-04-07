package com.shs.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MenuView extends JPanel {
	private JButton blogo;
	private ArrayList<JButton> menuItems;
	private JPanel pTopMenu;
	
	public MenuView() {
		super();
		//Panel Menu
		this.setLayout(new BorderLayout());
		
		
		//Panel top menu
		pTopMenu = new JPanel();
		pTopMenu.setLayout(new BorderLayout());
		pTopMenu.setBackground(ColorsApp.getBgTitle());
		//logo
		blogo = new  JButton(new ImageIcon("ressources\\shs-logo.png"));
		blogo.setActionCommand("index");
		blogo.setBackground(ColorsApp.getBgApp());
		pTopMenu.add(blogo, BorderLayout.NORTH);
		//title menu
		JLabel lbmenu = new JLabel();
		lbmenu.setText("MENU");
		lbmenu.setHorizontalAlignment(JLabel.CENTER);
		lbmenu.setFont(new Font("Arial", Font.BOLD, 25));
		lbmenu.setForeground(ColorsApp.getBgApp());
		pTopMenu.add(lbmenu, BorderLayout.SOUTH);
		
		
		//Panel Center menu
		JPanel pCenterMenu = new JPanel();
		pCenterMenu.setLayout(new GridLayout(6,1));
		//Items
		menuItems = new ArrayList<JButton>(6);
		menuItems.add(new JButton("Supervised Rooms"));
		//Items for R3
		for (int i = 1; i < 6; i++) {
			menuItems.add(new JButton());
		}

		//buttons colors
		
		for (int indexB = 0; indexB < menuItems.size(); indexB++) {
			menuItems.get(indexB).setBackground(ColorsApp.getBgThem());
			menuItems.get(indexB).setForeground(ColorsApp.getBgApp());
			menuItems.get(indexB).setFont(new Font("Arial", Font.BOLD, 25));
			menuItems.get(indexB).setBorder(new LineBorder(ColorsApp.getBgTitle()));
			
		}
		
		//add buttons to pCenterMenu
		for (int i = 0; i < menuItems.size(); i++) {
			pCenterMenu.add(menuItems.get(i));
		}
		
		//pMenu add
		this.add(pTopMenu, BorderLayout.NORTH);
		this.add(pCenterMenu, BorderLayout.CENTER);
	}
	
	public void setBgMenuItemByIndex(int i,Color c) {
		menuItems.get(i).setBackground(c);
	}
	
	public void setBgMenuItem(Color c) {
		for(int i =0; i< menuItems.size(); i++)
			menuItems.get(i).setBackground(c);
	}

	public JPanel getpTopMenu() {
		return pTopMenu;
	}

	public void setpTopMenuBg(Color c) {
		this.pTopMenu.setBackground(c);
	}

	public void addMenuListner(ActionListener act) {
		for (int i = 0; i < menuItems.size(); i++) {
			menuItems.get(0).addActionListener(act);
		}
	}

	public void addLogoListneract(ActionListener act) {
		blogo.addActionListener(act);
		
	}
}
