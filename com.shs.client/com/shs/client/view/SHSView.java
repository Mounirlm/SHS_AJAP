package com.shs.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.shs.client.controller.RoomController;

// La vue s'occupe de l'afffichage
public class SHSView {
 
	public JFrame frame;
	private Color bckGApp = Color.white;
	private static int WIDTH =1600 ;
	private static int HEIGHT =850 ;
	
	
	public SHSView() {
		//window
		frame = new JFrame("SHS AutonHome");
		frame.setSize(WIDTH,HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setBackground(bckGApp );
		
		
		BorderLayout layout = new BorderLayout();
		frame.setLayout(layout);
		
		//Panel Titre
		JPanel pTitle = new JPanel(new FlowLayout());
		pTitle.setBackground(Color.white);
		JLabel title = new JLabel("New secured room");
		pTitle.add(title);
		frame.add(pTitle,BorderLayout.NORTH);
		
		//Panel Formulaire
		JPanel pForm = new JPanel(new GridLayout(4, 1));
		JTextField jtfRoomType = new JTextField("Bedroom",20);
		JTextField jtfFloor = new JTextField(20);
		pForm.add(new JLabel("Type"));
		pForm.add(jtfRoomType);
		pForm.add(new JLabel("Floor"));
		pForm.add(jtfFloor);
		frame.add(pForm, BorderLayout.CENTER);
		
		//Panel Valider
		JPanel pValidate = new JPanel();
		JButton validateButton = new JButton("Confirm");
		validateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] form = new String[2];
				form[0] = jtfRoomType.getText();
				form[1] = jtfFloor.getText();
				try {
					RoomController.insert(form);
					title.setText("Inserted new secured room with success");
					
				} catch (Exception e1) {
					title.setText(e1.getMessage());
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