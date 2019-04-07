package com.shs.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class CreateRoomView extends JPanel {
	private ColorsDimApp cdApp = new ColorsDimApp();//TODO METTRE EN STATIC
	private FormView formView;
	private LBTitle lbTitle;
	
	public CreateRoomView() {
		super();
		this.setLayout(new BorderLayout());
		lbTitle = new LBTitle("New Secured Room");
		this.add(lbTitle, BorderLayout.NORTH);
		
		Map<String, String> cols = new LinkedHashMap<>();
		cols.put("TYPE","Bedroom");cols.put("FLOOR","");cols.put("ROOM NUMBER","");
		
		ArrayList<String> buttons = new ArrayList<>();
		buttons.add("INSERT");
		
		formView = new FormView("New Secured Room", cols, buttons, new ArrayList<String>(), "v", false,false, 20,20,20);
		formView.setPreferredSize(new Dimension(cdApp.getWIDTH()-900, cdApp.getHEIGHT()-300));
		this.add(formView, BorderLayout.CENTER);
		
	}
	public JTextField getJtf(int index) {
		return formView.getJtf(index);
	}
	
	public void setTitle(String text) {
		lbTitle.setText(text);
	}
	public void addJBInsertListner(ActionListener act) {
		formView.addJBListner(act);
	}
	public FormView getFormView() {
		return formView;
	}
}
