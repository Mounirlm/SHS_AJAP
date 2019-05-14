package com.shs.client.app;

import java.io.IOException;
import java.sql.SQLException;

import com.shs.client.controller.AbstractControler;
import com.shs.client.controller.CalculetteControler;
import com.shs.client.model.AbstractModel;
import com.shs.client.model.Calculator;
import com.shs.client.view.Calculette;

public class Main {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
		Application app = new Application();
		app.start();
		 AbstractModel calc = new Calculator();
		    //Création du contrôleur
		    AbstractControler controler = new CalculetteControler(calc);
		    //Création de notre fenêtre avec le contrôleur en paramètre
		    Calculette calculette = new Calculette(controler);
		    //Ajout de la fenêtre comme observer de notre modèle
		    calc.addObserver(calculette);
		

	}

}
