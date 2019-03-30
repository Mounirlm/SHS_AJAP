package com.shs.client.controller;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.SQLException;
import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import com.shs.client.view.SHSView;
import com.shs.commons.model.Room;

// Fais le lien entre la vue(print) et le model(getteur)

public class RoomController {
	 private SHSView shsView;
	 
	 public RoomController(SHSView v) throws SQLException, ClassNotFoundException {
		 shsView = v;
	 }
	 
	 
	 public static void sendToServer(Object room) throws SQLException, IOException  {
		 	Socket server = null ;
		    int port = 6533;
		    JsonWriter writer = null;
		    try {
		      server = new Socket(InetAddress.getLocalHost(),port);
		      writer = new JsonWriter(new OutputStreamWriter(server.getOutputStream(), "UTF-8"));
		      Gson gson = new Gson();	
		      
			    writer.setIndent("	");
			    writer.beginObject();
			    writer.name("request").value("insert-room");
			    writer.name("room").value(gson.toJson(room));
			    writer.endObject();
			    writer.flush();
			    System.out.println("send room to server for insert");
			 
		      } 
		    catch (IOException ioe) { }
		    finally {
		    	server.close();
		    	writer.close();
		    }
		 
	 } 
	
	 public static void insert(String[] form) throws Exception {
			if(form[0].isEmpty() || form[1].isEmpty())
				throw new Exception("Empty");
			if (isInteger(Character.toString(form[0].charAt(0))))
				throw new Exception("Room type can't start by a number");
			if(!isInteger(form[1]))
				throw new Exception("Floor number must be a number");
			
			Room room = new Room();
			room.setType_room(form[0]);
			room.setFloor(Integer.parseInt(form[1]));
			System.out.println(room);
			sendToServer(room);
		}

		private static boolean isInteger(String s) {
			try {
				Integer.parseInt(s);
			} catch (Exception e) {
				return false;
			}
			return true;
		}
		

	}
 
