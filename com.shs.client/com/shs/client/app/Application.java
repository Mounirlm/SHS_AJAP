package com.shs.client.app;

import java.sql.SQLException;

import com.shs.client.controller.DBConnectController;
import com.shs.client.view.SHSView;

public class Application {
	DBConnectController shsController;
	
	public Application() throws SQLException, ClassNotFoundException {
		shsController = new DBConnectController(new SHSView());
		
	}
	
	public void start() throws SQLException {
		shsController.start();	
	}
}
