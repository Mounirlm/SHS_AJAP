package com.shs.commons.model;

import java.util.ArrayList;
import java.util.List;

public class Stock {

	
	private static String name = "stock";
	private List<Sensor>sensors ;
	

	public Stock() {
		super();
		sensors = new ArrayList<Sensor>();
		Stock.name=getName();
	}
	// commentaires essai testgdfhs
	private String getName() {
		// TODO Auto-generated method stub
		return Stock.name;
	}

	public List<Sensor> getSensors() {
		return sensors;
	}
	
	public Sensor[] getSensorArray()
	{
		Sensor[] sa = new Sensor[sensors.size()];
		for(int i =0 ; i<sensors.size();i++)
		{
			sa[i] = sensors.get(i);
		}
		return sa;
	}

	
}
