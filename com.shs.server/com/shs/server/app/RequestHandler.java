package com.shs.server.app;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.shs.commons.model.Alert;
import com.shs.commons.model.Historical;
import com.shs.commons.model.Room;
import com.shs.commons.model.Sensor;
import com.shs.commons.model.Type_Room;
import com.shs.commons.model.User;
import com.shs.server.connection.pool.DataSource;
import com.shs.server.model.AlertManager;
import com.shs.server.model.AlertRequestManager;
import com.shs.server.model.HistoricalRequestManager;
import com.shs.server.model.RoomManager;
import com.shs.server.model.RoomRequestManager;
import com.shs.server.model.SensorManager;
import com.shs.server.model.SensorRequestManager;
import com.shs.server.model.Type_RoomRequestManager;
import com.shs.server.model.UserManager;
import com.shs.server.model.UserRequestManager;
import com.shs.server.model.Wing_RoomRequestManager;


public class RequestHandler implements Runnable {
	private static int cpt=0;
	private int num;
	private Socket client= null;
	private Connection connDB;
	private JsonReader reader;
	private JsonWriter writer;
	private static Map<Integer, ArrayList<Historical>> CACHE = new HashMap<>();


	public RequestHandler(Socket client, Connection connDB) {
		num=cpt++;
		this.client = client;
		this.connDB = connDB;


		try {
			reader = new JsonReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
			writer = new JsonWriter(new OutputStreamWriter(client.getOutputStream(), "UTF-8"));
		} catch (IOException e) {}
	}

	@Override
	public void run(){
		//Communication Json
		try {
			System.out.println("Thread:"+num+" "+requestHandler(reader));
		} catch (IOException e) {
			System.out.println("Error communication to client "+e);
		} catch (SQLException e) {
			System.out.println("Error DB "+e);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		finally{
			try {
				stopConnection();
			} catch (IOException e) {}
		}

	}

	public String requestHandler(JsonReader reader) throws IOException, SQLException, ParseException {
		String request=null;
		Object object=null;
		String className=null;
		String[] res=null;
		String message=null;

		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("request")) {
				request = reader.nextString();
				res=request.split("-");
				className=res[1];
			}
			else if (name.equals("object")) {//TODO ADD CLASS FOR R3
				String objectJson = reader.nextString();
				if(className.equals("Room"))
					object = new Gson().fromJson(objectJson, Room.class);
				if(className.equals("User"))
					object = new Gson().fromJson(objectJson, User.class);
				if(className.equals("Sensor"))
					object = new Gson().fromJson(objectJson, Sensor.class);
				if(className.equals("Alert"))
					object = new Gson().fromJson(objectJson, Alert.class);
				if(className.equals("Historical"))
					object = new Gson().fromJson(objectJson, Historical.class);

			}else {
				reader.skipValue();
			}
		}
		reader.endObject();

		//DB Traitement TODO ADD CLASS FOR R3
		switch (className) {
		case "Room":
			Room room =(Room) object;
			RoomRequestManager reqRoom = new RoomRequestManager(connDB, reader, writer, room, request);
			message=reqRoom.requestManager();
			break;

		case "User":
			User user =(User) object;
			UserRequestManager reqUser = new UserRequestManager(connDB, reader, writer, user, request);
			message=reqUser.requestManager();
			break;

		case "Sensor":
			Sensor sensor =(Sensor) object;
			SensorRequestManager reqSensor = new SensorRequestManager(connDB, reader, writer, sensor, request);
			message=reqSensor.requestManager();
			break;

		case "Alert":
			Alert alert =(Alert) object;
			AlertRequestManager reqAlert = new AlertRequestManager(connDB, reader, writer, alert, request);
			message=reqAlert.requestManager();
			break;

		case "Type_Room":
			Type_RoomRequestManager reqType_Room = new Type_RoomRequestManager(connDB, reader, writer, request);
			message=reqType_Room.requestManager();
			break;

		case "Wing_Room":
			Wing_RoomRequestManager reqWing_Room = new Wing_RoomRequestManager(connDB, reader, writer, request);
			message=reqWing_Room.requestManager();
			break;

		case "Historical":
			Historical historic =(Historical) object;			
			//add to DB
			HistoricalRequestManager reqHist = new HistoricalRequestManager(connDB, reader, writer, historic, "insert-Historical");
			message=reqHist.requestManager();
			//CACHE
			synchronized (CACHE) {
				this.isAlertToCache(historic);
			}

			break;

		default:
			break;
		}
		return message;
	}


	private void isAlertToCache(Historical historic) {
		try {//get sensor of signal
			SensorManager sensM = new SensorManager(DataSource.getConnection());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Sensor sensor = null;
		try {
			sensor = SensorManager.getSensor(historic.getFk_sensor());
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
		if (sensor.getFk_type_sensor().getTrigger_point_max()!=null) {
			if (Integer.parseInt(historic.getMessage())> sensor.getFk_type_sensor().getTrigger_point_max()) {
				this.addToCache(historic, sensor);
			}
		}
		if (sensor.getFk_type_sensor().getTrigger_point_min()!=null) {
			if (Integer.parseInt(historic.getMessage())< sensor.getFk_type_sensor().getTrigger_point_min()) {
				this.addToCache(historic, sensor);
			}
		}

	}

	private void addToCache(Historical historic, Sensor sensor) {
		//add to cache
		if (!CACHE.containsKey(historic.getFk_sensor())) {
			ArrayList<Historical> a  = new ArrayList<Historical>();
			a.add(historic);
			CACHE.put(historic.getFk_sensor(), a);
		}else {
			ArrayList<Historical> a = CACHE.get(historic.getFk_sensor());
			a.add(historic);
		}

		//check of cache to add alert in DB
		System.out.println("CACHE size :"+CACHE);
		for(Map.Entry<Integer, ArrayList<Historical>> cache : CACHE.entrySet()) {
			ArrayList<Historical> hitoricals = cache.getValue();
			ArrayList<Historical> last = new ArrayList<>();

			//get last signals of alerts
			for (int i = hitoricals.size()-1; i > sensor.getFk_type_sensor().getNb_alerts(); i--) {
				last.add(hitoricals.get(i));
			}

			//check if we have a number of signals of alert high
			if(last.size() >= sensor.getFk_type_sensor().getNb_alerts()) {
				//check if signals of alerts have the same date
				boolean date_ok=false, time_ok=false;
				for (int i = 0; i < last.size(); i++) {
					if (last.size()>i+1) {
						if (last.get(i).getDate_signal() == last.get(i+1).getDate_signal()) {
							date_ok=true;
						}
					}

				}
				//check if signals of alerts are close in time
				for (int i = 0; i < last.size(); i++) {
					if (last.size()>i+1) {
						if ((last.get(i).getHour_signal().getTime() - last.get(i+1).getHour_signal().getTime())< 5000) {
							time_ok=true;
						}
					}

				}	
				if (date_ok && time_ok) {
					Alert alert = new Alert();
					alert.setfk_sensor(sensor.getId());
					alert.setDescription(historic.getMessage());
					alert.setDate_alert(historic.getDate_signal());
					alert.setHour_alert(historic.getHour_signal());
					try {
						AlertManager alertManager = new AlertManager(DataSource.getConnection());
						AlertManager.create(alert);
						 System.out.println("alert inserted for sensor "+sensor.getId());
					}catch(SQLException ex) {
						System.err.println("Error Cache insert alert: "+ex.getMessage());
					}
				}
			}

		}
	}




	public void stopConnection() throws IOException {
		reader.close();
		writer.close();
		client.close();
	}


}
