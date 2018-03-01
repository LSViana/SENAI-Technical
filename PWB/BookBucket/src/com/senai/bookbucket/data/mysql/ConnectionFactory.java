package com.senai.bookbucket.data.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private Connection connection;
	private String url, user, password;
	public ConnectionFactory() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		url = "jdbc:mysql://localhost:3306/bookbucket?serverTimeZone=UTC";
		user = "root";
		password = "root132";
	}
	public Connection open() throws SQLException {
		return connection = DriverManager.getConnection(url, user, password);
	}
	public void close() throws SQLException {
		connection.close();
		connection = null;
	}
}
