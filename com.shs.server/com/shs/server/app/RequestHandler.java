package com.shs.server.app;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.shs.commons.model.Room;
import com.shs.commons.model.User;
import com.shs.server.model.RoomManager;
import com.shs.server.model.RoomRequestManager;
import com.shs.server.model.UserManager;
import com.shs.server.model.UserRequestManager;


public class RequestHandler implements Runnable {
	private static int cpt=0;
	private int num;
	private Socket client= null;
	private Connection connDB;
	private JsonReader reader;
	private JsonWriter writer;

	
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
			System.out.println("Thread:"+num+" "+readMessage(reader));
		} catch (IOException e) {
	    	System.out.println("Error communication to client "+e);
		} catch (SQLException e) {
			System.out.println("Error DB "+e);
		}
        finally{
			try {
				stopConnection();
			} catch (IOException e) {}
		}
		
	}
	
	public String readMessage(JsonReader reader) throws IOException, SQLException {
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

		default:
			break;
		}
	    return message;
	}
	
	
	public void stopConnection() throws IOException {
        reader.close();
        writer.close();
        client.close();
    }


}
