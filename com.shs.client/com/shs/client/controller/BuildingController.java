package com.shs.client.controller;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.shs.client.model.RoomClientHandler;
import com.shs.client.view.SHSView;
import com.shs.commons.model.Building;
import com.shs.commons.model.BuildingClientHandler;
import com.shs.commons.model.Floor;
import com.shs.commons.model.FloorClientHandler;
import com.shs.commons.model.Room;

public class BuildingController {
	
	private BuildingClientHandler buildingService;
	private FloorClientHandler    floorService;
	
	private SHSView view;
    

	public BuildingController() throws  IOException {
		this.view=view;
		buildingService = new BuildingClientHandler();
		floorService = new FloorClientHandler();
	}

	public List<Building> getBuildingList() throws IOException {
		
		return buildingService.getBuilding("select-Building");
	}
	
	public List<Floor> getBuildingFloorList() throws IOException{
		return floorService.getFloor("select-Floor");
	}
	

}
