package com.blueone.app;

import javax.swing.SwingUtilities;

import com.blueone.controller.TTTController;
import com.blueone.model.TTTModel;
import com.blueone.view.TTTView;

public class App {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				TTTView view  = new TTTView(3,3);
				TTTModel model = new TTTModel();
				TTTController unJeu = new TTTController(model,view);
				//unJeu.controle();
			}
		});
		

	}

}
