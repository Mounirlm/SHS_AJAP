package com.shs.server.app;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

import com.shs.server.connection.pool.DataSource;

public class ServerAcceptor {
	 public static void main (String[] args) throws SQLException {
		 	ServerSocket server;
		    int port = 6533;
		    try {
			      server = new ServerSocket(port) ;
			      System.out.println("Server Ok") ;
			      while ( true ) {
			    	  Connection connDB = null;
			    	  //connDB = DataSource.getConnection();
			    	  //System.out.println(connDB);
			    	  //if(connDB != null) {
				        System.out.println("Waiting client") ;
				        Socket client = server.accept() ;
				        System.out.println("Connection established");      
				        //creation RequsetHandlre
				        RequestHandler req = new RequestHandler(client, connDB);
				        Thread service = new Thread(req);
				        service.start();
				        //client.close() ; 
			    	  //}
			      } 
		      }
		    catch(IOException e) { }
	 }


}
