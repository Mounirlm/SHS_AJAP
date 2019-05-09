package com.shs.mockserver.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.shs.commons.model.Sensor;
import com.shs.commons.model.SensorClientHandler;
import com.shs.mockserver.model.MockSensor;

public class MockApplication {
	private List<Sensor> sensors;
	private List<MockSensor> mockSensors;
	private SensorClientHandler sensorH;
	
	
	public MockApplication() {
		sensors = new ArrayList<>();
		mockSensors = new ArrayList<>();
		try {
			sensorH = new SensorClientHandler();
		} catch (IOException e) {
			System.err.println("error sensorclienthandler: "+e.getMessage());
		}
	}

	/*
	 * 
	 */
	public void start() {
		//upload real sensors from database
		try {
			sensors = sensorH.searchAllSensors();
		} catch (IOException e) {
			System.err.println("error select-all-sensors: "+e.getMessage());
		}
		
		if (!sensors.isEmpty()) {
			for (Sensor sensor : sensors) {
				mockSensors.add(new MockSensor(sensor));
			}
			
			for (MockSensor mockSensor : mockSensors) {
				System.out.println(mockSensor.toString()+"\n");
			}
		}
		
		
	}

}
