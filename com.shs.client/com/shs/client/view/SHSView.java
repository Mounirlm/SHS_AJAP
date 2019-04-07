package com.shs.client.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.shs.client.controller.MenuController;
import com.shs.client.controller.RoomController;

// La vue s'occupe de l'afffichage
public class SHSView {
 
	public JFrame frame;
	
	private MenuView pMenu;
	private AppView pApp;
	private ColorsDimApp cdApp;
	private JPanel appPanel;
	private ConnectionView connectionPanel;
	private CardLayout cd;
	
	public SHSView() {
		cdApp = new ColorsDimApp();
		
		//window properties
		frame = new JFrame("SHS AutonHome");
		frame.setSize(cdApp.getWIDTH(),cdApp.getHEIGHT());
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(cdApp.getBgApp());
		
		//main layout
		cd = new CardLayout();
		frame.setLayout(cd);
		
		//appPanel
		appPanel=new JPanel(new BorderLayout());
		//Panel Menu
		pMenu = new MenuView();
		//Panel App
		pApp= new AppView();
		appPanel.add(pMenu, BorderLayout.WEST);
		appPanel.add(pApp, BorderLayout.CENTER);
		
		
		//connectionPanel
		connectionPanel= new ConnectionView();
		
		//Window
		frame.getContentPane().add("appPanel", appPanel);
		frame.getContentPane().add("connectionPanel", connectionPanel);
		cd.show(frame.getContentPane(), "connectionPanel");
		show();
	}
	
	private void show() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void addMenuListner(ActionListener act) {
		pMenu.addMenuListner(act);
	}

	public void setCardAppView(String s) {
		pApp.setCard(s);
		
	}

	public void addLogoListner(ActionListener act) {
		pMenu.addLogoListneract(act);
		
	}

	public void addRoomMenuListner(ActionListener act) {
		pApp.getSupRoomView().addRoomMenuListner(act);
		
	}


	public void setCardRoom(String name) {
		pApp.getSupRoomView().setCard(name);
		
	}

	public JTextField getJtfCreate(int index) {
		return pApp.getSupRoomView().getCreateView().getFormView().getJtf(index);
	}
	
	public JTextField getJtfRudSearch(int index) {
		return pApp.getSupRoomView().getRudView().getSearchView().getFormView().getJtf(index);
	}
	
	public JTextField getJtfRudUpdate(int index) {
		return pApp.getSupRoomView().getRudView().getUpdateView().getFormView().getJtf(index);
	}


	public void addJBInsertListner(ActionListener act) {
		pApp.getSupRoomView().addJBInsertListner(act);
	}

	public MenuView getpMenu() {
		return pMenu;
	}

	public AppView getpApp() {
		return pApp;
	}

	public void addJBSearchListner(ActionListener jbSearch) {
		pApp.getSupRoomView().getRudView().getSearchView().getFormView().addJBListner(jbSearch);
		
	}

	public void addJBUpdateListner(ActionListener jbUpdate) {
		pApp.getSupRoomView().getRudView().getUpdateView().getFormView().addJBListner(jbUpdate);	
	}

	public void addJBDeleteListner(ActionListener act) {
		pApp.getSupRoomView().getRudView().getDeleteView().getFormView().addJBListner(act);	
		
	}

	public JTextField getJtfRudDelete(int i) {
		return pApp.getSupRoomView().getRudView().getDeleteView().getFormView().getJtf(i);
	}

	
}