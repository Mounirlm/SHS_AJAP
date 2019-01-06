package com.blueone.controller;



import com.blueone.exceptions.DBException;
import com.blueone.view.SHSView;

import connectionPool.DataSource;
 
public class DBConnectController {
	 private DataSource poolModel;
	 private SHSView shsView;
	 
	 public DBConnectController(SHSView v) throws DBException {
	  poolModel = new DataSource();
	  shsView = v;
	 
	 }
	 
	 public void start() throws DBException  {
		 shsView.printScreen("Size of the pool: "+DataSource.getSize());
		 
		 shsView.printScreen("First connection: "+String.valueOf(DataSource.getConnection()));
		 DataSource.shutdown();
		 shsView.printScreen("Size of the pool after shutdown: "+DataSource.getSize());
	 }
 
}