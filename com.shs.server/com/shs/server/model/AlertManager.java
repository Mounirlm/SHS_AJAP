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
import com.shs.commons.model.Alert;
import com.shs.server.connection.pool.DataSource;

public class AlertManager {
	private static Connection conn;

	public AlertManager(Connection con) {
		this.conn=con;
	}


	public static ArrayList<Alert> getAlerts() throws SQLException, ParseException{
		Statement Stmt = conn.createStatement();

		ArrayList<Alert> alertList = new ArrayList<Alert>();
		ResultSet RS=null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			RS = Stmt.executeQuery("SELECT * FROM alert");

			while(RS.next()) {
				alertList.add(new Alert(RS.getInt("id"),dateFormat.parse(RS.getString("date_alert")), 
						Time.valueOf(RS.getString("hour_alert")),RS.getString("description"),
						RS.getInt("fk_users"),RS.getInt("fk_sensor")));
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
		return alertList;
	}
	
	
	public static Alert getAlert(int id) throws SQLException, ParseException{
		Statement Stmt = conn.createStatement();
		Alert alert=null;
		ResultSet RS=null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			RS = Stmt.executeQuery("SELECT * FROM Alert where id="+id);

			while(RS.next()) {

				alert = new Alert(RS.getInt("id"),dateFormat.parse(RS.getString("date_alert")), 
						Time.valueOf(RS.getString("hour_alert")),RS.getString("description"),
						RS.getInt("fk_users"),RS.getInt("fk_sensor"));

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
		return alert;
	}
	
	public static boolean create(Alert alert) throws SQLException{
		Statement Stmt = conn.createStatement();
		
		String req = "insert into alert (date_alert, hour_alert, description, fk_sensor, fk_users)"
				+ " values ('"+alert.getDate_alert_formatted()+"','"+alert.getHour_alert()+"',"
						+ "'"+alert.getDescription()+"',"+alert.getFk_sensor()+","+alert.getFk_user()+");";
		int n=0;
		try {
			n = Stmt.executeUpdate(req);}
		finally {
		// Closing
		if(Stmt!=null)
		    try{Stmt.close();}catch(Exception e){e.printStackTrace();}  
        DataSource.releaseConnection(conn);
		}
		return n==1;
	}
	
	public static boolean update(Alert alert) throws SQLException {
		Statement Stmt = conn.createStatement();
		
		String req= "update Alert set date_alert='"+alert.getDate_alert_formatted()+"', hour_alert='"+alert.getHour_alert()+"', "
				+ "description='"+alert.getDescription()+"',"
				+ " fk_sensor="+alert.getFk_sensor()+", fk_users="+alert.getFk_user()+", where id="+alert.getId()+"";
		
		int n=0;
		try {
			n = Stmt.executeUpdate(req);}
		finally {
		// Closing
		if(Stmt!=null)
		    try{Stmt.close();}catch(Exception e){e.printStackTrace();}  
        DataSource.releaseConnection(conn);
		}
		return n==1;
	}
	
	//TODO ADD CASCADE CONSTRAINTS ON DELETE IN SCRIPT
		public static boolean delete(Alert alert) throws SQLException{
			Statement Stmt = conn.createStatement();
			int n=0;
			try{
			n = Stmt.executeUpdate("DELETE FROM alert WHERE id=" + alert.getId());}
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
			n = Stmt.executeUpdate("DELETE FROM alert");System.out.println(n);
			}finally {
				//Closing
				DataSource.releaseConnection(conn);
				if(Stmt!=null)
		        	try{Stmt.close();}catch(Exception e){e.printStackTrace();} ;
		        
	        }
	        return n>0;
		}


		public static int countByFloorMonthYear(int floor, int month, int year) throws SQLException {
			PreparedStatement Stmt = conn.prepareStatement("SELECT COUNT(*) FROM alert ");
			
			return 0;
		}
}