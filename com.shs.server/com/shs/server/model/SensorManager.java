package com.shs.server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.shs.commons.model.Sensor;
import com.shs.commons.model.Type_Sensor;
import com.shs.commons.model.Wing_Room;
import com.shs.commons.model.Room;
import com.shs.server.connection.pool.DataSource;

public class SensorManager {
	private static Connection conn;
	
	public SensorManager(Connection con) {
		this.conn=con;
	}
	

	public static ArrayList<Sensor> getSensors() throws SQLException, ParseException{
		Statement Stmt = conn.createStatement();
		Statement Stmt2 = conn.createStatement();
		Statement Stmt3 = conn.createStatement();
		
        ArrayList<Sensor> sensorsList = new ArrayList<Sensor>();
        ResultSet RS=null;
        ResultSet rswing_room=null;
        ResultSet rstype_sensor=null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
        RS = Stmt.executeQuery("SELECT * FROM sensor");
        
        while(RS.next()) {
        	rswing_room=Stmt2.executeQuery("SELECT * FROM wing_room WHERE id="+RS.getInt("fk_wing_room"));
        	rstype_sensor=Stmt3.executeQuery("SELECT * FROM type_sensor WHERE id="+RS.getInt("fk_type_sensor"));
        	
        	
        	if ( rswing_room.next()  && rstype_sensor.next()) {
        		Room room = new Room();
            	room.setId(RS.getInt("fk_room"));
        	sensorsList.add(new Sensor(RS.getInt("id"),RS.getString("sensor_name"), RS.getString("ip_address"), RS.getString("mac_address"),
        			dateFormat.parse(RS.getString("date_setup")), RS.getBoolean("status"), RS.getBoolean("installed"),
        			new Wing_Room(rswing_room.getInt("id"), rswing_room.getString("name")),
        			RS.getFloat("price"),
        			room,
        			new Type_Sensor(rstype_sensor.getInt("id"), rstype_sensor.getString("name")),
        			RS.getInt("scope_sensor")));
        			
        	}
        }	
        }
        finally {
        // Closing
        DataSource.releaseConnection(conn);
        if(RS!=null)
	        try{RS.close();}catch(Exception e){e.printStackTrace();} 
		if(rstype_sensor!=null)
        	try{rstype_sensor.close();}catch(Exception e){e.printStackTrace();} 
        if(rswing_room!=null)
        	try{rswing_room.close();}catch(Exception e){e.printStackTrace();}  
        if(Stmt!=null)
        	try{Stmt.close();}catch(Exception e){e.printStackTrace();} 
        if(Stmt2!=null)
        	try{Stmt2.close();}catch(Exception e){e.printStackTrace();} 
        if(Stmt3!=null)
        	try{Stmt3.close();}catch(Exception e){e.printStackTrace();}  
        }
	    return sensorsList;
	}
	
	public static Sensor getSensor(int id) throws SQLException, ParseException{
		Statement Stmt = conn.createStatement();
		Statement Stmt2 = conn.createStatement();
		Statement Stmt3 = conn.createStatement();
		
        Sensor sensor = null;
        ResultSet RS=null;
        ResultSet rswing_room=null;
        ResultSet rstype_sensor=null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
        RS = Stmt.executeQuery("SELECT * FROM sensor WHERE id="+id);
        
        while(RS.next()) {
        	rswing_room=Stmt2.executeQuery("SELECT * FROM wing_room WHERE id="+RS.getInt("fk_wing_room"));
        	rstype_sensor=Stmt3.executeQuery("SELECT * FROM type_sensor WHERE id="+RS.getInt("fk_type_sensor"));
        	
        	
        	if ( rswing_room.next()  && rstype_sensor.next()) {
        		Room room = new Room();
            	room.setId(RS.getInt("fk_room"));
            	sensor = new Sensor(RS.getInt("id"),RS.getString("sensor_name"), RS.getString("ip_address"), RS.getString("mac_address"),
        			dateFormat.parse(RS.getString("date_setup")), RS.getBoolean("status"), RS.getBoolean("installed"),
        			new Wing_Room(rswing_room.getInt("id"), rswing_room.getString("name")),
        			RS.getFloat("price"),
        			room,
        			new Type_Sensor(rstype_sensor.getInt("id"), rstype_sensor.getString("name")),
        			RS.getInt("scope_sensor"));
        			
        	}
        }	
        }
        finally {
        // Closing
        DataSource.releaseConnection(conn);
        if(RS!=null)
	        try{RS.close();}catch(Exception e){e.printStackTrace();} 
		if(rstype_sensor!=null)
        	try{rstype_sensor.close();}catch(Exception e){e.printStackTrace();} 
        if(rswing_room!=null)
        	try{rswing_room.close();}catch(Exception e){e.printStackTrace();}  
        if(Stmt!=null)
        	try{Stmt.close();}catch(Exception e){e.printStackTrace();} 
        if(Stmt2!=null)
        	try{Stmt2.close();}catch(Exception e){e.printStackTrace();} 
        if(Stmt3!=null)
        	try{Stmt3.close();}catch(Exception e){e.printStackTrace();}  
        }
	    return sensor;
	}
	
	public static boolean create(Sensor sensor) throws SQLException{
		PreparedStatement pStmt = conn.prepareStatement("insert into sensor (sensor_name, ip_address, mac_address,"
				+ " date_setup, status, installed, fk_position, price, fk_room, fk_type_sensor, scope_sensor)"
				+ " values (?,?,?,?,?,?,?,?,?,?,?);");
		pStmt.setString(1, sensor.getSensor_name());
		pStmt.setString(2, sensor.getIp_address());
		pStmt.setString(3, sensor.getMac_address());
		pStmt.setDate(4, (java.sql.Date) sensor.getDate_setup());
		pStmt.setBoolean(5, sensor.getStatus());
		pStmt.setBoolean(6, sensor.getInstalled());
		pStmt.setInt(7, sensor.getFk_position().getId());
		pStmt.setFloat(8, sensor.getPrice());
		pStmt.setInt(9, sensor.getFk_room().getId());
		pStmt.setInt(10, sensor.getFk_type_sensor().getId());
		pStmt.setInt(11, sensor.getScope_sensor());
		
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

	
	public static boolean update(Sensor sensor) throws SQLException {
		PreparedStatement pStmt = conn.prepareStatement("update sensor set sensor_name='?', ip_address='?', mac_address='?',"
				+ " date_setup='?', status=?, installed=?, fk_position=?, price=?, fk_room=?, fk_type_sensor=?, "
				+ "scope_sensor=? where id=?;");
		pStmt.setString(1, sensor.getSensor_name());
		pStmt.setString(2, sensor.getIp_address());
		pStmt.setString(3, sensor.getMac_address());
		pStmt.setDate(4, (java.sql.Date) sensor.getDate_setup());
		pStmt.setBoolean(5, sensor.getStatus());
		pStmt.setBoolean(6, sensor.getInstalled());
		pStmt.setInt(7, sensor.getFk_position().getId());
		pStmt.setFloat(8, sensor.getPrice());
		pStmt.setInt(9, sensor.getFk_room().getId());
		pStmt.setInt(10, sensor.getFk_type_sensor().getId());
		pStmt.setInt(11, sensor.getScope_sensor());
		pStmt.setInt(12, sensor.getId());
		
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
	public static boolean delete(Sensor sensor) throws SQLException{
		Statement Stmt = conn.createStatement();
		int n=0;
		try{
		n = Stmt.executeUpdate("DELETE FROM sensor WHERE id=" + sensor.getId());}
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
		n = Stmt.executeUpdate("DELETE FROM sensor");System.out.println(n);
		}finally {
			//Closing
			DataSource.releaseConnection(conn);
			if(Stmt!=null)
	        	try{Stmt.close();}catch(Exception e){e.printStackTrace();} ;
	        
        }
        return n>0;
	}
}