package com.shs.server.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.shs.commons.model.Type_Sensor;

public class Type_SensorRequestManager {
	private Connection connDB;
	private JsonReader reader;
	private JsonWriter writer;
	private String  request;
	Type_SensorManager type_SensorManager;
	
	public Type_SensorRequestManager(Connection connDB, JsonReader reader, JsonWriter writer, String request) {
		super();
		this.connDB = connDB;
		this.reader = reader;
		this.writer = writer;
		this.request = request;
		this.type_SensorManager = new Type_SensorManager(connDB);
	}
	
	public String requestManager() throws SQLException, IOException {		
		boolean response = false;
		String message=null, error="no row(s)";
		String[] res=request.split("-");
		switch (request) {
			case "selectAll-Type_Sensor":
				try{
					List<Type_Sensor> types;
					types=Type_SensorManager.getAllType_Sensor();
					writer.beginObject();
					if(!types.isEmpty()) {
						response=true;
						Gson gson = new Gson();
						for (Type_Sensor type : types) {
							writer.name("types").value(gson.toJson(type));
						}
					}else {
						writer.name("null").value("null");	
					}
					writer.endObject();
				}catch(SQLException e) {
		        	error="Error select all "+e;
		        	writer.name("null").value(error);
		        }	
					
				break;
			default:
				break;
			}
			
		if(response)
			message=request+"-succusful";
		else
			message=request+"-failed: "+error;
		
		//send to client
		writer.flush();
		
		return message;
	}
}
