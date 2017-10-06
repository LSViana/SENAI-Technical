package br.sp.senai.informatica.noon.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static Connection CONNECTION;
	private static String URL = "jdbc:mysql://172.16.7.23/empresat";
	private static String USER = "tarde";
	private static String PASSWORD = "tarde";
	public static Connection openConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
	public static void closeConnection(Connection c) throws SQLException {
		c.close();
	}
}
