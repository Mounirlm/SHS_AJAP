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

public class SensorTypeManager {
	private static Connection conn;

	public SensorTypeManager(Connection con) {
		this.conn=con;
	}

	public boolean update(Type_Sensor typeSensor) throws SQLException {
		Statement Stmt = conn.createStatement();

		String req = "update Type_Sensor set name='"+typeSensor.getName()+"', trigger_point_min='"+typeSensor.getTrigger_point_min()+"', "
				+ "trigger_point_max='"+typeSensor.getTrigger_point_max()+"',"
				+ "nb_alerts='"+typeSensor.getNb_alerts()+"' where id="+typeSensor.getId()+";";
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
	
}