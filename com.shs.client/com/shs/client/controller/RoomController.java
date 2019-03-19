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

import com.shs.client.model.User;
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
	 
	 public void start() throws SQLException  {
		 	Socket server ;
		    int port = 6533;
		    try {
		      server = new Socket(InetAddress.getLocalHost(),port) ;
		      PrintWriter out = new PrintWriter(server.getOutputStream(), true);
		      BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
		      System.out.println("send TOTO");
		      out.println("TOTO");
		      out.close() ;
		      } 
		    catch (IOException ioe) { } ;
	 } 
		
 
}