package com.shs.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

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
	public CreateRoomView() {
		super();
		this.setLayout(new BorderLayout());
		//title
		title = new JLabel("New Secured Room");
		title.setOpaque(true);
		title.setBackground(Color.decode("#afabab"));
		title.setForeground(cdApp.getBgApp());
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 30));
		title.setBorder(new LineBorder(cdApp.getBgTitle(), 2));
		
		//pCenter
		JPanel pCenter = new JPanel(new FlowLayout());
		
		JPanel pForm = new JPanel(new GridLayout(8, 1));
		jtfRoomType = new JTextField("Bedroom");
		jtfFloor = new JTextField();
		
		pForm.add(new JPanel());
		
		JLabel lbType =new JLabel("TYPE");
		lbType.setOpaque(true);
		lbType.setBackground(cdApp.getBgThem());
		lbType.setForeground(cdApp.getBgApp());
		lbType.setHorizontalAlignment(JLabel.CENTER);
		lbType.setFont(new Font("Arial", Font.BOLD, 25));
		pForm.add(lbType);
		jtfRoomType.setFont(new Font("Arial", Font.BOLD, 16));
		pForm.add(jtfRoomType);

		pForm.add(new JPanel());
		
		JLabel lbFloor =new JLabel("FLOOR");
		lbFloor.setOpaque(true);
		lbFloor.setBackground(cdApp.getBgThem());
		lbFloor.setForeground(cdApp.getBgApp());
		lbFloor.setHorizontalAlignment(JLabel.CENTER);
		lbFloor.setFont(new Font("Arial", Font.BOLD, 25));
		pForm.add(lbFloor);
		jtfFloor.setFont(new Font("Arial", Font.BOLD,16 ));
		pForm.add(jtfFloor);
		
		
		//Panel Valider
		JPanel pValidate = new JPanel();
		validateButton = new JButton("INSERT");
		validateButton.setBackground(cdApp.getBgThem());
		validateButton.setForeground(cdApp.getBgApp());
		validateButton.setHorizontalAlignment(JLabel.CENTER);
		validateButton.setFont(new Font("Arial", Font.BOLD, 20));
		pValidate.add("inserer",validateButton);
		pForm.add(new JPanel());
		pForm.add(pValidate);
		
		pForm.setPreferredSize(new Dimension(cdApp.getWIDTH()-800, cdApp.getHEIGHT()-300));
		pCenter.add(pForm);
		
		this.add(title, BorderLayout.NORTH);
		this.add(pCenter, BorderLayout.CENTER);
		
	}
	public JTextField getJtfRoomType() {
		return  jtfRoomType;
	}
	public JTextField getJtfFloor() {
		return jtfFloor;
	}
	public void setTitle(String text) {
		title.setText(text);
	}
	public void addJBInsertListenr(ActionListener act) {
		validateButton.addActionListener(act);
	}
}
