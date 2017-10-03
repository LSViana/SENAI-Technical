package br.sp.senai.informatica.noon.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static Connection CONNECTION;
	private static String URL = "jdbc:mysql://localhost/agendat";
	private static String USER = "root";
	private static String PASSWORD = "root132";
	public static Connection openConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
	public static void closeConnection(Connection c) throws SQLException {
		c.close();
	}
}
