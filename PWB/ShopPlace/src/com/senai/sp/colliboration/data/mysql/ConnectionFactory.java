package com.senai.sp.colliboration.data.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static Connection openConnnection() {		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/shopplace?serverTimezone=UTC";
			String userName = "root", password = "root132";
			return DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
