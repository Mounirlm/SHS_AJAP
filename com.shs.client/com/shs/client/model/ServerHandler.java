package com.shs.client.model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.shs.commons.model.Room;
import com.shs.server.connection.pool.DBAccess;

public class ServerHandler {
	private Socket server;
	private JsonReader reader;
	private JsonWriter writer;
	private Gson gson;
	//WITHOUT VM
	private int port = 6533;
	private InetAddress adress =InetAddress.getLocalHost();
	//WITH VM
	private int portServer = DBAccess.getPORT_SERVER();
	private String adressServer = DBAccess.getSERVER();
	
	public ServerHandler() throws UnknownHostException, IOException {
		gson = new Gson();
	}
	
	public void insertRoomToServer(Room room)throws IOException  {
	    try {
	    	//connections
	     	getFlux();
	     	//Creation request Json
		    writer.setIndent("	");
		    writer.beginObject();
		    writer.name("request").value("insert-room");
		    writer.name("room").value(gson.toJson(room));
		    writer.endObject();
		    writer.flush();
		    System.out.println("send room to server for insert");
		 
	      } 
	    catch (IOException ioe) { 
	    	throw new IOException("Error communication to server");
	    }finally {
	    	stopFlux();
	    }
	 
 } 
	public void getFlux() throws IOException {
		try {  
			this.server = new Socket(adress,port);		
			reader = new JsonReader(new InputStreamReader(server.getInputStream(), "UTF-8"));
		    writer = new JsonWriter(new OutputStreamWriter(server.getOutputStream(), "UTF-8"));
		} catch (IOException e) {
			throw new IOException("Error connection  to server");
		}
	}
	
	public void stopFlux() throws IOException {
        reader.close();
        writer.close();
        server.close();
    }
}
