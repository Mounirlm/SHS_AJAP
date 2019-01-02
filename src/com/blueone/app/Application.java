package com.blueone.app;

import com.blueone.controller.SHSController;
import com.blueone.model.SHSModel;
import com.blueone.view.SHSView;

public class Application {
	SHSController shsController;
	
	public Application() {
		shsController = new SHSController( new SHSModel(),new SHSView());
		
	}
	
	public void start() {
		shsController.start();	
	}
}
