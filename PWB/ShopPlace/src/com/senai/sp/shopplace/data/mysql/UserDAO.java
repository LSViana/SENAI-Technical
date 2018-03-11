package com.senai.sp.shopplace.data.mysql;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.senai.sp.shopplace.data.DAO;
import com.senai.sp.shopplace.model.User;

@Repository
public class UserDAO extends DAO<User> {

	public void insert(User obj) {
		openConnection();
		//
		String sql = "INSERT INTO user (name, email, password, dateofbirth) VALUES(?, ?, ?, ?)";
		try {
			// Hashing user password
			obj.hashPassword();
			//
			PreparedStatement stmt;
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, obj.getName());
			stmt.setString(2, obj.getEmail());
			stmt.setString(3, obj.getPassword());
			stmt.setDate(4, new Date(obj.getDateOfBirth().getTime()));
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
		openConnection();
		//
		String sql = "SELECT id, name, email, password, dateofbirth FROM user WHERE id = ?"; 
		try {
			PreparedStatement stmt;
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			User user;
			if(rs.next()) {
				user = new User();
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				Date dt = rs.getDate("dateofbirth");
				Calendar c = Calendar.getInstance();
				c.setTime(dt);
				c.add(Calendar.DATE, 1);
				user.setDateOfBirth(new Date(c.getTimeInMillis()));
				return user;
			}
			else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			closeConnection();	
		}
	}

	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public User authenticate(User user) {
		openConnection();
		//
		String sql = "SELECT id, name, email, password, dateofbirth FROM user WHERE email = ? AND password = ?"; 
		try {
			PreparedStatement stmt;
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getPassword());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setDateOfBirth(rs.getDate("dateofbirth"));
				return user;
			}
			else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			closeConnection();	
		}
	}

}
