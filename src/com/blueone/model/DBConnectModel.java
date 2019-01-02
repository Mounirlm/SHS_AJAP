package com.blueone.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnectModel {
	private Connection con;
	
	public DBConnectModel() {DBAccess.getInstance();}
	
	public String startConnection() {
		String message="";
		
		try {
		      Class.forName(DBAccess.getDB_DRIVER_CLASS());
		      message="Driver ok";
		      con = DriverManager.getConnection(DBAccess.getDB_URL(), DBAccess.getDB_USERNAME(), DBAccess.getDB_PASSWORD());
		      message+="\nConnection ok";
		    } catch (Exception e) {
		      message="Error";
		      e.printStackTrace();
		    }
		return message;
	}
	
}