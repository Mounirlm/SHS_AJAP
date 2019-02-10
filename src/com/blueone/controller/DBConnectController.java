package com.blueone.controller;



import java.sql.SQLException;
import com.blueone.model.UserModel;
import com.blueone.view.SHSView;
import connectionPool.DataSource;

// Fais le lien entre la vue(print) et le model(getteur)

public class DBConnectController {
	 private UserModel poolModel;
	 private SHSView shsView;
	 
	 public DBConnectController(SHSView v) throws SQLException, ClassNotFoundException {
		 poolModel = new UserModel();
		 shsView = v;
	 
	 }
	 
	 public void start() throws SQLException  {
		 shsView.printScreen("Size of the pool: "+DataSource.getSize());	
		 shsView.printScreen(poolModel.getUsers());
		 DataSource.shutdown();	
		 shsView.printScreen("Size of the pool after shutdown: "+DataSource.getSize());
		
	 }
 
}