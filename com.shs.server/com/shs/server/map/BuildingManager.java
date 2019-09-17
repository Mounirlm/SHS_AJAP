package com.shs.server.map;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.shs.commons.model.Building;
import com.shs.commons.model.Floor;

public class BuildingManager {


	private static Connection conn;
	
	public BuildingManager(Connection con) {
		this.conn=con;
	}
	
	public static ArrayList<Building> getBuilding() throws SQLException{
		
		Statement Stmt = conn.createStatement();
		
		
        ArrayList<Building> buildingList = new ArrayList<Building>();
       
        ResultSet RS=null;
       	
        try {
        	//
        RS = Stmt.executeQuery("SELECT * FROM building");
        
        while(RS.next()) {
        	
        	buildingList.add(new Building(RS.getInt("id"),RS.getString("name"),RS.getString("type")));
        	
        	}
        }	
        
        finally {
        // Closing
        
        if(RS!=null)
	        try{RS.close();}catch(Exception e){e.printStackTrace();} 
		
        if(Stmt!=null)
        	try{Stmt.close();}catch(Exception e){e.printStackTrace();} 
        
        
        }
	    return buildingList;
	}
	
	
//	
//	public static ArrayList<Floor> getFloor() throws SQLException{
//		Statement Stmt = conn.createStatement();
//		Statement Stmt2 = conn.createStatement();
//		
//        ArrayList<Floor> floorList = new ArrayList<Floor>();
//        ResultSet RS=null;
//        ResultSet rsbuilding=null;
//		
//        try {
//        	//
//        RS = Stmt.executeQuery("SELECT * FROM floor");
//        
//        while(RS.next()) {
//        	rsbuilding=Stmt2.executeQuery("SELECT * FROM building");
//        	
//        	if ( rsbuilding.next() ) {
//        	floorList.add(new Floor(RS.getInt("id"),RS.getString("name"),RS.getString("image_path"), 
//        			new Building(rsbuilding.getInt("id"),rsbuilding.getString("name"), rsbuilding.getString("type"))));
//        			 
//        	}
//        }	
//        }
//        finally {
//        // Closing
//        
//        if(RS!=null)
//	        try{RS.close();}catch(Exception e){e.printStackTrace();} 
//		if(rsbuilding!=null)
//        	try{rsbuilding.close();}catch(Exception e){e.printStackTrace();} 
//        
//        if(Stmt!=null)
//        	try{Stmt.close();}catch(Exception e){e.printStackTrace();} 
//        if(Stmt2!=null)
//        	try{Stmt2.close();}catch(Exception e){e.printStackTrace();} 
//        
//        }
//	    return floorList;
//	}
//	
//	public static ArrayList<Floor> getFloor2()throws SQLException {
//		ArrayList<Floor> floorList = new ArrayList<Floor>();
//		PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM floor INNER JOIN building b ON fk_building = b.id;");
//		ResultSet rs = pStmt.executeQuery();
//		while(rs.next()) {
//			floorList.add(new Floor(rs.getInt("id"),rs.getString("name"),rs.getString("image_path"), 
//        			new Building(rs.getInt("id"),rs.getString("name"), rs.getString("type")) ) );
//        	}
//		return floorList;
//	}
}
