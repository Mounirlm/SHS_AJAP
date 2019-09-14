package com.shs.commons.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Floor {
	
	String name;
	String imagePath; 
	ArrayList<Room> room;
	transient BufferedImage floorcache =null;
	private Building fk_building;
	
	public Floor()
	{
		super();
		room=new ArrayList<Room>();
	}
	
	public Floor(String name, String path, Building fk_building){
		this.name=name;
		imagePath=path;
		this.fk_building=fk_building;
	}
	
	public Floor(String imagePath) throws IOException 
	{
		this();
		this.imagePath=imagePath;
		File imageFile= new File(imagePath);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		name = name;
	}

	public ArrayList<Room> getRoom() {
		return room;
	}

	public void setRoom(ArrayList<Room> room) {
		this.room = room;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
	public ArrayList<Sensor> getSensors()
	{		ArrayList<Sensor> ret = new ArrayList<>();
		for (Room r : getRoom()) {
			ret.addAll(r.getSensors());
		}
		return ret;
	}
	
	
	public BufferedImage getFloorImage()
	{
		if(floorcache==null)
		{
			try 
			{
				File bmpFile = new File(getImagePath());
				floorcache = ImageIO.read(bmpFile);
			} 
			catch (IOException e) 
			{
				
				e.printStackTrace();
			}
		}
		return floorcache;
		
	}
	
	@Override
	public String toString() {
		return getName();
	}

	public Building getFk_building() {
		return fk_building;
	}

	public void setFk_building(Building fk_building) {
		this.fk_building = fk_building;
	}

}
