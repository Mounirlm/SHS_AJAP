package com.shs.commons.model;

import java.util.Date;

public class Alert {
	private int id;
	private Date alert;
	private String description;
	private int idUser;
	private int idSensor;
	
	public Alert(int id, Date alert, String description, int idUser, int idSensor) {
		super();
		this.id = id;
		this.alert = alert;
		this.description = description;
		this.idUser = idUser;
		this.idSensor = idSensor;
	}
	
	public Alert() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getAlert() {
		return alert;
	}

	public void setAlert(Date alert) {
		this.alert = alert;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdSensor() {
		return idSensor;
	}

	public void setIdSensor(int idSensor) {
		this.idSensor = idSensor;
	}

	@Override
	public String toString() {
		return "Alert [id=" + id + ", alert=" + alert + ", description=" + description + ", idUser=" + idUser
				+ ", idSensor=" + idSensor + "]";
	}
	
	
}
