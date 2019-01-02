package com.blueone.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class JDBCConnectionPoolModel implements JDBCConnectionPool {
    private static final int INITIAL_POOL_SIZE = 5;
	private static final int MAX_POOL_SIZE = 50;
	private ArrayList<Connection> connectionPool;
    private ArrayList<Connection> usedConnections;
	
	public JDBCConnectionPoolModel() throws SQLException {
		DBAccess.getInstance();
		addInitialConnections();
		connectionPool=new ArrayList<>(MAX_POOL_SIZE);
		usedConnections = new ArrayList<>(MAX_POOL_SIZE);
	}
	
	public void addInitialConnections() throws SQLException {
		for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
			Connection c = createConnection(DBAccess.getDB_URL(),DBAccess.getDB_USERNAME(), DBAccess.getDB_PASSWORD());
			System.out.println(c);
			//this.connectionPool.add(c);
        }
		System.out.println(connectionPool.size());
	}
	
	private static Connection createConnection(String url, String user, String password) throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
		     
	public int getSize() {
		return connectionPool.size() + usedConnections.size();
	}

	@Override
	public Connection getConnection() throws SQLException {
		if (connectionPool.isEmpty()) {
	        if (usedConnections.size() < MAX_POOL_SIZE) {
	            connectionPool.add(createConnection(DBAccess.getDB_URL(),DBAccess.getDB_USERNAME(), DBAccess.getDB_PASSWORD()));
	        } else {
	            throw new RuntimeException(
	              "Maximum pool size reached, no available connections!");
	        }
	    }
	 
	    Connection connection = connectionPool
	      .remove(connectionPool.size() - 1);
	    usedConnections.add(connection);
	    return connection;
	}

	@Override
	public boolean releaseConnection(Connection connection) throws SQLException{
		connectionPool.add(connection);
        return usedConnections.remove(connection);
	}
	
	public void shutdown() throws SQLException {
	    for (Connection c : usedConnections) {
	    	releaseConnection(c);
	    }
	    for (Connection c : connectionPool) {
	        c.close();
	    }
	    connectionPool.clear();
	}
	
}