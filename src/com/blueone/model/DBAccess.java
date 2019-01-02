package com.blueone.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
//import com.blueone.ressources/db.properties;

public class DBAccess {
	//singleton
	private static DBAccess dbAccess = new DBAccess();
	private String dbType;
	private String url;
	private String user;
	private String password;
    
	//private Constructor
	private DBAccess(){
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("db.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			this.dbType = prop.getProperty("dbType");
			this.url = prop.getProperty("url");
			this.user = prop.getProperty("user");
			this.password = prop.getProperty("password");
			System.out.println("###"+dbAccess.toString());
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	   
	//getters
	public static DBAccess getInstance(){
	if(dbAccess == null)
		dbAccess = new DBAccess();  
	    return dbAccess;
	}
	
	public String getDbType() {
		return dbType;
	}
	
	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "DBAccess [dbType=" + dbType + ", url=" + url + ", user=" + user + ", password=" + password + "]";
	}
	   
}

