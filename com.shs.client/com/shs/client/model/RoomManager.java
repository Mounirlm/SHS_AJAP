package com.shs.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.shs.server.connection.pool.DataSource;

public class RoomManager {

	public static ArrayList<Room> getRooms() throws SQLException{
		Connection conn = DataSource.getConnection();
		Statement Stmt = conn.createStatement();
        ResultSet RS = Stmt.executeQuery("SELECT * FROM room");
        ArrayList<Room> roomList = new ArrayList<Room>();
        while(RS.next())
        	roomList.add(new Room(RS.getInt("id"),RS.getString("type_room"),RS.getInt("floor")));
        // Closing
	    RS.close();
	    Stmt.close();
	    DataSource.releaseConnection(conn);
	    return roomList;
	}
	
	public static Room getRoom(int id) throws SQLException{
		Connection conn = DataSource.getConnection();
		Statement Stmt = conn.createStatement();
        ResultSet RS = Stmt.executeQuery("SELECT * FROM room WHERE id="+id);
        RS.next();
        Room room = new Room(RS.getInt("id"),RS.getString("type_room"),RS.getInt("floor"));
        // Closing
	    RS.close();
	    Stmt.close();
	    DataSource.releaseConnection(conn);
        return room;
	}
	
	public static boolean create(Room room) throws SQLException{
		Connection conn = DataSource.getConnection();
		PreparedStatement pStmt = conn.prepareStatement("INSERT INTO room ('type_room','floor') VALUES (?,?)");
		pStmt.setString(1, room.getType_room());
		pStmt.setInt(2, room.getFloor());
		int n = pStmt.executeUpdate();
		// Closing
		pStmt.close();
        DataSource.releaseConnection(conn);
		return n==1;
	}
	
	public static boolean update(Room room) throws SQLException {
		Connection conn = DataSource.getConnection();
		PreparedStatement pStmt = conn.prepareStatement("UPDATE FROM room SET type_room=?, floor=? WHERE id=?");
		pStmt.setString(1, room.getType_room());
		pStmt.setInt(2, room.getFloor());
		pStmt.setInt(3, room.getId());
		int n = pStmt.executeUpdate();
		// Closing
		pStmt.close();
        DataSource.releaseConnection(conn);
		return n==1;
	}
	
	public static boolean delete(Room room) throws SQLException{
		Connection conn = DataSource.getConnection();
		Statement Stmt = conn.createStatement();
		int n = Stmt.executeUpdate("DELETE FROM room WHERE id=" + room.getId());
		//Closing
        Stmt.close();
        DataSource.releaseConnection(conn);
        return n==1;
	}
}