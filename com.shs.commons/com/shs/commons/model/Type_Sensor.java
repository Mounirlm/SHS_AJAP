package com.shs.commons.model;

public class Type_Sensor {
	private int id;
	private String name;
	
	public Type_Sensor() {
		// TODO Auto-generated constructor stub
	}

	public Type_Sensor(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
	
	
}
