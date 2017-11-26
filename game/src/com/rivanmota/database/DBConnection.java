package com.rivanmota.database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public final class DBConnection {

	private DataSource dataSource;
	
	public DBConnection() {
		try {
			dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/hockeyGame");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
