package com.blueone.app;

import java.sql.SQLException;

import com.blueone.controller.DBConnectController;
import com.blueone.exceptions.DBException;
import com.blueone.view.SHSView;

public class Application {
	DBConnectController shsController;
	
	public Application() throws DBException {
		shsController = new DBConnectController(new SHSView());
		
	}
	
	public void start() throws SQLException {
		shsController.start();	
	}
}
