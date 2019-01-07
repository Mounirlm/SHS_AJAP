package com.blueone.controller;



import java.sql.SQLException;

import com.blueone.exceptions.DBException;
import com.blueone.model.UserModel;
import com.blueone.view.SHSView;

import connectionPool.DataSource;
 
public class DBConnectController {
	 private UserModel poolModel;
	 private SHSView shsView;
	 
	 public DBConnectController(SHSView v) throws DBException {
	  poolModel = new UserModel();
	  shsView = v;
	  DataSource.initPool();
	 }
	 
	 public void start() throws SQLException  {
		 shsView.printScreen("Size of the pool: "+DataSource.getSize());
		 
		 shsView.printScreen("First connection: "+String.valueOf(DataSource.getConnection()));
		 DataSource.shutdown();
		 shsView.printScreen("Size of the pool after shutdown: "+DataSource.getSize());
		 shsView.printScreen(poolModel.getUsers());
	 }
 
}