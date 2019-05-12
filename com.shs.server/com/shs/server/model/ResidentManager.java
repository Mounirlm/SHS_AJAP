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

public class ResidentManager {
	private static Connection conn;

	public ResidentManager(Connection con) {
		this.conn=con;
	}
	
	public static int countByFloorMonthYear(int floor, int month, int year) throws SQLException {
		PreparedStatement pStmt = conn.prepareStatement("SELECT COUNT(*) FROM resident INNER JOIN room r ON fk_resident_room = r.id  WHERE r.floor=?;");
		pStmt.setInt(1, floor);
		ResultSet rs = pStmt.executeQuery();
		rs.next();
		return rs.getInt(1);
	}
}