package com.shs.mockserver.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.shs.commons.model.HistoricalClientHandler;
import com.shs.commons.model.Sensor;
import com.shs.commons.model.SensorClientHandler;
import com.shs.commons.model.Type_Sensor;
import com.shs.mockserver.model.MockSensor;
import com.shs.mockserver.model.ServerScenarioAccess;
import com.shs.mockserver.view.MockSHS;

public class MockController implements ActionListener {
	private MockSHS view;
	private List<Sensor> sensors;
	private List<MockSensor> mockSensors;
	private SensorClientHandler sensorH;
	private HistoricalClientHandler histH;
	private List<Type_Sensor> types_sensors;
	public MockController(MockSHS view) {
		super();
		this.view = view;
		view.getbStart().addActionListener(this);
		view.getbStop().addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		//JOptionPane.showMessageDialog(null,"coucou", "Inserted", JOptionPane.INFORMATION_MESSAGE);
		if(e.getSource() instanceof JButton ){
			String choix = e.getActionCommand();
			switch (choix) {
			case "START":
				view.getbStart().setVisible(false);
				view.getbStop().setVisible(true);
				view.setScreenServer("Running", Color.GREEN);
				try {	
					start();
				}catch (Exception e2) {
					JOptionPane.showMessageDialog(null,"Error", e2.getMessage(), JOptionPane.ERROR_MESSAGE);
				}
				break;

			case "STOP":
				view.getbStop().setVisible(false);
				view.getbStart().setVisible(true);
				view.setScreenServer("Stopped", Color.RED);
				try {	
					stop();
				}catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
				}
				break;

			default:
				break;
			}
		}


	}





	/*
	 * 
	 */
	public void start() throws Exception {
		sensors = new ArrayList<>();
		mockSensors = new ArrayList<>();
		types_sensors = new ArrayList<>();
		view.cleanViews();
		try {
			sensorH = new SensorClientHandler();
			histH = new HistoricalClientHandler();
		} catch (IOException e) {
			throw new  IOException("error clienthandler: "+e.getMessage());
		}
		//upload real sensors from database
		try {
			sensors = sensorH.searchAllSensors();
			types_sensors = sensorH.selectAllTypeSensors();
			for (Type_Sensor types : types_sensors) {
				view.addPtypesSensors(types.toString2());
			}

		} catch (IOException e) {
			throw new  Exception("error : "+e.getMessage());
		}

		if (!sensors.isEmpty()) {
			//Get scenarios from properties
			List<Map<String, String>> scenas = new ArrayList<>();
			ServerScenarioAccess.getInstance();
			String scenarios = ServerScenarioAccess.getScenario();
			Map<String, String> map;

			//get each scenarios to a Map
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
			for (int i = 0; i < scenas.size(); i++) {
				view.addScenarios(scenas.get(i).toString());
			}
			System.out.println(sensors);
			//creation of mocksensors with scenarios 
			for (Sensor sensor : sensors) {
				if((sensor.getInstalled() && sensor.getStatus())) {
					for (int i = 0; i < scenas.size(); i++) {
						if (sensor.getId() == Integer.parseInt(scenas.get(i).get("id"))) {
							mockSensors.add(new MockSensor(sensor,histH, scenas.get(i), view));
							//send signals to server
							mockSensors.get(mockSensors.size()-1).start();
						}
						else {
							mockSensors.add(new MockSensor(sensor,histH, view));
							//mockSensors.get(mockSensors.size()-1).start();
						}
					}
				}	
			}


			//Send signals to server
			for (MockSensor mockSensor : mockSensors) {

				//mockSensor.start();
				//System.out.println(mockSensor.toString()+"\n");
			}
		}


	}


	private void stop() {
		for (MockSensor mockSensor : mockSensors) {
			mockSensor.stopMockSensor();
		}
		mockSensors.clear();
		sensors.clear();
		types_sensors.clear();

	}

}
