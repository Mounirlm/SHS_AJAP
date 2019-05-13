package com.shs.client.view.sensor;

import com.shs.commons.model.Type_Sensor;

public class SensorTypeViewFactory {

	public static SensorTypeGenericView getInstance(Type_Sensor sensorType) {
		String type = sensorType.getName();
		switch (type) {
		case "smoke sensor":
			return new SmokeSensorView();
		case "temperature sensor":
			return new TemperatureSensorView();
		case "door sensor":
			return new DoorSensorView();
		case "window sensor":
			return new WindowSensorView();
		case "fall sensor":
			return new FallSensorView();
		default:
			return new SmokeSensorView();
		}
	}
}
