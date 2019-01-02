package com.blueone.app;

import com.blueone.controller.DBconnectController;
import com.blueone.model.DBConnectModel;
import com.blueone.view.SHSView;

public class Application {
	DBconnectController shsController;
	
	public Application() {
		shsController = new DBconnectController( new DBConnectModel(),new SHSView());
		
	}
	
	public void start() {
		shsController.start();	
	}
}
