package com.blueone.model;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.blueone.exceptions.DBException;

import connectionPool.DataSource;

public class UserModel {
	private DataSource poolModel;
	
	public UserModel() throws DBException {
		poolModel = new DataSource();
	}
	
	public String getUsers() throws SQLException {
		Connection conn = DataSource.getConnection();
		Statement Stmt = conn.createStatement();
        ResultSet RS = Stmt.executeQuery("SELECT * FROM utilisateur");
        String message="";
       
       
        ResultSetMetaData resultMeta = RS.getMetaData();

       
           

        while(RS.next()){         

          for(int i = 1; i <= resultMeta.getColumnCount(); i++)

        	  message+="\t" + RS.getObject(i).toString() + "\t |";

          
	}
        
        // Fermetures

        RS.close();
        Stmt.close();
        DataSource.releaseConnection(conn);
		return message;
	
	}
}
