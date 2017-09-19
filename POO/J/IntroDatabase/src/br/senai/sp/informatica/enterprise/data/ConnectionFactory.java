package br.senai.sp.informatica.enterprise.data;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	public static Connection openConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost/sys");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static void closeConnection(Connection c) {
		try {
			c.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
