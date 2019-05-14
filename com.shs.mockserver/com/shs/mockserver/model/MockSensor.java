package com.shs.mockserver.model;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.shs.commons.model.Historical;
import com.shs.commons.model.HistoricalClientHandler;
import com.shs.commons.model.MockSensorMessage;
import com.shs.commons.model.Sensor;
import com.shs.commons.model.ServerAccess;
import com.shs.mockserver.view.MockSHS;


public class MockSensor extends Thread{
	private Sensor sensor;
	private Map<String, String> scenas;
	private ArrayList<MockSensorMessage> messages = new ArrayList<>();
	private MockSensorMessage defaultMockSensorMessage;
	//communication to server
	private HistoricalClientHandler histH;
	private boolean run = true;
	private MockSHS view;

	public MockSensor(Sensor sensor, HistoricalClientHandler histH2, MockSHS view) {
		this.sensor = sensor;
		this.histH = histH2;
		this.defaultMockSensorMessage = new MockSensorMessage(sensor, sensor.getFk_type_sensor().getTrigger_point_max()-1 , 1);
		this.view = view;
	}

	public MockSensor(Sensor sensor2, HistoricalClientHandler histH2, Map<String, String> map, MockSHS v) {
		this(sensor2, histH2, v);
		this.scenas = map;


		//Get messages from map of values
		for(Map.Entry<String, String> val : scenas.entrySet()) {
			if (val.getKey().startsWith("value")) {
				String [] mess = val.getValue().split("-");
				int value = Integer.parseInt(mess[0]);
				int time = Integer.parseInt(mess[1]);
				messages.add(new MockSensorMessage(sensor, value, time));
			}
		}
		//System.out.println(messages);

	}



	/*
	 * send to server signal of life with
	 */
	public void run() {
		while(run){

			//for each scenario
			for (MockSensorMessage mockSensorMessage : messages) {
				if(mockSensorMessage.getTime_sc()>0) {//if sensor is not broken
					for (int i = 0; i < mockSensorMessage.getTime_sc(); i++) {
						//Send signal to server
						try {
							if(run==true) {
								jsonSignals(mockSensorMessage);
							}
							else {
								break;
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			//default messages
			if(messages.isEmpty()) {
				while(run) {
					try {
						if(run==true)
							jsonSignals(defaultMockSensorMessage);
						else 
							break;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else if (messages.get(messages.size()-1).getTime_sc()>0){//if sensor is not broken
				while(run) {
					try {
						if(run==true)
							jsonSignals(defaultMockSensorMessage);
						else 
							break;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else {
				stopMockSensor();
			}
		}

	}



	private void jsonSignals(MockSensorMessage mockSensorMessage) throws IOException {
		synchronized (histH) {
			Historical historic = new Historical();
			//Calendar calendar = Calendar.getInstance();
			//DateFormat format = new SimpleDateFormat("HH:mm:ss");

			historic.setFk_sensor(mockSensorMessage.getSensor().getId());
			historic.setMessage(String.valueOf(mockSensorMessage.getCurrent_value()));
			historic.setDate_signal(new Date());
			historic.setHour_signal(new java.sql.Time(new Date().getTime()));
			//System.out.println(historic);
			//send to server
			try {
				Color color =Color.BLACK;
				if(isAlert(historic, sensor))
					color = Color.RED;

				view.addSignals(historic.toString(), color);

				histH.insertHistoricalToServer(historic);

			} catch (IOException e1) {
				System.err.println("error : "+e1.getMessage());

			}

			//Delta of messages 
			try {
				Thread.sleep(ServerScenarioAccess.getDELTA_SIGNALS());
			} catch (InterruptedException e) {
				throw new  IOException(e.getMessage());
			}
		}

	}

	public void stopMockSensor() {
		run=false;
	}

	@Override
	public String toString() {
		return "MockSensor [sensor=" + sensor + "]";
	}

	private boolean isAlert(Historical historic, Sensor sensor) {
		boolean rep = false;
		//check if value lower than trigger point min
		if (sensor.getFk_type_sensor().getTrigger_point_min()!=0) {
			if (Integer.parseInt(historic.getMessage())<= sensor.getFk_type_sensor().getTrigger_point_min()) {
				rep=true;
			}
		}
		//check if value upper than max trigger
		else if (sensor.getFk_type_sensor().getTrigger_point_max()!=0) {
			if (Integer.parseInt(historic.getMessage())>= sensor.getFk_type_sensor().getTrigger_point_max()) {
				rep=true;
			}
		}
		return rep;

	}

}
