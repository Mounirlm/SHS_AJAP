package com.blueone.controller;

import java.sql.SQLException;

import com.blueone.model.JDBCConnectionPoolModel;
import com.blueone.view.SHSView;
 
public class DBconnectController {
	 private JDBCConnectionPoolModel poolModel;
	 private SHSView shsView;
	 
	 public DBconnectController(JDBCConnectionPoolModel m, SHSView v) {
	  poolModel = m;
	  shsView = v;
	 
	 }
	 
	 public void start() throws SQLException {
		 shsView.printScreen(" "+poolModel.getSize());
		 shsView.printScreen(String.valueOf(poolModel.getConnection()));
		 poolModel.shutdown();
	 }
 
}