package queryreceiver.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	static String url = "jdbc:mysql://localhost:3306/query-receiver";
	static String username = "root";
	static String password = "root132";
	//
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//
			return DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
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
