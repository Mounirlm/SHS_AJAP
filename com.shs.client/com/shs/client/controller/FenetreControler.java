package com.shs.client.controller;

import javax.swing.JFrame;

//Fenetre avec les différents panels
public class FenetreControler extends JFrame implements KeyListener{
   
  //Attributs
  //Différents menus
  public PanelMenu panelMenu;
  public PanelEquipment panelEquipment;
  //Autres attributs
  private int menuActif;
  Profil profilActif;
   
  //Constructeur de la fenetre
  public FenetreControler(Profil prof){
       
      //Stockage profil actif
      this.profilActif=prof;
       
      //Réglage des paramètres
      this.setSize(800, 600);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setResizable(false);
      this.setLocationRelativeTo(null);
      this.setVisible(true);
      this.setFocusable(true);
      this.addKeyListener(this);
       
      //Création des futurs panels
      this.panelMenu = new PanelMenu(this);
      this.panelEquipment = new PanelEquipment(this);
       
  }
   
  //Méthode concernant les menus (changement)
  public void changerMenu(int menuSelectionne){
       
      //On change de panel en fonction du nombre passé en paramètre
      switch(menuSelectionne){
       
      //Menu de base (selection)
      case 0:
          this.setContentPane(this.panelMenu);
          this.menuActif=0;
          this.repaint();
          this.revalidate();
          break;
      //Le jeu
      case 1:
          this.setContentPane(this.panelEquipment);
          this.repaint();
          this.revalidate();
          this.menuActif=1;
          break;
      case 2:
          break;

      case 3:
          break;

      case 4:
          break;
 
      default:
          System.err.println("Menu selectionne non valide");
          break;
      }
  }
   
  /*
   * LES KEYS LISTENERs
   * ->Raccourcis clavier
   * Menu pause
  */
  public void keyPressed(KeyEvent touche) {
      //Touche appuyée est echap durant le jeu -> menu pause
      if(touche.getKeyCode()==touche.VK_ESCAPE && this.menuActif==1){
          this.changerMenu(0);
      }
  }
   
  public void keyReleased(KeyEvent arg0) {
       
       
  }
   
  public void keyTyped(KeyEvent arg0) {
       
       
  }
   
}