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
	
	public static ArrayList<Room> getRoomsBy(String req) throws SQLException{
		Statement Stmt = conn.createStatement();
		ArrayList<Room> roomList = new ArrayList<Room>();
		ResultSet RS=null;
		try {
        RS = Stmt.executeQuery("SELECT * FROM room WHERE "+req);
        while(RS.next())
        	roomList.add(new Room(RS.getInt("id"),RS.getString("type_room"),RS.getInt("floor"), RS.getInt("room_number")));
		}finally {
        // Closing
	    RS.close();
	    Stmt.close();
	    DataSource.releaseConnection(conn);
		}
	    return roomList;
	}

	public static ArrayList<Room> getRooms() throws SQLException{
		Statement Stmt = conn.createStatement();
        ArrayList<Room> roomList = new ArrayList<Room>();
        ResultSet RS=null;
        try {
        RS = Stmt.executeQuery("SELECT * FROM room");
        while(RS.next())
        	roomList.add(new Room(RS.getInt("id"),RS.getString("type_room"),RS.getInt("floor"), RS.getInt("room_number")));
        }finally {
        // Closing
	    RS.close();
	    Stmt.close();
	    DataSource.releaseConnection(conn);
        }
	    return roomList;
	}
	
	public static Room getRoom(int id) throws SQLException{
		Statement Stmt = conn.createStatement();
		Room room = null;
		ResultSet RS=null;
		try {
        RS = Stmt.executeQuery("SELECT * FROM room WHERE id="+id);
        if(RS.next()) {
        room=new Room(RS.getInt("id"),RS.getString("type_room"),RS.getInt("floor"),RS.getInt("room_number"));
        }
		}finally {
	        // Closing
		    RS.close();
		    Stmt.close();
		    DataSource.releaseConnection(conn);
		}
        return room;
	}
	
	public static boolean create(Room room) throws SQLException{
		PreparedStatement pStmt = conn.prepareStatement("INSERT INTO room (type_room,floor, room_number) VALUES (?,?,?)");
		pStmt.setString(1, room.getType_room());
		pStmt.setInt(2, room.getFloor());
		pStmt.setInt(3, room.getRoom_number());
		int n=0;
		try {
			n = pStmt.executeUpdate();}
		finally {
		// Closing
		pStmt.close();
        DataSource.releaseConnection(conn);
		}
		return n==1;
	}

	
	public static boolean update(Room room) throws SQLException {
		Statement Stmt = conn.createStatement();
		String reqDB="update room set ";
		
		if(room.getType_room()!=null) {
			reqDB+="type_room = '"+room.getType_room()+"'";
			if(room.getFloor()!=null)
				reqDB+=", floor = '"+room.getFloor()+"'";
			if(room.getRoom_number()!=null)
				reqDB+=", room_number = '"+room.getRoom_number()+"'";
		}
		else if(room.getFloor()!=null) {
			reqDB+="floor = '"+room.getFloor()+"'";
			if(room.getRoom_number()!=null)
				reqDB+=", room_number = '"+room.getRoom_number()+"'";
		}
		else if(room.getRoom_number()!=null) {
				reqDB+="room_number = '"+room.getRoom_number()+"'";
		}
		reqDB+=" WHERE id="+room.getId()+";"; ;
		
		int n=0;
		try{
			n = Stmt.executeUpdate(reqDB);
		}finally {
		// Closing
		Stmt.close();
        DataSource.releaseConnection(conn);
		}
		return n==1;
	}
	
	public static boolean delete(Room room) throws SQLException{
		Statement Stmt = conn.createStatement();
		int n=0;
		try{
		n = Stmt.executeUpdate("DELETE FROM room WHERE id=" + room.getId());}
		finally {
		//Closing
        Stmt.close();
        DataSource.releaseConnection(conn);
		}
        return n==1;
	}

	public static boolean deleteAll() throws SQLException{
		Statement Stmt = conn.createStatement();
		int n=0;
		try{
		n = Stmt.executeUpdate("DELETE FROM room");System.out.println(n);
		}finally {
			//Closing
	        Stmt.close();
	        DataSource.releaseConnection(conn);
        }
        return n>0;
	}
}