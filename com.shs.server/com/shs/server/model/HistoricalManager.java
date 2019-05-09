package com.shs.server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.shs.commons.model.Historical;
import com.shs.server.connection.pool.DataSource;

public class HistoricalManager {

	private static Connection conn;

	public HistoricalManager(Connection con) {
		this.conn=con;
	}


	public static ArrayList<Historical> getHistoricals() throws SQLException, ParseException{
		Statement Stmt = conn.createStatement();

		ArrayList<Historical>historicalList = new ArrayList<Historical>();
		ResultSet RS=null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			RS = Stmt.executeQuery("SELECT * FROM Historical");

			while(RS.next()) {
				historicalList.add(new Historical(RS.getInt("id"),dateFormat.parse(RS.getString("date_signal")), 
						Time.valueOf(RS.getString("hour_signal")),RS.getString("msessage"),
						RS.getInt("fk_sensor")));


			}	
		}
		finally {
			// Closing
			DataSource.releaseConnection(conn);
			if(RS!=null)
				try{RS.close();}catch(Exception e){e.printStackTrace();} 
			if(Stmt!=null)
				try{Stmt.close();}catch(Exception e){e.printStackTrace();} 
			 
		}
		return historicalList;
	}
	
	
	public static Historical getHistorical(int id) throws SQLException, ParseException{
		Statement Stmt = conn.createStatement();
		Historical Historical=null;
		ResultSet RS=null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			RS = Stmt.executeQuery("SELECT * FROM Historical where id="+id);

			while(RS.next()) {

				Historical = new Historical(RS.getInt("id"),dateFormat.parse(RS.getString("date_signal")), 
						Time.valueOf(RS.getString("hour_signal")),RS.getString("msessage"),
						RS.getInt("fk_sensor"));

			}	
		}
		finally {
			// Closing
			DataSource.releaseConnection(conn);
			if(RS!=null)
				try{RS.close();}catch(Exception e){e.printStackTrace();} 
			if(Stmt!=null)
				try{Stmt.close();}catch(Exception e){e.printStackTrace();} 
			 
		}
		return Historical;
	}
	
	public static boolean create(Historical historical) throws SQLException{
		PreparedStatement pStmt = conn.prepareStatement("insert into Historical (date_signal, hour_signale, message, fk_sensor)"
				+ " values (?,?,?,?);");
		pStmt.setDate(1, (java.sql.Date) historical.getDate_signal());
		pStmt.setTime(2, (java.sql.Time) historical.getHour_signal());
		pStmt.setString(3, historical.getMessage());
		pStmt.setInt(4, historical.getFk_sensor());
		
		
		int n=0;
		try {
			n = pStmt.executeUpdate();}
		finally {
		// Closing
		if(pStmt!=null)
		    try{pStmt.close();}catch(Exception e){e.printStackTrace();}  
        DataSource.releaseConnection(conn);
		}
		return n==1;
	}
	
	public static boolean update(Historical historical) throws SQLException {
		PreparedStatement pStmt = conn.prepareStatement("update Historical set date_signal='?', hour_signal='?', message='?',"
				+ " fk_sensor='?', where id=?");
		pStmt.setDate(1, (java.sql.Date) historical.getDate_signal());
		pStmt.setTime(2, (java.sql.Time) historical.getHour_signal());
		pStmt.setString(3, historical.getMessage());
		pStmt.setInt(4, historical.getFk_sensor());
		pStmt.setInt(5, historical.getId());
		
		
		int n=0;
		try {
			n = pStmt.executeUpdate();}
		finally {
		// Closing
		if(pStmt!=null)
		    try{pStmt.close();}catch(Exception e){e.printStackTrace();}  
        DataSource.releaseConnection(conn);
		}
		return n==1;
	}
	
	//TODO ADD CASCADE CONSTRAINTS ON DELETE IN SCRIPT
		public static boolean delete(Historical historical) throws SQLException{
			Statement Stmt = conn.createStatement();
			int n=0;
			try{
			n = Stmt.executeUpdate("DELETE FROM Historical WHERE id=" + historical.getId());}
			finally {
			//Closing
			DataSource.releaseConnection(conn);
			if(Stmt!=null)
	        	try{Stmt.close();}catch(Exception e){e.printStackTrace();} 
			}
	        return n==1;
		}

		public static boolean deleteAll() throws SQLException{
			Statement Stmt = conn.createStatement();
			int n=0;
			try{
			n = Stmt.executeUpdate("DELETE FROM Historical");System.out.println(n);
			}finally {
				//Closing
				DataSource.releaseConnection(conn);
				if(Stmt!=null)
		        	try{Stmt.close();}catch(Exception e){e.printStackTrace();} ;
		        
	        }
	        return n>0;
		}
}