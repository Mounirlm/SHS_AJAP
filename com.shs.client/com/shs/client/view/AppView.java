package com.shs.client.view;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public class AppView extends JPanel{
	private ColorsDimApp cdApp;
	private CardLayout cdViewApp;
	private ArrayList<JPanel> appView;
	private SupRoomView supRoomView;
	private IndexView indexView;
	
	public AppView() {
		super();
		cdApp = new ColorsDimApp();
		this.setBackground(cdApp.getBgApp());
		
		cdViewApp = new CardLayout();
		this.setLayout(cdViewApp);
		//new views
		appView = new ArrayList<>(7);
		indexView = new IndexView();
		supRoomView = new SupRoomView();
		
		//add views in list views
		appView.add(indexView);
		appView.add(supRoomView);
		//add other vieew for R3
		for (int i = 2; i < appView.size(); i++) {
			appView.add(new JPanel());
		}
		
		//fill cardLayout
		this.add("indexView", appView.get(0));
		this.add("supRoomView", appView.get(1));
		//add other vieew for R3
		for (int i = 2; i < appView.size(); i++) {
			this.add("others", appView.get(i));
		}
		
		cdViewApp.show(this, "indexView");			
		
	}
	

	public void setCard(String name) {
		cdViewApp.show(this, name);
		
	}


	public SupRoomView getSupRoomView() {
		return supRoomView;
	}
}
