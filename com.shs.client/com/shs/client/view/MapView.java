package com.shs.client.view;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import javax.swing.JPanel;

import com.shs.client.controller.BuildingController;
import com.shs.client.controller.SensorController;
import com.shs.client.view.sensor.SensorListView;
import com.shs.commons.model.Building;
import com.shs.commons.model.Floor;
import com.shs.commons.model.Sensor;

public class MapView extends JPanel {
private SensorListView sensorList;
private SensorController sensorController;

// apres 
private BuildingListView buildingList;
private BuildingController buildingController;

MapTotalView pTotal;

public MapView() {
	super();
//	try {
//	    sensorController = new SensorController();
//		List<Sensor> allSensors = sensorController.getAllSensors();
//		sensorList = new SensorListView(allSensors);
//	} catch (IOException e) {
//		sensorList = new SensorListView(null);
//		e.printStackTrace();
//	}
//	this.add(sensorList, BorderLayout.CENTER);
//	
	/// /////////////////ici cest moun
	
	this.setLayout(new BorderLayout());	
	
	
	try {
		buildingController = new BuildingController();
		List<Building> buildings=buildingController.getBuildingList();
		buildingList= new BuildingListView(buildings);
		this.add(buildingList,BorderLayout.CENTER);
		
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		//buildingList = new BuildingListView(null);
		e.printStackTrace();
	}
	
	
	
	
	
	//pTotal = new MapTotalView();
}

 public MapTotalView getTotalView() {
	return pTotal;
}
}
