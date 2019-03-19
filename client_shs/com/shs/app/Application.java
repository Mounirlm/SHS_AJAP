package com.shs.app;

import java.sql.SQLException;

import com.shs.controller.DBConnectController;
import com.shs.view.SHSView;

public class Application {
	DBConnectController shsController;
	
	public Application() throws SQLException, ClassNotFoundException {
		shsController = new DBConnectController(new SHSView());
		
	}
	
	public void start() throws SQLException {
		shsController.start();	
	}
}
