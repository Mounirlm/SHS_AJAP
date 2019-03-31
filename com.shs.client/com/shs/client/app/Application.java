package com.shs.client.app;

import java.io.IOException;
import java.sql.SQLException;

import com.shs.client.controller.MenuController;
import com.shs.client.controller.RoomController;
import com.shs.client.view.SHSView;

public class Application {
	RoomController roomController;
	MenuController menuController;
	
	public Application() throws SQLException, ClassNotFoundException, IOException {
		SHSView appWindow = new SHSView();
		roomController = new RoomController(appWindow);
		menuController = new MenuController(appWindow);
	}
	
	public void start() throws SQLException {
		//shsController.start();	
	}
}
