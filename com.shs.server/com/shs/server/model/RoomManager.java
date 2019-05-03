package com.shs.server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.shs.commons.model.Room;
import com.shs.commons.model.Type_Room;
import com.shs.commons.model.Wing_Room;
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
		ResultSet rstype_room=null;
		ResultSet rswing_room=null;
		try {
        RS = Stmt.executeQuery("SELECT * FROM room WHERE "+req);
        while(RS.next()) {
        	rstype_room=Stmt.executeQuery("SELECT * FROM type_room WHERE id="+RS.getInt("fk_type_room"));
        	rswing_room=Stmt.executeQuery("SELECT * FROM wing_room WHERE id="+RS.getInt("fk_wing_room"));

        	roomList.add(new Room(RS.getInt("id"),RS.getInt("floor"), RS.getInt("room_number"), RS.getInt("m2"),
        			new Type_Room(rstype_room.getInt("id"), rstype_room.getString("name")),
        			new Wing_Room(rswing_room.getInt("id"), rswing_room.getString("name"))));
        	
        }
        }finally {
        // Closing
	    RS.close();
	    rstype_room.close();
	    rswing_room.close();
	    Stmt.close();
	    DataSource.releaseConnection(conn);
		}
	    return roomList;
	}

	public static ArrayList<Room> getRooms() throws SQLException{
		Statement Stmt = conn.createStatement();
        ArrayList<Room> roomList = new ArrayList<Room>();
        ResultSet RS=null;
        ResultSet rstype_room=null;
		ResultSet rswing_room=null;
        try {
        RS = Stmt.executeQuery("SELECT * FROM room");
        
        while(RS.next()) {
        	rstype_room=Stmt.executeQuery("SELECT * FROM type_room WHERE id="+RS.getInt("fk_type_room"));
        	rswing_room=Stmt.executeQuery("SELECT * FROM wing_room WHERE id="+RS.getInt("fk_wing_room"));

        	roomList.add(new Room(RS.getInt("id"),RS.getInt("floor"), RS.getInt("room_number"), RS.getInt("m2"),
        			new Type_Room(rstype_room.getInt("id"), rstype_room.getString("name")),
        			new Wing_Room(rswing_room.getInt("id"), rswing_room.getString("name"))));
        }	
        }
        finally {
        // Closing
	    RS.close();
	    rstype_room.close();
	    rswing_room.close();
	    Stmt.close();
	    DataSource.releaseConnection(conn);
        }
	    return roomList;
	}
	
	public static Room getRoom(int id) throws SQLException{
		Statement Stmt = conn.createStatement();
		Room room = null;
		ResultSet RS=null;
		ResultSet rstype_room=null;
		ResultSet rswing_room=null;
		try {
        RS = Stmt.executeQuery("SELECT * FROM room WHERE id="+id);
        if(RS.next()) {
        room=new Room(RS.getInt("id"),RS.getInt("floor"), RS.getInt("room_number"), RS.getInt("m2"),
    			new Type_Room(rstype_room.getInt("id"), rstype_room.getString("name")),
    			new Wing_Room(rswing_room.getInt("id"), rswing_room.getString("name")));
        }
		}finally {
	        // Closing
		    RS.close();
		    rstype_room.close();
		    rswing_room.close();
		    Stmt.close();
		    DataSource.releaseConnection(conn);
		}
        return room;
	}
	
	public static boolean create(Room room) throws SQLException{
		PreparedStatement pStmt = conn.prepareStatement("insert into room (floor, room_number, m2, fk_type_room, fk_wing_room) values (?,?,?,?,?)");
		pStmt.setInt(1, room.getFloor());
		pStmt.setInt(2, room.getRoom_number());
		pStmt.setInt(3, room.getType_room().getId());
		pStmt.setInt(3, room.getWing_room().getId());
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
		
		if(room.getFloor()!=null) {
			reqDB+="floor = '"+room.getFloor()+"'";
			if(room.getRoom_number()!=null)
				reqDB+=", room_number = '"+room.getRoom_number()+"'";
			if(room.getM2()!=null)
				reqDB+=", m2 = '"+room.getM2()+"'";
			if(room.getType_room()!=null)
				reqDB+=", fk_type_room = '"+room.getType_room().getId()+"'";
			if(room.getWing_room()!=null)
				reqDB+=", fk_wing_room = '"+room.getWing_room().getId()+"'";
		}
		else if(room.getRoom_number()!=null) {
			reqDB+=", room_number = '"+room.getRoom_number()+"'";
			if(room.getM2()!=null)
				reqDB+=", m2 = '"+room.getM2()+"'";
			if(room.getType_room()!=null)
				reqDB+=", fk_type_room = '"+room.getType_room().getId()+"'";
			if(room.getWing_room()!=null)
				reqDB+=", fk_wing_room = '"+room.getWing_room().getId()+"'";
		}
		else if(room.getM2()!=null) {
			reqDB+=", m2 = '"+room.getM2()+"'";
			if(room.getType_room()!=null)
				reqDB+=", fk_type_room = '"+room.getType_room().getId()+"'";
			if(room.getWing_room()!=null)
				reqDB+=", fk_wing_room = '"+room.getWing_room().getId()+"'";
		}
		else if(room.getType_room()!=null) {
				reqDB+=", fk_type_room = '"+room.getType_room().getId()+"'";
			if(room.getWing_room()!=null)
				reqDB+=", fk_wing_room = '"+room.getWing_room().getId()+"'";
		}
		else if(room.getWing_room()!=null) {
			reqDB+=", fk_wing_room = '"+room.getWing_room().getId()+"'";
		}
		reqDB+=" WHERE id="+room.getId()+";";
		
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
	//TODO ADD CASCADE CONSTRAINTS ON DELETE IN SCRIPT
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