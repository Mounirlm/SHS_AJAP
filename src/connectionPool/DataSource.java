package connectionPool;

import java.sql.Connection;

import java.sql.SQLException;

// Data source permet d'utiliser la poll

public class DataSource {
	private static JDBCConnectionPool pool = null;  

	
	public DataSource() throws SQLException, ClassNotFoundException{
		initPool();
	}
	
	public JDBCConnectionPool initPool() throws ClassNotFoundException, SQLException {
		if(pool==null)
			pool = new JDBCConnectionPool();
		
		return pool;
	}
		
	public static Connection getConnection() throws SQLException {
		return pool.getConnection();
	}
	
	public static void releaseConnection(Connection c) throws SQLException {
		pool.releaseConnection(c);
	}
	
	public static void shutdown() throws SQLException {
		pool.shutdownConnections();
	}

	public static int getSize() {
		return pool.getSize();
	}
	
}
