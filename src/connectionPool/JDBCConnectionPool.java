package connectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;


// La pool

public class JDBCConnectionPool implements JDBCConnectionPoolInterface {
    private static final int INITIAL_SIZE = 5;
	private static final int MAX_SIZE = 50;
	private ArrayList<Connection> connections;
    private ArrayList<Connection> usedConnections;
	
	public JDBCConnectionPool() throws SQLException  {
		DBAccess.getInstance();
		connections=new ArrayList<Connection>(INITIAL_SIZE);
		usedConnections = new ArrayList<Connection>();
		addInitialConnections();
	}
	

	private void addInitialConnections() throws SQLException{
		for (int i = 0; i < INITIAL_SIZE; i++) {
			try {
				this.connections.add(createConnection());
			} catch (SQLException e) {
				throw new SQLException("Can't create connection",e);
			}
        }
	}
	
	private Connection createConnection() throws SQLException{
			try {
				return DriverManager.getConnection(DBAccess.getDB_URL(),DBAccess.getDB_USERNAME(), DBAccess.getDB_PASSWORD());
			} catch (SQLException e) {
				throw new SQLException("Can't create connection",e);
			}
        
	}
		     
	public int getSize() {
		return connections.size() + usedConnections.size();
	}

	@Override
	public synchronized Connection getConnection() throws SQLException {
		if (connections.isEmpty()) {
	        if (usedConnections.size() < MAX_SIZE) {
	            try {
					connections.add(createConnection());
				} catch (SQLException e) {
					throw new SQLException("Can't create connection", e);
				}
	        } else {
	            throw new RuntimeException(
	              "Maximum pool size reached, no available connections!");
	        }
	    }
	 
	    Connection connection = connections.remove(connections.size() - 1);
	    usedConnections.add(connection);
	    return connection;
	}

	@Override
	public synchronized boolean releaseConnection(Connection connection) {
		connections.add(connection);
        return usedConnections.remove(connection);
	}
	
	@Override
	public synchronized void shutdownConnections() throws SQLException {
		if(!usedConnections.isEmpty()) {
			while (!usedConnections.isEmpty()) {
				releaseConnection(usedConnections.get(0));
			}
		}
	    
	    for (Connection c : connections) {
	        try {
				c.close();
			} catch (SQLException e) {
				throw new SQLException("Can't close connection", e);
			}
	    }
	    connections.clear();
	}
	
}