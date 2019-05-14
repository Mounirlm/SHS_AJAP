package com.shs.client.view;

import java.awt.BorderLayout;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.shs.client.controller.AbstractControler;
import com.shs.client.controller.FenetreControler;
import com.shs.client.model.Observer;
import com.shs.client.view.EquipmentView.SearchView;

	public class EquipmentNeed extends JPanel implements ActionListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private CardLayout cd;
		private ExtensionView bAgrandissement1;
		private EquipmentAdd bDevis1;
		private Maintenance bMaintenance1;
		private JPanel pCenter;
		public Observer observer;
	   
	  //L'instance de notre objet contrôleur
	  private AbstractControler controler;

			     
			    //Fenetre ouverte
			    FenetreControler fenetreP;
			     
			    //Boutons personnalisés
			    JButton bDevis = new JButton(" Add new equipment");
			    JButton bAgrandissement = new JButton("Enlargment Quote");
			    JButton bMaintenance = new JButton("Maintenance");

			     
			    //Constructeur du panel
			    public EquipmentNeed(FenetreControler fenetrePrincipale){
			         
			        //Fenetre ouverte
			        this.fenetreP = fenetrePrincipale;
			         
			        //Organisation du centre
			        JPanel centre = new JPanel();
			        GridLayout gdly = new GridLayout(3,1);
			        this.setLayout(null);
			        centre.setLayout(gdly);
			         
			        //Ajout des boutons dans le centre
			        centre.add(this.bDevis1);
			        centre.add(this.bAgrandissement1);
			        centre.add(this.bMaintenance1);
			       
			 
			        //Ajout des listener aux boutons
			        this.bDevis1.addActionListener(this);
			        this.bAgrandissement1.addActionListener(this);
			        this.bMaintenance1.addActionListener(this);
			         
			        //Position et taille du menu
			        centre.setBounds(247, 200, 300, 350);
			         
			        //Ajout des panels
			        this.add(centre);
			    }
			 
			    public void actionPerformed(ActionEvent clic) {
			        //Différentes actions en fonction du bouton
			        
			        //Bouton jouer
			        if(clic.getSource()==this.bDevis1){
			            this.fenetreP.changerMenu(1);
			        }
			        //Bouton meilleurs scores
			        if(clic.getSource()==this.bAgrandissement1){
			            this.fenetreP.changerMenu(2);
			        }
			        //Bouton xp
			        if(clic.getSource()==this.bMaintenance1){
			            this.fenetreP.changerMenu(3);
			        }
			       
			    }

}