package com.shs.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

// La vue s'occupe de l'afffichage
public class SHSView {
 
	public JFrame frame;
	private static int WIDTH =1600 ;
	private static int HEIGHT =850 ;
	//private int indexB=0;
	private JPanel pMenu;
	private JPanel pApp;
	private Color bgThem = Color.decode("#5b9bd5");
	private Color bgTitle = Color.decode("#1f4e79");
	private Color bgApp = Color.WHITE;
	private Component blogo;
	private ArrayList<JButton> menuItems;
	
	public SHSView() {
		//window properties
		frame = new JFrame("SHS AutonHome");
		frame.setSize(WIDTH,HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(bgApp);
		
		//main layout
		BorderLayout layout = new BorderLayout();
		frame.setLayout(layout);
		
		//Panel Menu
		pMenu= new JPanel();
		pMenu.setLayout(new BorderLayout());
		//pMenu.setBackground(bgThem);
		pMenu.setSize(350,HEIGHT);
		
		//Panel top menu
		JPanel pTopMenu = new JPanel();
		pTopMenu.setLayout(new BorderLayout());
		pTopMenu.setBackground(bgTitle);
		pTopMenu.setSize(350, 90);
		//logo
		blogo = new  JButton(new ImageIcon("ressources\\shs-logo.png"));
		blogo.setBackground(bgApp);
		blogo.setSize(350, 50);
		pTopMenu.add(blogo, BorderLayout.NORTH);
		//title menu
		JLabel lbmenu = new JLabel();
		lbmenu.setText("MENU");
		lbmenu.setHorizontalAlignment(JLabel.CENTER);
		lbmenu.setFont(new Font("Arial", Font.BOLD, 25));
		lbmenu.setForeground(bgApp);
		pTopMenu.add(lbmenu, BorderLayout.SOUTH);
		
		
		//Panel Center menu
		JPanel pCenterMenu = new JPanel();
		pCenterMenu.setLayout(new GridLayout(5,1));
		//pCenterMenu.setBackground(bgThem);
		//Items
		menuItems = new ArrayList<JButton>();
		menuItems.add(new JButton("Supervised Rooms"));
		//Items for R3
		for (int i = 1; i < 5; i++) {
			menuItems.add(new JButton());
		}

		//buttons colors
		
		for (int indexB = 0; indexB < 5; indexB++) {
			menuItems.get(indexB).setBackground(bgThem);
			menuItems.get(indexB).setForeground(bgApp);
			menuItems.get(indexB).setFont(new Font("Arial", Font.BOLD, 25));
			menuItems.get(indexB).setBorder(new LineBorder(bgTitle));
			
		}
		
		//add buttons to pCenterMenu
		for (int i = 0; i < 5; i++) {
			pCenterMenu.add(menuItems.get(i));
		}
		
		//pMenu add
		pMenu.add(pTopMenu, BorderLayout.NORTH);
		pMenu.add(pCenterMenu, BorderLayout.CENTER);
		
		
		//Panel App
		pApp= new JPanel();
		pApp.setLayout(new GridLayout(2,1));
		pApp.setBackground(bgApp);
		
		
		//Window
		frame.getContentPane().add(pMenu);
		frame.getContentPane().add(pApp);
		
		
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
	public void setBgMenuItemByIndex(int i,Color c) {
		menuItems.get(i).setBackground(c);
	}
	
	public void setBgMenuItem(Color c) {
		for(int i =0; i< menuItems.size(); i++)
			menuItems.get(i).setBackground(c);
	}
}