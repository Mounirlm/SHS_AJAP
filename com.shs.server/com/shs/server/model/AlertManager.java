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

import com.shs.commons.model.Alert;
import com.shs.commons.model.Sensor;
import com.shs.server.connection.pool.DataSource;

public class AlertManager {
	private static Connection conn;
	
	public AlertManager(Connection con) {
		this.conn=con;
	}
	

	
}