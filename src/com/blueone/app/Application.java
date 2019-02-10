package com.blueone.app;

import java.sql.SQLException;
import com.blueone.controller.DBConnectController;
import com.blueone.view.SHSView;

public class Application {
	DBConnectController shsController;
	
	public Application() throws SQLException, ClassNotFoundException {
		shsController = new DBConnectController(new SHSView());
		
	}
	
	public void start() throws SQLException {
		shsController.start();	
	}
}
