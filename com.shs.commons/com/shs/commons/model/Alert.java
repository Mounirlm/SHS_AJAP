package com.shs.commons.model;

import java.sql.Time;
import java.util.Date;

public class Alert {
	private int id;
	private Date date_alert;
	private Time hour_alert;
	private String description;
	private int fk_user;
	private int fk_sensor;
	
	public Alert() {
		// TODO Auto-generated constructor stub
	}
	
	public Alert(int id, Date alert, Time hour_alert, String description, int idUser, int idSensor) {
		super();
		this.id = id;
		this.date_alert = alert;
		this.hour_alert = hour_alert;
		this.description = description;
		this.fk_user = idUser;
		this.fk_sensor = idSensor;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getDate_alert() {
		return date_alert;
	}


	public void setDate_alert(Date alert) {
		this.date_alert = alert;
	}


	public Time getHour_alert() {
		return hour_alert;
	}


	public void setHour_alert(Time hour_alert) {
		this.hour_alert = hour_alert;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getFk_user() {
		return fk_user;
	}


	public void setfk_user(int idUser) {
		this.fk_user = idUser;
	}


	public int getFk_sensor() {
		return fk_sensor;
	}


	public void setfk_sensor(int idSensor) {
		this.fk_sensor = idSensor;
	}
	
	
	
	
}