package com.senai.sp.colliboration.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.senai.sp.colliboration.data.DAO;
import com.senai.sp.colliboration.model.User;

@Repository
public class UserDAO extends DAO<User> {

	public void insert(User obj) {
		openConnection();
		//
		String sql = "INSERT INTO user (name, username, password) VALUES(?, ?, ?)";
		try {
			PreparedStatement stmt;
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, obj.getName());
			stmt.setString(2, obj.getUsername());
			stmt.setString(3, obj.getPassword());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//
		closeConnection();
	}

	public void delete(User obj) {
		openConnection();
		//
		String sql = "DELETE FROM user WHERE id = ?";
		try {
			PreparedStatement stmt;
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, obj.getId());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//
		closeConnection();
	}

	public void update(User obj) {
		// TODO Auto-generated method stub
	}

	public User get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean authenticate(User user) {
		openConnection();
		//
		String sql = "SELECT * FROM user WHERE username = ? AND password = ?"; 
		try {
			PreparedStatement stmt;
			stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			closeConnection();	
		}
	}

}
