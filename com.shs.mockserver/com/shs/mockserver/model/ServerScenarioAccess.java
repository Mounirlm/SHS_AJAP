package com.shs.mockserver.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.shs.server.connection.pool.DBAccess;

public class ServerScenarioAccess {
	private static ServerScenarioAccess serverScenarioAccess = new ServerScenarioAccess();
	private String scenario;
	
	private ServerScenarioAccess() {
		Properties prop = new Properties();
		InputStream input = null;
		
		try {
			
			input = new FileInputStream("./mockserver.resources/server-scenario.properties");

			// load a properties file
			prop.load(input);
			
			// get the property value and print it out
			scenario = prop.getProperty("scenario");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//getters
		public static ServerScenarioAccess getInstance(){
		if(serverScenarioAccess == null)
			serverScenarioAccess = new ServerScenarioAccess();  
		    return serverScenarioAccess;
		}

		public String getScenario() {
			return scenario;
		}

		@Override
		public String toString() {
			return "ServerScenarioAccess [scenario=" + scenario + "]";
		}

		
}
