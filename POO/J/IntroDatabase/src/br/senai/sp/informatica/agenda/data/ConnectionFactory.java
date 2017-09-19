package br.senai.sp.informatica.agenda.data;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	public static Connection openConnection() {
		try {
			// Register the JDBC driver loading the class into JVM memory, i. e., all the static part of the class is loaded and working
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost/greetings", "root", "root132");
		} catch (Exception e) {
			// Something went out wrong.
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
