package com.shs.server.app;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.Socket;
import java.sql.Connection;

import com.google.gson.stream.JsonReader;

public class RequestHandler implements Runnable {
	private Socket client= null;
	Connection connDB;
	BufferedReader in;
	DataInputStream din;
	PrintWriter out;
	DataOutputStream dout;
	
	public RequestHandler(Socket client, Connection connDB) {
		this.client = client;
		this.connDB = connDB;
			
		try {
			out = new PrintWriter(client.getOutputStream(), true);
	        in = new BufferedReader(new InputStreamReader(client.getInputStream()));

			        
		} catch (IOException e) {}
	}

	@Override
	public void run() {
		//Communication Json
		String resp = null;
		try {
			resp = in.readLine();
			JsonReader reader = new JsonReader(new StringReader(resp));
			System.out.println(reader.toString());
		} catch (IOException e) {}
        
        
        try {
			stopConnection();
		} catch (IOException e) {}
	}
	
	public void stopConnection() throws IOException {
        in.close();
        out.close();
        client.close();
    }


}
