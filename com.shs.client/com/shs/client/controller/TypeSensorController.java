package com.shs.client.controller;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.List;

import com.shs.commons.model.Sensor;
import com.shs.commons.model.SensorClientHandler;
import com.shs.commons.model.Type_Sensor;
import com.shs.server.connection.pool.DataSource;
import com.shs.server.model.SensorTypeManager;

public class TypeSensorController {
private SensorTypeManager typeSensorService;

public TypeSensorController() throws UnknownHostException, IOException, SQLException, ClassNotFoundException {
	typeSensorService = new SensorTypeManager(new DataSource().getConnection());
}

public boolean update(Type_Sensor typeSensor) throws IOException, SQLException {
	return typeSensorService.update(typeSensor);
}

}
