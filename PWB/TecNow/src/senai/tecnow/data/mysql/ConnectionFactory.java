package senai.tecnow.data.mysql;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public ConnectionFactory() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}
	
	private Connection connection;
	
	public void open() {
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/tecnow?serverTimeZone=UTC", "root", "root132");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void close() {
		try {
			if(connection != null && !connection.isClosed())
				connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Connection getConnection() {
		return connection;
	}

}
