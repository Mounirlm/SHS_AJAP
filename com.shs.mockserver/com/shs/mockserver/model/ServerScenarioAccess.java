package com.shs.mockserver.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.shs.server.connection.pool.AccessConfig;

public class ServerScenarioAccess {
	private static ServerScenarioAccess serverScenarioAccess = new ServerScenarioAccess();
	private static String scenarios;
	private static Long DELTA_SIGNALS;
	private static int ROWS_VIEWS;
	
	public ServerScenarioAccess() {
		Properties prop = new Properties();
		InputStream input = null;
		
		try {
			
			input = new FileInputStream("ressources\\server-scenario.properties");

			// load a properties file
			prop.load(input);
			
			// get the property value and print it out
			scenarios = prop.getProperty("scenarios");
			DELTA_SIGNALS=Long.parseLong(prop.getProperty("DELTA_SIGNALS"));
			ROWS_VIEWS=Integer.parseInt(prop.getProperty("ROWS_VIEWS"));
			
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
			serverScenarioAccess = new ServerScenarioAccess();  
		    return serverScenarioAccess;
		}

		public static String getScenario() {
			return scenarios;
		}

		@Override
		public String toString() {
			return "ServerScenarioAccess [scenarios=" + scenarios + "]";
		}

		public static Long getDELTA_SIGNALS() {
			return DELTA_SIGNALS;
		}

		public static int getROWS_VIEWS() {
			return ROWS_VIEWS;
		}

		
}
