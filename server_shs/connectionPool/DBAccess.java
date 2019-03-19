package connectionPool;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class DBAccess {
	//singleton

	private static DBAccess dbAccess = new DBAccess();
	private static  String DB_DRIVER_CLASS;
	private static String DB_URL;
	private static String DB_USERNAME;
	private static String DB_PASSWORD;
	private static int INITIAL_SIZE;
	private static int MAX_SIZE;
	
	//private Constructor
	private DBAccess(){
		Properties prop = new Properties();
		InputStream input = null;
		
		try {
			
			input = new FileInputStream("./MyRessources/db.properties");

			// load a properties file
			prop.load(input);
			
			// get the property value and print it out
			DB_DRIVER_CLASS = prop.getProperty("DB_DRIVER_CLASS");
			DB_URL = prop.getProperty("DB_URL");
			DB_USERNAME = prop.getProperty("DB_USERNAME");
			DB_PASSWORD = prop.getProperty("DB_PASSWORD");
			INITIAL_SIZE = Integer.parseInt(prop.getProperty("INITIAL_POOL_SIZE"));
			MAX_SIZE = Integer.parseInt(prop.getProperty("MAX_POOL_SIZE"));
			
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
	public static DBAccess getInstance(){
	if(dbAccess == null)
		dbAccess = new DBAccess();  
	    return dbAccess;
	}
	
	public static String getDB_DRIVER_CLASS() {
		return DB_DRIVER_CLASS;
	}
	
	public static String getDB_URL() {
		return DB_URL;
	}

	public static String getDB_USERNAME() {
		return DB_USERNAME;
	}

	public static String getDB_PASSWORD() {
		return DB_PASSWORD;
	}
	
	public static int getINITIAL_SIZE() {
		return INITIAL_SIZE;
	}

	public static int getMAX_SIZE() {
		return MAX_SIZE;
	}
	
	@Override
	public String toString() {
		return "DBAccess [dbType=" + DB_DRIVER_CLASS + ", url=" + DB_URL + ", user=" + DB_USERNAME + ", password=" + DB_PASSWORD + "]";
	}
	   
}

