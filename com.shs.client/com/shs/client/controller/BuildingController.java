package com.shs.client.controller;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shs.client.model.RoomClientHandler;
import com.shs.client.view.AnalyzeView;
import com.shs.client.view.MapView;
import com.shs.client.view.SHSView;
import com.shs.commons.model.Building;
import com.shs.commons.model.BuildingClientHandler;
import com.shs.commons.model.Floor;
import com.shs.commons.model.FloorClientHandler;
import com.shs.commons.model.Room;
import com.shs.commons.model.Sensor;
import com.shs.commons.model.Type_Sensor;

public class BuildingController {
	
	
	private MapView mapView;
	
	private static BuildingClientHandler buildingService;
	private static FloorClientHandler    floorService;
	private RoomClientHandler     roomService;
	private SensorController     sensorService;
	private static TypeSensorController  typeSensorService;
	
	static Building building;
	
	private SHSView view;
    

	public BuildingController() throws  IOException, ClassNotFoundException, SQLException {
		this.view=view;
		
		//mapView = view.getpApp().getMapView();
		
		buildingService = new BuildingClientHandler();
		floorService = new FloorClientHandler();
		roomService =new RoomClientHandler();
		sensorService= new SensorController();
		typeSensorService =new TypeSensorController();
		
	}
	// Test if they are several building
	public static List<Building> getBuildingList() throws IOException {
		
		
		return buildingService.getBuilding("select-Building");
	}
	
	// At this case, we have one building with id=1 and name ="Residence-1"
	
	public static Building getBuilding() throws IOException{
		  
		   for(Building b : getBuildingList())
		   {
			   if (b.getName()=="Residence1")   building=b;
		   }
		  return building;
	}
	
	// Return list of floor belonging to a building
	
	public static List<Floor> getBuildingFloorList() throws IOException
		{	
			    //building.setFloor(floorService.getFloorInBuilding());
			    
				return  floorService.getFloorInBuilding();
		}
	
	public void BuildingFloorList() throws IOException {
		
		building.setFloor(getBuildingFloorList());
	}
		
		
	public static Floor getFloor(int idFloor) throws IOException{
			   
			Floor floor=null;
			
			   for(Floor f: getBuildingFloorList())
			   {
				   if (f.getId()==idFloor)   floor=f;
			   }
			   
			  return floor;
		}
	
	//At this case we have one floor, so this method return only rooms belonging to this floor (id=1).
	
	public List<Room>getRoomListInFloor(int idFloor) throws IOException 
	{
		
//		Floor floor = null;
//	
//		for (Floor f : floorService.getFloorInBuilding())
//		{
//			if (f.getId()==idFloor)  floor.setRoom((roomService.selectRoomsWithPosition(idFloor)));
//		  
//		}
		
		return roomService.selectRoomsWithPosition(idFloor);
		     
	}
	
	// Method which returns the sensor associated to room
	
	public List <Sensor> getSensorWithPosition () throws IOException 
	{

		  return sensorService.getAllSensorsWithPosition();
				
	}
	
	public List<Type_Sensor> getSensorTypeList () throws IOException, SQLException {
		
		for (Sensor s :  this.getSensorWithPosition()){
		 
			for(Type_Sensor ts : typeSensorService.getSensorType()) {
			
		       if( s.getFk_type_sensor()==ts) s.setFk_type_sensor(ts);
			}
		}
		return typeSensorService.getSensorType();
		
		  
	}
	
	public static Type_Sensor getSensorType(String name) throws IOException{
		 
		 		 
		 return typeSensorService.getSensorType(name);
		 
	 }
	 
	 
		
	 
}
