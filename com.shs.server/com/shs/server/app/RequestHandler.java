package com.shs.server.app;

import com.shs.commons.model.Room;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.Connection;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;


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
			//Creation request Json
		    writer.beginObject();
		    writer.name("response").value("Room inserted");
		    writer.endObject();
		    writer.flush();
		    System.out.println("Thread:"+num+" send response :Room inserted");
		} catch (IOException e) {
	    	System.out.println("Error communication to client "+e);
		}
        finally{
			try {
				stopConnection();
			} catch (IOException e) {}
		}
		
	}
	
	public Room readMessage(JsonReader reader) throws IOException {
		String request=null;
		Room room=null;
		
		reader.beginObject();
	     while (reader.hasNext()) {
	       String name = reader.nextName();
	       if (name.equals("request")) {
	    	   request = reader.nextString();System.out.println(request);
	       }
	       else if (name.equals("room")) {
	    	   String roomJson = reader.nextString();
	   			room = new Gson().fromJson(roomJson, Room.class);
	       }else {
	         reader.skipValue();
	       }
	     }
	    reader.endObject();
	    return room;
	}
	
	public void stopConnection() throws IOException {
        reader.close();
        writer.close();
        client.close();
    }


}
