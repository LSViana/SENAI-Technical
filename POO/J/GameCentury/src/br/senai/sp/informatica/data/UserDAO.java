package br.senai.sp.informatica.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class UserDAO {
	private String loginQuery = "SELECT * FROM `user` WHERE username = ? AND password = ?";
	private Connection connection;
	public boolean doLogin(String userName, String password) {
		connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(loginQuery);
			stmt.setString(1, userName);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			ConnectionFactory.closeConnection(connection);
		}
	}
}
