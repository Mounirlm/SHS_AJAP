package com.shs.server.map;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.shs.commons.model.Building;
import com.shs.commons.model.Floor;
import com.shs.commons.model.Room;
import com.shs.commons.model.Sensor;
import com.shs.commons.model.ServerAccess;

public class BuildingClientHandler {
	private Socket server;
	private JsonReader reader;
	private JsonWriter writer;
	private Gson gson;
	//WITH VM
	private int port = ServerAccess.getPORT_SERVER();
	private String adress =ServerAccess.getSERVER();
	
	public BuildingClientHandler() throws UnknownHostException, IOException {
		gson = new Gson();
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
	public List<Building> getBuilding() throws IOException {
		
		List<Building > list= new ArrayList<Building>();
		Building building;
		getFlux();
		
		try {
			
			String request = "select-Building";
			//Creation request Json
			
			writer.setIndent("	");
		    writer.beginObject();
		    writer.name("request").value(request);
		    
		    writer.endObject();
		    writer.flush();
		    System.out.println("request:"+request);
		    
		    //response
		    reader.beginObject();
		    System.out.println("il fait bo "); 
		    
		    while (reader.hasNext()) {
			    String name= reader.nextName();
			    if(name.equals("null")) {
			    	System.out.println(reader.nextString());
			    
			    }else {
			    String objectJson=reader.nextString();
			    list.add(new Gson().fromJson(objectJson, Building.class));
		    	
		    	System.out.println(list); 	
		    }
		   
	    }
		    reader.endObject();
		    return  list;
	      } 
	    catch (IOException ioe) { 
	    	throw new IOException("Error communication to server ");
		}
	    finally {
	    	stopFlux();
	    }
	}
}
