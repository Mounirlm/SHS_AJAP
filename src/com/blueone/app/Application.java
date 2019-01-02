package com.blueone.app;

import java.sql.SQLException;

import com.blueone.controller.DBconnectController;
import com.blueone.model.JDBCConnectionPoolModel;
import com.blueone.view.SHSView;

public class Application {
	DBconnectController shsController;
	
	public Application() throws SQLException {
		shsController = new DBconnectController(new JDBCConnectionPoolModel(), new SHSView());
		
	}
	
	public void start() throws SQLException {
		shsController.start();	
	}
}
