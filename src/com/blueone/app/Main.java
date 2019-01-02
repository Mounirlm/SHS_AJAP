package com.blueone.app;

import java.sql.SQLException;

import javax.swing.SwingUtilities;

import com.blueone.controller.DBconnectController;
import com.blueone.model.JDBCConnectionPoolModel;
import com.blueone.view.SHSView;

public class Main {

	public static void main(String[] args) throws SQLException {
		Application app = new Application();
		app.start();
		

	}

}
