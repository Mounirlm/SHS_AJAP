package com.shs.mockserver.model;

import com.shs.commons.model.Sensor;

public class MockSensor {
	private Sensor sensor;
	
	public MockSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	@Override
	public String toString() {
		return "MockSensor [sensor=" + sensor + "]";
	}
	
	
}
