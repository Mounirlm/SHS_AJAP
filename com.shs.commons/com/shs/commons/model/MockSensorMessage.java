package com.shs.commons.model;

public class MockSensorMessage {
	private Sensor sensor;
	private int current_value;
	private int time_sc;//how long
	
	public MockSensorMessage(Sensor sensor, int current_value, int time_sc) {
		super();
		this.sensor = sensor;
		this.current_value = current_value;
		this.time_sc = time_sc;
	}
	
	public Sensor getSensor() {
		return sensor;
	}
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	public int getCurrent_value() {
		return current_value;
	}
	public void setCurrent_value(int current_value) {
		this.current_value = current_value;
	}
	public int getTime_sc() {
		return time_sc;
	}
	public void setTime_sc(int time_sc) {
		this.time_sc = time_sc;
	}
	@Override
	public String toString() {
		return "MockSensorMessage [sensor=" + sensor.getId() + ", current_value=" + current_value + ", time_sc=" + time_sc
				+ "]";
	}
	
	
	
	
}