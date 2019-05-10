package com.shs.mockserver.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.shs.commons.model.Sensor;
import com.shs.commons.model.SensorClientHandler;
import com.shs.mockserver.model.MockSensor;
import com.shs.mockserver.model.ServerScenarioAccess;

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
			//Get scenarios from properties
			List<Map<String, String>> scenas = new ArrayList<>();
			String scenarios = ServerScenarioAccess.getScenario();
			Map<String, String> map;
			//get each scenarios to HashMap
			String [] tabscenas = scenarios.split("/");
			for (int i = 0; i < tabscenas.length; i++) {
				map = new TreeMap<>(new Comparator<String>(){

					@Override
					public int compare(String h1, String h2) {
						return h1.compareTo(h2);
					}

				});

				String[] tabscen = tabscenas[i].split(";");

				for (int j = 0; j < tabscen.length; j++) {
					String[] tabVals = tabscen[j].split(":");

					for (int k = 0; k < tabVals.length-1; k++) {
						map.put(tabVals[k], tabVals[k+1]);

					}
				}

				scenas.add(map);
			}
			System.out.println(scenas);

			//creation of mocksensors with scenarios 
			for (Sensor sensor : sensors) {
				for (int i = 0; i < scenas.size(); i++) {
					if (sensor.getId() == Integer.parseInt(scenas.get(i).get("id"))) {
						mockSensors.add(new MockSensor(sensor, scenas.get(i)));
					}
					else {
						mockSensors.add(new MockSensor(sensor));
					}
				}	
			}


			//Send signals to server
			for (MockSensor mockSensor : mockSensors) {
				//mockSensor.sendSignalToServer();
				//System.out.println(mockSensor.toString()+"\n");
			}
		}


	}

}
