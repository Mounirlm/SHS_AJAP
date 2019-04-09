package com.shs.server.app;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.shs.server.connection.pool.DBAccess;
import com.shs.server.connection.pool.DataSource;

public class ServerAcceptor {
	 public static void main (String[] args) throws SQLException, ClassNotFoundException, IOException {
		 	ServerSocket server = null;
		    int port = DBAccess.getPORT_SERVER();
		    
		    try {
			      server = new ServerSocket(port);
			      System.out.println("Server  Ok");
			   }catch(IOException e) { 
				   System.out.println("Error server "+e);
			   }
		    DataSource dt = new DataSource();
		    if(server!=null) {
			      while ( true ) {
			    	  Connection connDB=null;
			    	  try {
			    	  connDB=DataSource.getConnection();
			    	  System.out.println("Connection DB ok");
			    	  }catch(SQLException e1) {
			    		  System.out.println("Connection DB Refused "+e1);
			    	  }
			    	  if(connDB != null) {
				        System.out.println("Waiting client") ;
				        Socket client = null;
						try {
							client = server.accept();
						} catch (IOException e) {System.out.println("Error connection client");}
				        System.out.println("Connection established");      
				        //creation RequsetHandlre
				        RequestHandler req = new RequestHandler(client, connDB);
				        Thread service = new Thread(req);
				        service.start();
			    	  }
			      } 
		      }
	 }
		    
	 }



