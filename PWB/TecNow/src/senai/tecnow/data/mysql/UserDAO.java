package senai.tecnow.data.mysql;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sun.istack.internal.logging.Logger;

import senai.tecnow.data.DAO;
import senai.tecnow.model.Gender;
import senai.tecnow.model.User;

@Repository
public class UserDAO implements DAO<User> {
	
	public ConnectionFactory cf;
	
	public UserDAO() {
		try {
			cf = new ConnectionFactory();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} 
	}

	@Override
	public List<User> searchAll() {
		try {
			cf.open();
			//
			List<User> result = new ArrayList<User>();
			String sql = "SELECT * FROM user";
			PreparedStatement ps = cf.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				User user = new User();
				//
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setDateOfBirth(rs.getDate("dateOfBirth"));
				user.setPassword(rs.getString("password"));
				user.setGender(Gender.valueOf(rs.getString("gender").toUpperCase()));
				//
				result.add(user);
			}
			return result;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			cf.close();
		}
	}

	@Override
	public User get(Long id) {
		try {
			cf.open();
			//
			String sql = "SELECT * FROM user WHERE id = ?";
			PreparedStatement ps = cf.getConnection().prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				User user = new User();
				//
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setDateOfBirth(rs.getDate("dateOfBirth"));
				user.setPassword(rs.getString("password"));
				user.setGender(Gender.valueOf(rs.getString("gender").toUpperCase()));
				//
				return user;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			cf.close();
		}
	}
	
	public User get(String email) {
		try {
			cf.open();
			//
			String sql = "SELECT * FROM user WHERE email = ?";
			PreparedStatement ps = cf.getConnection().prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				User user = new User();
				//
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setDateOfBirth(rs.getDate("dateOfBirth"));
				user.setPassword(rs.getString("password"));
				user.setGender(Gender.valueOf(rs.getString("gender").toUpperCase()));
				//
				return user;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			cf.close();
		}
	}

	@Override
	public boolean persist(User obj) {
		try {
			cf.open();
			//
			if(obj.getId() < 0)
				return false;
			//
			if(get(obj.getEmail()) != null) {
				// Object already exists
				update(obj);
			}
			else {
				// Object is new
				insert(obj);
				User updated = get(obj.getEmail());
				obj.setEmail(updated.getEmail());
			}
			return true;
		} catch(SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			return false;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			cf.close();
		}
	}
	
	private void update(User obj) throws SQLException {
		try {
			cf.open();
			//
			String sql = "UPDATE user SET name = ?, email = ?, dateOfBirth = ?, password = ?, gender = ? WHERE id = ?";
			PreparedStatement ps = cf.getConnection().prepareStatement(sql);
			ps.setLong(6, obj.getId());
			ps.setString(1, obj.getName());
			ps.setString(2, obj.getEmail());
			ps.setString(4, obj.getPassword());
			ps.setDate(3, new Date(obj.getDateOfBirth().getTime()));
			ps.setString(5, obj.getGender().toString().toLowerCase());
			//
			ps.executeUpdate();
		} finally {
			cf.close();
		}
	}
	
	private void insert(User obj) throws SQLException {
		try {
			cf.open();
			//
			String sql = "INSERT INTO user VALUES(?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = cf.getConnection().prepareStatement(sql);
			ps.setLong(1, obj.getId());
			ps.setString(2, obj.getName());
			ps.setString(3, obj.getEmail());
			ps.setString(4, obj.getPassword());
			ps.setDate(5, new Date(obj.getDateOfBirth().getTime()));
			ps.setString(6, obj.getGender().toString().toLowerCase());
			//
			ps.executeUpdate();
		} finally {
			cf.close();
		}
	}

	@Override
	public boolean delete(User obj) {
		try {
			cf.open();
			//
			String sql = "DELETE FROM user WHERE id = ?";
			PreparedStatement ps = cf.getConnection().prepareStatement(sql);
			ps.setLong(1, obj.getId());
			ps.executeUpdate();
			//
			return true;
		} catch(SQLException e) {
			return false;
		} finally {
			cf.close();
		}
	}

	@Override
	public boolean exists(User obj) {
		try {
			cf.open();
			//
			String sql = "SELECT * FROM user WHERE id = ?";
			PreparedStatement ps = cf.getConnection().prepareStatement(sql);
			ps.setLong(1, obj.getId());
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			cf.close();
		}
	}
	
	public boolean existsEmail(String email) {
		try {
			cf.open();
			//
			String sql = "SELECT * FROM user WHERE email = ?";
			PreparedStatement ps = cf.getConnection().prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			cf.close();
		}
	}

}