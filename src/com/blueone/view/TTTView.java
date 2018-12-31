package com.blueone.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.blueone.view.PNCView;
 
public class TTTView  extends JPanel{
 
	public static final int LARGEUR = 320;
	public static final int HAUTEUR = 320;
	public static final int lig = 3;
	public static final int col = 3;
	private PNCView[][] pnc = new PNCView[3][3] ;
	
	private JFrame fenetreJeu = new JFrame ( "la fenetre pour jouer");
 
	public TTTView(int lig, int col) {
		fenetreJeu.setLayout(new GridLayout(3,3));
		fenetreJeu.setBackground(Color.BLACK);
		Dimension dim0 = new Dimension(HAUTEUR, LARGEUR);
		fenetreJeu.setSize(dim0);
		fenetreJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		for(int i = 0; i<lig; i++){
			for(int j = 0; j<col; j++){
				pnc[i][j] = new PNCView(i, j);
				fenetreJeu.getContentPane().add(pnc[i][j]);
			}
		}
		fenetreJeu.setVisible(true);
		
	}
  
 
	 public JFrame getfenetreJeu() {
	  return this.fenetreJeu;
	 }
	 
	
	public PNCView getPanneau(JButton b) {
		PNCView p = null;
		for(int i = 0; i<lig; i++){
			for(int j = 0; j<col; j++){
				if (pnc[i][j].getButton() == b) {
					p=pnc[i][j];
				} 
			}
		}
		return p;
	}
	
	public PNCView[][] getPnc() {
		return this.pnc;
	}
 
}