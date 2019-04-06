package com.shs.server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.shs.commons.model.Room;
import com.shs.server.connection.pool.DataSource;

public class RoomManager {
	private static Connection conn;
	public RoomManager(Connection con) {
		this.conn=con;
	}

	public static ArrayList<Room> getRooms() throws SQLException{
		Statement Stmt = conn.createStatement();
        ResultSet RS = Stmt.executeQuery("SELECT * FROM room");
        ArrayList<Room> roomList = new ArrayList<Room>();
        while(RS.next())
        	roomList.add(new Room(RS.getInt("id"),RS.getString("type_room"),RS.getInt("floor"), RS.getInt("room_number")));
        // Closing
	    RS.close();
	    Stmt.close();
	    DataSource.releaseConnection(conn);
	    return roomList;
	}
	
	public static Room getRoom(int id) throws SQLException{
		Statement Stmt = conn.createStatement();
        ResultSet RS = Stmt.executeQuery("SELECT * FROM room WHERE id="+id);
        RS.next();
        Room room = new Room(RS.getInt("id"),RS.getString("type_room"),RS.getInt("floor"),RS.getInt("room_number"));
        // Closing
	    RS.close();
	    Stmt.close();
	    DataSource.releaseConnection(conn);
        return room;
	}
	
	public static boolean create(Room room) throws SQLException{
		PreparedStatement pStmt = conn.prepareStatement("INSERT INTO room (type_room,floor, room_number) VALUES (?,?,?)");
		pStmt.setString(1, room.getType_room());
		pStmt.setInt(2, room.getFloor());
		pStmt.setInt(3, room.getRoom_number());
		int n = pStmt.executeUpdate();
		// Closing
		pStmt.close();
        DataSource.releaseConnection(conn);
		return n==1;
	}

	
	public static boolean update(Room room) throws SQLException {
		PreparedStatement pStmt = conn.prepareStatement("UPDATE room SET type_room=?, floor=?, room_number=? WHERE id=?");
		pStmt.setString(1, room.getType_room());
		pStmt.setInt(2, room.getFloor());
		pStmt.setInt(3, room.getRoom_number());
		pStmt.setInt(4, room.getId());
		int n = pStmt.executeUpdate();
		// Closing
		pStmt.close();
        DataSource.releaseConnection(conn);
		return n==1;
	}
	
	public static boolean delete(Room room) throws SQLException{
		Statement Stmt = conn.createStatement();
		int n = Stmt.executeUpdate("DELETE FROM room WHERE id=" + room.getId());
		//Closing
        Stmt.close();
        DataSource.releaseConnection(conn);
        return n==1;
	}

	public static boolean deleteAll() throws SQLException{
		Statement Stmt = conn.createStatement();
		int n = Stmt.executeUpdate("DELETE FROM room");System.out.println(n);
		//Closing
        Stmt.close();
        DataSource.releaseConnection(conn);
        return n>0;
	}
}