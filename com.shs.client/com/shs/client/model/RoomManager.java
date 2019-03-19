package com.shs.client.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.shs.server.connection.pool.DataSource;

public class RoomManager {
	private DataSource data;
	
	public RoomManager() throws SQLException, ClassNotFoundException {
		data = new DataSource();
	}
	
	public static ArrayList<User> getUsers() throws SQLException {	
		Connection conn = DataSource.getConnection();
		Statement Stmt = conn.createStatement();
        ResultSet RS = Stmt.executeQuery("SELECT * FROM users");      
        ArrayList<User> userList = new ArrayList<User>();
        while(RS.next()){         
        	userList.add(new User(RS.getInt("id"), RS.getString("first_name"), RS.getString("last_name"), RS.getString("email"), RS.getString("password"), RS.getString("function")));           
        
		
	    // Closing
	    RS.close();
	    Stmt.close();
	    DataSource.releaseConnection(conn);
		}
		return userList;
	
	}
	
	public static User getUser(int id) throws SQLException{
		Connection conn = DataSource.getConnection();
		Statement Stmt = conn.createStatement();
        ResultSet RS = Stmt.executeQuery("SELECT * FROM users WHERE id="+id);
        RS.next();
        User user = new User(RS.getInt("id"), RS.getString("first_name"), RS.getString("last_name"), RS.getString("email"), RS.getString("password"), RS.getString("function"));
        // closing
        RS.close();
        Stmt.close();
        DataSource.releaseConnection(conn);
		return user;
	}
	
	public static boolean update(User user) throws SQLException{
		Connection conn = DataSource.getConnection();
		PreparedStatement pStmt = conn.prepareStatement("UPDATE FROM users SET first_name=?, last_name=?, email=?, login=?, password=?, function=? WHERE id=?");
		pStmt.setString(1, user.getFirst_name());
		pStmt.setString(2, user.getLast_name());
		pStmt.setString(3, user.getEmail());
		pStmt.setString(5, user.getPassword());
		pStmt.setString(6, user.getFunction());
		pStmt.setInt(7, user.getId());
		int n = pStmt.executeUpdate();
		// Closing
        pStmt.close();
        DataSource.releaseConnection(conn);
        return n==1;
	}
	
	public static boolean deleteUser(User user) throws SQLException{
		Connection conn = DataSource.getConnection();
		Statement Stmt = conn.createStatement();
		int n = Stmt.executeUpdate("DELETE FROM users WHERE id=" + user.getId());
		
        Stmt.close();
        DataSource.releaseConnection(conn);
        return n==1;
	}
	
	
	
}
