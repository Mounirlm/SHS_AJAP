package connectionPool;

import java.sql.Connection;

import com.blueone.exceptions.DBException;
/**
 * DataSource
 * 
 * The class DataSource allow to create one instance
 * of a connection pool in static to manipulate connections
 * in the database.
 * 
 * Copyright 07/01/2019 (@author Kadia Toure)
 * @version 1.0
 */

// Data source permet d'utiliser la poll

public class DataSource {
	private static JDBCConnectionPool pool=null;
	
	public DataSource() throws DBException {
		initPool();
	}
	
	/**
     * init an instance of JDBCConnectionPool
     */
	public static void initPool() throws DBException{
		if(pool == null) {
			pool = new JDBCConnectionPool();  
		}
	}
	
	public static Connection getConnection() throws DBException {
		return pool.getConnection();
	}
	
	public static void releaseConnection(Connection c) throws DBException {
		pool.releaseConnection(c);
	}
	
	public static void shutdown() throws DBException {
		pool.shutdown();
	}

	public static int getSize() {
		return pool.getSize();
	}
	
}
