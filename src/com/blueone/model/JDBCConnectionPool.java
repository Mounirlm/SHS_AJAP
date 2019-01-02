package com.blueone.model;

import java.sql.Connection;
import java.sql.SQLException;

public interface JDBCConnectionPool {
    Connection getConnection() throws SQLException;
    boolean releaseConnection(Connection connection)throws SQLException;
}