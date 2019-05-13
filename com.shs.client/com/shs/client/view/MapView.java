package com.shs.client.view;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.List;

import javax.swing.JPanel;

import com.shs.client.controller.SensorController;
import com.shs.client.view.sensor.SensorListView;
import com.shs.commons.model.Sensor;

public class MapView extends JPanel {
private SensorListView sensorList;
private SensorController sensorController;

public MapView() {
	super();
	try {
	    sensorController = new SensorController();
		List<Sensor> allSensors = sensorController.getAllSensors();
		sensorList = new SensorListView(allSensors);
	} catch (IOException e) {
		sensorList = new SensorListView(null);
		e.printStackTrace();
	}
	this.add(sensorList, BorderLayout.CENTER);
}

}
