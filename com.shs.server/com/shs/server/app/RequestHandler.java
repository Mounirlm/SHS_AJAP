package com.shs.server.app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;

public class RequestHandler implements Runnable {
	Socket client= null;
	Connection connDB;
	public RequestHandler(Socket client, Connection connDB) {
		this.client = client;
		this.connDB = connDB;
	}

	@Override
	public void run() {
		//Communication Json
		try {
			InputStream in = client.getInputStream();
			DataInputStream din = new DataInputStream(in);
			OutputStream out = client.getOutputStream();
			DataOutputStream dout = new DataOutputStream(out);
			
	        String s = din.readUTF();
	        System.out.println(s);
	        
		} catch (IOException e) {}
        		
	}

}
