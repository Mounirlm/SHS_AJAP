package com.shs.client.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Maintenance implements ActionListener {
		
		super();
		
		JPanel panel = new JPanel();
		panel.setVisible(true);
		JTextField zone_n; 
		JTextField[] zone_nom; JPanel p_sensors; 
		JTextField[] zone_sexe; JPanel p_open; 
		JTextField[] zone_taille; JPanel p_mvt; 
		JButton bouton_ok, bouton_quitter;
		JFrame fen;
		JPanel p = new JPanel();
		public Maintenance() {
			
			// creation + affichage du formulaire
			fen = new JFrame("Input form for devices to be revised");
			p=new JPanel(new GridLayout(6,2));
			fen.add(p);
			p.add(new JLabel("n"));
			zone_n=new JTextField(); p.add(zone_n);
			p.add(new JLabel("Sensors_number"));
			p_sensors=new JPanel(new GridLayout(1, 10)); p.add(p_sensors); 
			zone_nom=new JTextField [10]; 
			for(int i1=0; i1<10; i1++) {
				zone_nom[i1]=new JTextField(); p_sensors.add(zone_nom[i1]);
			}
			p.add(new JLabel("Opening_detectors"));
			p_open=new JPanel(new GridLayout(1, 10)); p.add(p_open); 
			zone_sexe=new JTextField [10]; 
			for(int i1=0; i1<10; i1++) {
				zone_sexe[i1]=new JTextField(); p_open.add(zone_sexe[i1]);
			}
			p.add(new JLabel("Nomber_of_movement_detector"));
			p_mvt=new JPanel(new GridLayout(1, 10)); p.add(p_mvt); 
			zone_taille=new JTextField [10]; 
			for(int i1=0; i1<10; i1++) {
				zone_taille[i1]=new JTextField(); p_mvt.add(zone_taille[i1]);
			}

			bouton_ok=new JButton("calculate the cost of maintenance"); p.add(bouton_ok);
			bouton_ok.addActionListener(this);
			bouton_quitter=new JButton("quitter"); p.add(bouton_quitter);
			bouton_quitter.addActionListener(this);
			fen.pack();
			fen.setVisible(true);
		}
		public void actionPerformed(ActionEvent e) { // actions associees aux boutons
			int i1; 
			int n; 
			String [] Sensors_number = new String[10]; 
			String [] Opening_detectors = new String[10]; 
			double [] Nomber_of_movement_detector = new double[10];  
			if (e.getSource()==bouton_ok) {  
				// recuperation des entrees
				n = Integer.parseInt( zone_n.getText() ); 
				for(i1=0; i1<n; i1++) {
					nom[i1] = zone_nom[i1].getText(); 
				}
				for(i1=0; i1<n; i1++) {
					sexe[i1] = zone_sexe[i1].getText(); 
				}
				// calcul des IMCs
				for(i1=0; i1<n; i1++) {
				   Nomber_of_movement_detector[i1] = Nomber_of_movement_detector+[i1];
				}
				// affichage des IMCs
				for(i1=0; i1<n; i1++) {
					println( nom[i1] + " : " + imc[i1] ); 
				}
			}
			if (e.getSource()==bouton_quitter) {
				fen.dispose();
			}
		}
		public void actionPerformed1(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
}
