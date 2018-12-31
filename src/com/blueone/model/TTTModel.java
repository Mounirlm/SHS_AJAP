package com.blueone.model;
public class TTTModel {
 
	private int [] [] tabJeu;
	private int nbreEssai;
 
	 public TTTModel() {
		 tabJeu = new int [3] [3] ;
		 nbreEssai =0;
		 for(int i=0; i<tabJeu.length; i++){
			for(int j=0; j<tabJeu.length; j++){
				tabJeu[i][j] = 0;
			}
		}
	 }

	public void incNbreEssai() {
		this.nbreEssai++;
		
	}

	public int getNbreEssai() {
		return this.nbreEssai;
	}

	public void setTabJeu(int abs, int ord, int i) {
		tabJeu[abs][ord] =i;
		
	}
	
	public boolean verifLigne(int abs,int ord) {
		boolean b = true;
		for(int col=0; col<tabJeu.length; col++) {
			b=b && (tabJeu[abs][col] == tabJeu[abs][ord]);
		}
		return b;
	}
	
	public boolean verifColonne(int abs,int ord) {
		boolean b = true;
		for(int lig=0; lig<tabJeu.length; lig++) {
			b=b && (tabJeu[lig][ord] == tabJeu[abs][ord]);
		}
		return b;
	}
	
	public boolean verifDiagonale(int abs,int ord) {
		boolean b = true;
		if(abs!=ord) {
			return false;
		}
		else {
			for(int j=0; j<tabJeu.length; j++) {
				b=b && (tabJeu[j][j] == tabJeu[abs][ord]);
			}
		}
		
		return b;
	}
	
	public boolean verifDiagonale2(int abs,int ord) {
		boolean b = true;
		//if((abs==0 && ord==0) || (abs==0 && ord==1) || (abs==1 && ord==0) || (abs==1 && ord==2) || (abs==2 && ord==0) || (abs==0 && ord==1) ) {
		if(abs+ord+1!= tabJeu.length) {
			return false;
		}
		else {
			for(int j=1; j<tabJeu.length; j++) {
				b=b && (tabJeu[j][tabJeu.length-j-1] == tabJeu[abs][ord]);
			}
		}
		
		return b;
	}
	
	public boolean verif(int abs,int ord) {
		return (verifLigne(abs,ord) || verifColonne(abs,ord) || verifDiagonale(abs,ord) || verifDiagonale2(abs,ord));
	}

	public void afficher(String string) {

		System.out.println(string);
		
	}

 
}