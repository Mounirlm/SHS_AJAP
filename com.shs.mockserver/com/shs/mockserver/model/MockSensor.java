package com.shs.mockserver.model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.shs.commons.model.MockSensorMessage;
import com.shs.commons.model.Sensor;
import com.shs.commons.model.ServerAccess;


public class MockSensor {
	private Sensor sensor;
	private MockSensorMessage mockSensorMessage;
	private Map<String, String> scenas;
	private ArrayList<MockSensorMessage> messages = new ArrayList<>();
	//communication to server
	private Socket server;
	private JsonReader reader;
	private JsonWriter writer;
	private int port = ServerAccess.getPORT_SERVER();
	private String adress =ServerAccess.getSERVER();
	

	public MockSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public MockSensor(Sensor sensor2, Map<String, String> map) {
		this(sensor2);
		this.scenas = map;
		
		
		//Get messages from hashmap of values
		for(Map.Entry<String, String> val : scenas.entrySet()) {
			if (val.getKey().startsWith("value")) {
				String [] mess = val.getValue().split("-");
				int value = Integer.parseInt(mess[0]);
				int time = Integer.parseInt(mess[1]);
				messages.add(new MockSensorMessage(sensor, value, time));
			}
		}
		System.out.println(messages);
		
	}

	public void getFlux() throws IOException { 
		try {
			this.server = new Socket(adress,port);		
			reader = new JsonReader(new InputStreamReader(server.getInputStream(), "UTF-8"));
			writer = new JsonWriter(new OutputStreamWriter(server.getOutputStream(), "UTF-8"));
		}catch(IOException e) {
			throw new IOException("Error connection to server ");
		}
	}

	public void stopFlux() throws IOException {
		try{
			reader.close();
			writer.close();
			server.close();}
		catch(IOException e) {
			throw new IOException("Error closed flux "+e);
		}
	}

	/*
	 * send to server signal of life with
	 */
	public void sendSignalToServer() {
		while(true){
			
			//get connections to socket server
			try {
				getFlux();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				String request = "mock-Sensor";

				//Creation request Json for server
				writer.beginObject();
				writer.name("request").value(request);
				writer.name("object").value(new Gson().toJson(sensor));
				writer.name("message").value(new Gson().toJson(mockSensorMessage));
				writer.endObject();
				writer.flush();//send to server
				System.out.println("client :request:"+request+"\n object"+new Gson().toJson(sensor));

				//response from server
				reader.beginObject();
				String response = "Server "+reader.nextName()+": "+reader.nextString();
				System.out.println(response);
				reader.endObject();
			} 
			catch (IOException ioe) { 
				System.out.println("Error communication to server ");
			}
			finally {
				//stop connections
				try {
					stopFlux();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
	}


}



@Override
public String toString() {
	return "MockSensor [sensor=" + sensor + "]";
}


}
