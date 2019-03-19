package com.shs.client.controller;



import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.List;

import com.shs.client.model.Room;
import com.shs.client.model.RoomManager;
import com.shs.client.view.SHSView;
import com.shs.server.connection.pool.DataSource;

// Fais le lien entre la vue(print) et le model(getteur)

public class RoomController {
	 private RoomManager roomManager;
	 private SHSView shsView;
	 
	 public RoomController(SHSView v) throws SQLException, ClassNotFoundException {
		 roomManager = new RoomManager();
		 shsView = v;
		 
	 
	 }
	 
	 
	 public static void sendToServer(Room room) throws SQLException  {
		 	Socket server ;
		    int port = 6533;
		    try {
		      server = new Socket(InetAddress.getLocalHost(),port) ;
		      PrintWriter out = new PrintWriter(server.getOutputStream());
		      BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
		      System.out.println("send room");
		      out.println(room.toString());
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
 
