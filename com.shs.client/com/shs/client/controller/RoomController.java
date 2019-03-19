package com.shs.client.controller;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.SQLException;
import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import com.shs.client.model.Room;
import com.shs.client.view.SHSView;

// Fais le lien entre la vue(print) et le model(getteur)

public class RoomController {
	 private SHSView shsView;
	 
	 public RoomController(SHSView v) throws SQLException, ClassNotFoundException {
		 shsView = v;
	 }
	 
	 
	 public static void sendToServer(String s) throws SQLException  {
		 	Socket server ;
		    int port = 6533;
		    try {
		      server = new Socket(InetAddress.getLocalHost(),port) ;
		      PrintWriter out = new PrintWriter(server.getOutputStream());
		      BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
		      System.out.println("send room");
		      out.println(s);
		      out.flush();
		      out.close() ;
		      } 
		    catch (IOException ioe) { } ;
		 
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
			//RoomManager.create(room);
			System.out.println(room);
			Gson gson = new Gson();
			String json = gson.toJson(room);  
			
		    JsonWriter writer = new JsonWriter(new OutputStreamWriter(System.out, "UTF-8"));
		    writer.setIndent("	");
		    writer.beginObject();
		    writer.name("request").value("insert-room");
		    writer.name("room").value(gson.toJson(room));
		    writer.endObject();
		    writer.flush();
		    writer.close();
		    
			sendToServer(writer.toString());
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
 
