package connectionPool;

import java.sql.Connection;
import java.sql.SQLException;

import com.blueone.exceptions.DBException;

public interface JDBCConnectionPoolInterface {
    public Connection getConnection() throws DBException;
    public boolean releaseConnection(Connection connection)throws DBException;
    public void shutdown()throws DBException;
}