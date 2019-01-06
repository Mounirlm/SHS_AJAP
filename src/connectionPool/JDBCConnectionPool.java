package connectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.blueone.exceptions.DBException;

public class JDBCConnectionPool implements JDBCConnectionPoolInterface {
    private static final int INITIAL_POOL_SIZE = 5;
	private static final int MAX_POOL_SIZE = 50;
	private ArrayList<Connection> connectionPool;
    private ArrayList<Connection> usedConnections;
	
	public JDBCConnectionPool() throws DBException  {
		DBAccess.getInstance();
		connectionPool=new ArrayList<Connection>();
		usedConnections = new ArrayList<Connection>();
		addInitialConnections();
	}
	
	private void addInitialConnections() throws DBException{
		for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
			try {
				this.connectionPool.add(DriverManager.getConnection(DBAccess.getDB_URL(),DBAccess.getDB_USERNAME(), DBAccess.getDB_PASSWORD()));
			} catch (SQLException e) {
				throw new DBException("Can't create connection",e);
			}
        }
	}
	
		     
	public int getSize() {
		return connectionPool.size() + usedConnections.size();
	}

	@Override
	public synchronized Connection getConnection() throws DBException {
		if (connectionPool.isEmpty()) {
	        if (usedConnections.size() < MAX_POOL_SIZE) {
	            try {
					connectionPool.add(DriverManager.getConnection(DBAccess.getDB_URL(),DBAccess.getDB_USERNAME(), DBAccess.getDB_PASSWORD()));
				} catch (SQLException e) {
					throw new DBException("Can't create connection", e);
				}
	        } else {
	            throw new RuntimeException(
	              "Maximum pool size reached, no available connections!");
	        }
	    }
	 
	    Connection connection = connectionPool.remove(connectionPool.size() - 1);
	    usedConnections.add(connection);
	    return connection;
	}

	@Override
	public synchronized boolean releaseConnection(Connection connection) {
		connectionPool.add(connection);
        return usedConnections.remove(connection);
	}
	
	@Override
	public synchronized void shutdown() throws DBException {
		if(!usedConnections.isEmpty()) {
			while (!usedConnections.isEmpty()) {
				releaseConnection(usedConnections.get(0));
			}
		}
	    
	    for (Connection c : connectionPool) {
	        try {
				c.close();
			} catch (SQLException e) {
				throw new DBException("Can't close connection", e);
			}
	    }
	    connectionPool.clear();
	}
	
}