package com.blueone.exceptions;

import java.sql.SQLException;

public class DBException extends SQLException{
	public DBException(String message, SQLException e) {
		super(message+"\n"+e.getMessage());
	}
}
