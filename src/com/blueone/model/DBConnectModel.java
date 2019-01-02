package com.blueone.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnectModel {
	private static DBAccess dbAccess;
	private Connection con;
	
	public DBConnectModel() {
		dbAccess.getInstance();
	}
	
	public String startConnexion() {
		String message="";
		try {
		      Class.forName(dbAccess.getDbType());
		      message="Driver ok";
		      con = DriverManager.getConnection(dbAccess.getUrl(), dbAccess.getUser(), dbAccess.getPassword());
		      message+="\n Connection ok";
		    } catch (Exception e) {
		      message="Error";
		      e.printStackTrace();
		    }
		return message;
	}
	
}