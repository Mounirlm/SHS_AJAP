package com.shs.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.shs.client.controller.RoomController;

// La vue s'occupe de l'afffichage
public class SHSView {
 
	public JFrame frame;
	public SHSView() {
		frame = new JFrame("SHS");
		BorderLayout layout = new BorderLayout();
		frame.setLayout(layout);
		frame.setResizable(false);
		frame.setBounds(500, 200, 500, 400);
		frame.setBackground(Color.black);
		
		//Panel Titre
		JPanel pTitle = new JPanel(new FlowLayout());
		pTitle.setBackground(Color.white);
		pTitle.add(new JLabel("New secured room"));
		frame.add(pTitle,BorderLayout.NORTH);
		
		//Panel Formulaire
		JPanel pForm = new JPanel(new GridLayout(4, 1));
		JTextField jtfRoomType = new JTextField("Room",20);
		JTextField jtfFloor = new JTextField(20);
		pForm.add(new JLabel("Type"));
		pForm.add(jtfRoomType);
		pForm.add(new JLabel("Floor"));
		pForm.add(jtfFloor);
		frame.add(pForm, BorderLayout.CENTER);
		
		//Panel Valider
		JPanel pValidate = new JPanel();
		JButton validateButton = new JButton("Confirm");
		//validateButton.addActionListener(new RoomController());
		validateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] form = new String[2];
				form[0] = jtfRoomType.getText();
				form[1] = jtfFloor.getText();
				try {
					RoomController.insert(form);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		pValidate.add("inserer",validateButton);
		frame.add(pValidate, BorderLayout.SOUTH);
		show();
	}
	
	private void show() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
 
}