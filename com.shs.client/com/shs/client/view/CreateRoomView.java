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
	private JLabel title;
	private ColorsDimApp cdApp = new ColorsDimApp();//TODO METTRE EN STATIC
	private JTextField jtfRoomType;
	private JTextField jtfFloor;
	private JButton validateButton;
	private FormView formView;
	private LBTitle lbTitle;
	
	public CreateRoomView() {
		super();
		this.setLayout(new BorderLayout());
		lbTitle = new LBTitle("New Secured Room");
		this.add(lbTitle, BorderLayout.NORTH);
		
		Map<String, String> cols = new LinkedHashMap<>();
		cols.put("ID","");cols.put("TYPE","");cols.put("FLOOR","");cols.put("ROOM NUMBER","");
		
		ArrayList<String> buttons = new ArrayList<>();
		buttons.add("INSERT");
		
		formView = new FormView("New Secured Room", cols, buttons, new ArrayList<String>(), "v", false);
		formView.setPreferredSize(new Dimension(cdApp.getWIDTH()-900, cdApp.getHEIGHT()-300));
		this.add(formView, BorderLayout.CENTER);
		
	}
	public JTextField getJtfRoomType() {
		//return  jtfRoomType;
		return null;
	}
	public JTextField getJtfFloor() {
		//return jtfFloor;
		return null;
	}
	public void setTitle(String text) {
		//title.setText(text);
	}
	public void addJBInsertListenr(ActionListener act) {
		//validateButton.addActionListener(act);
	}
}
