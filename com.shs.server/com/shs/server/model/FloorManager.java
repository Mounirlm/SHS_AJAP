package com.shs.server.model;


import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.shs.commons.model.Building;
import com.shs.commons.model.Floor;
import com.shs.commons.model.Room;
import com.shs.commons.model.Type_Room;
import com.shs.commons.model.Wing_Room;


public class FloorManager {
	
	
	private static Connection conn;
	
	
	
	public FloorManager(Connection con) {
		this.conn=con;
	}
	
	
public static ArrayList<Floor> getFloor() throws SQLException{
		
		Statement Stmt = conn.createStatement();
		Statement Stmt2 = conn.createStatement();
		
		
        ArrayList<Floor> floorList = new ArrayList<Floor>();
       
        ResultSet RS=null;
        ResultSet rsbuilding=null;
        
             	
        try {
        	//
        RS = Stmt.executeQuery("SELECT * FROM floor_map");
        
        while(RS.next()) {
        	       	
        	rsbuilding=Stmt2.executeQuery ("SELECT * FROM building WHERE id="+RS.getInt("fk_building"));
        	
        	if (rsbuilding.next()){
        		
        	floorList.add(new Floor(RS.getInt("id"),RS.getString("name"),RS.getString("image_path"),
        			new Building(rsbuilding.getInt("id"),rsbuilding.getString("name"),rsbuilding.getString("type"))));
        		}
        	}
        }	
        
        finally {
        // Closing
        
        if(RS!=null)
	        try{RS.close();}catch(Exception e){e.printStackTrace();} 
        
        if(rsbuilding!=null)
        	try{rsbuilding.close();}catch(Exception e){e.printStackTrace();}
		
        if(Stmt!=null)
        	try{Stmt.close();}catch(Exception e){e.printStackTrace();} 
        if(Stmt2!=null)
        	try{Stmt2.close();}catch(Exception e){e.printStackTrace();}
        
        }
	    return floorList;
	}


public static ArrayList<Floor> getFloor2()throws SQLException {
	ArrayList<Floor> floorList = new ArrayList<Floor>();
	Statement Stmt2 = conn.createStatement();
	ResultSet rsbuilding=null;
	PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM floor INNER JOIN building b ON fk_building = b.id;");
	ResultSet rs = pStmt.executeQuery();
	while(rs.next()) {
		rsbuilding=Stmt2.executeQuery ("SELECT * FROM building WHERE id="+rsbuilding.getInt("fk_building"));
    	if (rsbuilding.next()){
		floorList.add(new Floor(rs.getInt("id"),rs.getString("name"),rs.getString("image_path"), 
    			new Building(rsbuilding.getInt("id"),rsbuilding.getString("name"), rsbuilding.getString("type")) ) );
    	}
    	}
	return floorList;
}
	
	public static ArrayList<Room> getRoomWithPostion() throws SQLException {
		
		Statement Stmt = conn.createStatement();
		Statement Stmt2 = conn.createStatement();
		Statement Stmt3 = conn.createStatement();
		Statement Stmt4 = conn.createStatement();
		Statement Stmt5 = conn.createStatement();
		ArrayList<Room> roomList = new ArrayList<Room>();
		ResultSet RS=null;
		ResultSet rstype_room=null;
		ResultSet rswing_room=null;
		ResultSet rsfloor=null;
		ResultSet rsbuilding=null;
		try {
        RS = Stmt.executeQuery("SELECT * FROM room WHERE fk_floor_map IS NOT NULL");
        
        while(RS.next()) {
        	rstype_room=Stmt2.executeQuery("SELECT * FROM type_room WHERE id="+RS.getInt("fk_type_room"));
        	rswing_room=Stmt3.executeQuery("SELECT * FROM wing_room WHERE id="+RS.getInt("fk_wing_room"));
        	rsfloor=Stmt4.executeQuery("SELECT * FROM floor_map WHERE id="+RS.getInt("fk_floor_map"));
        	rsbuilding=Stmt5.executeQuery("SELECT * FROM building WHERE id="+RS.getInt("fk_building"));
        	
        	if ( rstype_room.next() && rswing_room.next() && rsfloor.next() && rsbuilding.next()) {
		        roomList.add(new Room(RS.getInt("id"),RS.getInt("floor"), RS.getInt("room_number"), RS.getInt("m2"),
		    			new Type_Room(rstype_room.getInt("id"), rstype_room.getString("name")),
		    			new Wing_Room(rswing_room.getInt("id"), rswing_room.getString("name")),
	        			RS.getInt("nb_doors"), RS.getInt("nb_windows"),RS.getInt("x"),RS.getInt("y"),RS.getInt("width"),RS.getInt("height"),
	        			new Floor(rsfloor.getInt("id"),rsfloor.getString("name"),rsfloor.getString("image_path") ,
	        			new Building(rsbuilding.getInt("id"),rsbuilding.getString("name"),rsbuilding.getString("type")) ) ));
        	}
        }
		}finally {
	        // Closing
				
			if(RS!=null)
		        try{RS.close();}catch(Exception e){e.printStackTrace();} 
			if(rstype_room!=null)
	        	try{rstype_room.close();}catch(Exception e){e.printStackTrace();} 
	        if(rswing_room!=null)
	        	try{rswing_room.close();}catch(Exception e){e.printStackTrace();} 
	        
	        if(rsfloor!=null)
	        	try{rsfloor.close();}catch(Exception e){e.printStackTrace();} 
	        if(rsbuilding!=null)
	        	try{rsbuilding.close();}catch(Exception e){e.printStackTrace();} 
	        
	        if(Stmt!=null)
	        	try{Stmt.close();}catch(Exception e){e.printStackTrace();} 
	        if(Stmt2!=null)
	        	try{Stmt2.close();}catch(Exception e){e.printStackTrace();} 
	        if(Stmt3!=null)
	        	try{Stmt3.close();}catch(Exception e){e.printStackTrace();}     
	        if(Stmt4!=null)
	        	try{Stmt4.close();}catch(Exception e){e.printStackTrace();} 
	        if(Stmt5!=null)
	        	try{Stmt5.close();}catch(Exception e){e.printStackTrace();} 
		}
        return roomList;
	}
	
	public static ArrayList<Room> getRoom2()throws SQLException {
		ArrayList<Room> roomList = new ArrayList<Room>();
		PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM room INNER JOIN floor f ON fk_floor_map = f.id");
		ResultSet rs = pStmt.executeQuery();
		while(rs.next()) {
			roomList.add(new Room(rs.getInt("id"),rs.getInt("floor"), rs.getInt("room_number"), rs.getInt("m2"),
		    			new Type_Room(rs.getInt("id"), rs.getString("name")),
		    			new Wing_Room(rs.getInt("id"), rs.getString("name")),
	        			rs.getInt("nb_doors"), rs.getInt("nb_windows"),rs.getInt("x"),rs.getInt("y"),rs.getInt("width"),rs.getInt("height"),
	        			new Floor(rs.getInt("id"),rs.getString("name"),rs.getString("image_path") ,
	        			new Building(rs.getInt("id"),rs.getString("name"),rs.getString("type")) ) )) ;
        	}
		return roomList;
	}
}
