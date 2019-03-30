package com.shs.client.app;

import java.io.IOException;
import java.sql.SQLException;

import com.shs.client.controller.RoomController;
import com.shs.client.view.SHSView;

public class Application {
	RoomController shsController;
	
	public Application() throws SQLException, ClassNotFoundException, IOException {
		shsController = new RoomController(new SHSView());
		
	}
	
	public void start() throws SQLException {
		//shsController.start();	
	}
}
