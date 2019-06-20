package com.shs.client.view;

import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EquipmentNeed extends JPanel {

    	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField NombrepieceEquip;
        JTextField CoutInstallation;
        JTextField CoutAchat;
        JButton DevisNouvelEquip;

		
			    //Constructeur du panel
			    public JPanel EquipmentNeed(){
			    	JLabel lab = new JLabel("-- Optional --");
			    	JPanel pane = new JPanel((LayoutManager) new MigLayout());
			    	pane.add(lab, "span");
			    	return pane;
			    }
}
