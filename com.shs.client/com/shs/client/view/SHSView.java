package com.shs.client.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.shs.client.controller.MenuController;

// La vue s'occupe de l'afffichage
public class SHSView {
 
	public JFrame frame;
	
	//private int indexB=0;
	private MenuView pMenu;
	private AppView pApp;
	private ColorsDimApp cdApp;
	
	public SHSView() {
		cdApp = new ColorsDimApp();
		
		//window properties
		frame = new JFrame("SHS AutonHome");
		frame.setSize(cdApp.getWIDTH(),cdApp.getHEIGHT());
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(cdApp.getBgApp());
		
		//main layout
		BorderLayout layout = new BorderLayout();
		frame.setLayout(layout);
		
		//Panel Menu
		pMenu = new MenuView();
		
		//Panel App
		pApp= new AppView();
		
		
		//Window
		frame.getContentPane().add(pMenu, BorderLayout.WEST);
		frame.getContentPane().add(pApp, BorderLayout.CENTER);
		
		
		/*//Panel Titre
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
		frame.add(pValidate, BorderLayout.SOUTH);*/
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
}