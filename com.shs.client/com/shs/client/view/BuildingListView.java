package com.shs.client.view;

import java.util.List;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.shs.commons.model.Building;


public class BuildingListView extends JPanel{

	private List<Building> buildings;
	private JList itemsListBuilding;

	public BuildingListView(List<Building> buildings ) {
		super();
		this.buildings = buildings;
		
		
		
		
	
	
	itemsListBuilding = new JList<Object>(this.getBuildingArray(buildings));
    JScrollPane scrollPane = new JScrollPane(itemsListBuilding,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    this.add(itemsListBuilding);
    
	}
	
	public Building[] getBuildingArray(List<Building> buildings)
	{
		Building[] bArray = new Building[buildings.size()];
		for(int i =0 ; i<buildings.size();i++)
		{
			bArray[i] = buildings.get(i);
		}
		return bArray;
	}

}
