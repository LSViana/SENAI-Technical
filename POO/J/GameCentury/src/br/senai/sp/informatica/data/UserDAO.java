package br.senai.sp.informatica.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.PreparedStatement;

public class UserDAO {
	private String loginQuery = "SELECT * FROM `user` WHERE username = ? AND password = ?";
	private String searchLogin = "SELECT * FROM `user` WHERE username = ?";
	private String insertBuy = "INSERT INTO `buy` VALUES(0, ?, ?, ?)";
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
	public void registerBuy(String userLogin, String gameName) {
		connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(searchLogin);
			stmt.setString(1, userLogin);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				PreparedStatement stmtBuy = connection.prepareStatement(insertBuy);
				stmtBuy.setInt(1, rs.getInt(1));
				stmtBuy.setString(2, LocalDateTime.now().toString());
				stmtBuy.setString(3, gameName);
				stmtBuy.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			ConnectionFactory.closeConnection(connection);
		}
	}
}
