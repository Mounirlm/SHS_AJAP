package com.blueone.controller;

import com.blueone.model.DBConnectModel;
import com.blueone.view.SHSView;
 
public class DBconnectController {
	 private DBConnectModel dbConnectModel;
	 private SHSView shsView;
	 
	 public DBconnectController(DBConnectModel m, SHSView v) {
	  dbConnectModel = m;
	  shsView = v;
	 
	 }
	 
	 public void start() {
		 shsView.printScreen(dbConnectModel.startConnexion());
	 }
 
}