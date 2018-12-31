package com.blueone.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PNCView extends JPanel{
	
	private int abs;
	private int ord;
	private JButton boutonJeu = new JButton("cliquer");
	public static final int LARGEUR_PANNEAU = 100;
	public static final int HAUTEUR_PANNEAU = 100;
	
	public PNCView(int i, int j) {
		super();
		abs=i;
		ord=j;
		Dimension dim = new Dimension(LARGEUR_PANNEAU, HAUTEUR_PANNEAU);
		this.setBackground(Color.LIGHT_GRAY);
		this.setLayout(new BorderLayout());
		this.setSize(dim);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(boutonJeu, BorderLayout.SOUTH);
		 
	}
	
	public JButton getButton() {
		return boutonJeu;
	}
	
	public int getAbs(){
		return this.abs;
	}
	
	public int getOrd(){
		return this.ord;
	}
}
