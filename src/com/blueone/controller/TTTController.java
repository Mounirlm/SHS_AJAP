package com.blueone.controller;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.blueone.model.TTTModel;
import com.blueone.view.PNCView;
import com.blueone.view.TTTView;
 
public class TTTController implements ActionListener{
 
 private TTTModel model;
 private TTTView view;
 private ActionListener act;
 
 public TTTController(TTTModel m, TTTView v) {
  model = m;
  view = v;
  initButton();
 }
 

 
 private void initButton() {
	 for(int i = 0; i<view.getPnc().length; i++){
			for(int j = 0; j<view.getPnc().length; j++){
					view.getPnc()[i][j].getButton().addActionListener(this);
			}
	 }		
	
}





	@Override
	public void actionPerformed(ActionEvent ev) {
		int abs=0, ord=0;
		int numJoueur;
		JButton but =null;
		if(ev.getSource() instanceof JButton ){
			but = (JButton) ev.getSource();
			PNCView p = view.getPanneau(but);
			abs = p.getAbs();
			ord = p.getOrd();
		}
		
		
		model.incNbreEssai();
		if(model.getNbreEssai()%2==1) {
			view.getPanneau(but).setBackground(Color.RED);
			model.setTabJeu(abs,ord, 1); 
			numJoueur = 1;
		}
		else {
			view.getPanneau(but).setBackground(Color.BLUE);
			model.setTabJeu(abs,ord, 2);
			numJoueur = 2;
		}
		but.setVisible(false);
		if(model.verif(abs, ord)){
			model.afficher("Le joueur "+numJoueur+" a gagné");
			System.exit(0);
		}
		else if(model.getNbreEssai() == 9) {
			model.afficher("Le jeu est fini, il n'y a pas de gagnant");
		}
		
	}
	
 

 
}