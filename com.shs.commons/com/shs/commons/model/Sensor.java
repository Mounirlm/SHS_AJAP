package com.shs.commons.model;

import java.util.Date;

public class Sensor {
	private int id;
	private String name;
	private String ipAdress;
	private Date date_setup;
	private boolean status;
	private String type;
	private int idRoom;
	
	public Sensor(int id, String name, String ipAdress, Date date_setup, boolean status, String type, int idRoom) {
		super();
		this.id = id;
		this.name = name;
		this.ipAdress = ipAdress;
		this.date_setup = date_setup;
		this.status = status;
		this.type = type;
		this.idRoom = idRoom;
	}
	
	public Sensor() {
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

	public String getIpAdress() {
		return ipAdress;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	public Date getDate_setup() {
		return date_setup;
	}

	public void setDate_setup(Date date_setup) {
		this.date_setup = date_setup;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(int idRoom) {
		this.idRoom = idRoom;
	}

	@Override
	public String toString() {
		return "Sensor [id=" + id + ", name=" + name + ", ipAdress=" + ipAdress + ", date_setup=" + date_setup
				+ ", status=" + status + ", type=" + type + ", idRoom=" + idRoom + "]";
	}
	
	
	
}
