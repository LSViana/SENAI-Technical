package senai.tecnow.data.mysql;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import senai.tecnow.data.DAO;
import senai.tecnow.model.Game;
import senai.tecnow.model.GameCategory;
import senai.tecnow.model.User;

@Repository
public class GameDAO implements DAO<Game> {
	
	@Autowired
	private UserDAO userDAO;
	
	public ConnectionFactory cf;
	
	public GameDAO() {
		try {
			cf = new ConnectionFactory();
		} catch(ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Game> searchAll() {
		try	{
			cf.open();
			//
			List<Game> result = new ArrayList<>();
			String sql = "SELECT * FROM game";
			PreparedStatement ps = cf.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Game game = new Game();
				//
				game.setId(rs.getLong("id"));
				game.setName(rs.getString("name"));
				game.setCategory(GameCategory.valueOf(rs.getString("category").toUpperCase()));
				game.setDateRegister(rs.getDate("dateregister"));
				game.setUser(userDAO.get(rs.getLong("iduser")));
				//
				result.add(game);
			}
			return result;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			cf.close();
		}
	}

	@Override
	public Game get(Long id) {
		try	{
			cf.open();
			//
			String sql = "SELECT * FROM game WHERE id = ?";
			PreparedStatement ps = cf.getConnection().prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Game game = new Game();
				//
				game.setId(rs.getLong("id"));
				game.setName(rs.getString("name"));
				game.setCategory(GameCategory.valueOf(rs.getString("category").toUpperCase()));
				game.setDateRegister(rs.getDate("dateregister"));
				game.setUser(userDAO.get(rs.getLong("iduser")));
				//
				return game;
			}
			return null;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			cf.close();
		}
	}
	
	public List<Game> get(User user) {
		try	{
			cf.open();
			//
			List<Game> result = new ArrayList<>();
			String sql = "SELECT * FROM game WHERE iduser = ?";
			PreparedStatement ps = cf.getConnection().prepareStatement(sql);
			ps.setLong(1, user.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Game game = new Game();
				//
				game.setId(rs.getLong("id"));
				game.setName(rs.getString("name"));
				game.setCategory(GameCategory.valueOf(rs.getString("category").toUpperCase()));
				game.setDateRegister(rs.getDate("dateregister"));
				game.setUser(userDAO.get(rs.getLong("iduser")));
				//
				result.add(game);
			}
			return result;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			cf.close();
		}
	}
	
	public Game get(String name, java.util.Date dateregister) {
		try	{
			cf.open();
			//
			String sql = "SELECT * FROM game WHERE name = ? AND dateregister = ?";
			PreparedStatement ps = cf.getConnection().prepareStatement(sql);
			ps.setString(1, name);
			ps.setDate(2, new Date(dateregister.getTime()));
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Game game = new Game();
				//
				game.setId(rs.getLong("id"));
				game.setName(rs.getString("name"));
				game.setCategory(GameCategory.valueOf(rs.getString("category").toUpperCase()));
				game.setDateRegister(rs.getDate("dateregister"));
				game.setUser(userDAO.get(rs.getLong("iduser")));
				//
				return game;
			}
			return null;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			cf.close();
		}
	}

	@Override
	public boolean persist(Game obj) {
		try	{
			cf.open();
			//
			if(obj.getId() == null || obj.getId() <= 0) {
				// Object doesn't yet exists
				insert(obj);
			}
			else {
				// Object already exists
				update(obj);
			}
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			cf.close();
		}
	}
	
	private void update(Game obj) {
		try	{
			cf.open();
			//
			String sql = "UPDATE game SET name = ?, dateregister = ?, category = ?, iduser = ? WHERE id = ?";
			PreparedStatement ps = cf.getConnection().prepareStatement(sql);
			ps.setLong(5, obj.getId());
			ps.setString(1, obj.getName());
			ps.setDate(2, new Date(obj.getDateRegister().getTime()));
			ps.setString(3, obj.getCategory().toString().toLowerCase());
			ps.setLong(4, obj.getUser().getId());
			//
			ps.executeUpdate();
		} catch(SQLException e) {
			
		} finally {
			cf.close();
		}
	}
	
	private void insert(Game obj) {
		try	{
			cf.open();
			//
			String sql = "INSERT INTO game VALUES(?, ?, ?, ?, ?)";
			PreparedStatement ps = cf.getConnection().prepareStatement(sql);
			ps.setLong(1, 0);
			ps.setString(2, obj.getName());
			ps.setString(3, obj.getCategory().toString().toLowerCase());
			ps.setDate(4, new Date(obj.getDateRegister().getTime()));
			ps.setLong(5, obj.getUser().getId());
			//
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			cf.close();
		}
	}

	@Override
	public boolean delete(Game obj) {
		return delete(obj.getId());
	}
	
	public boolean delete(Long id) {
		try	{
			cf.open();
			//
			String sql = "DELETE FROM game WHERE id = ?";
			PreparedStatement ps = cf.getConnection().prepareStatement(sql);
			ps.setLong(1, id);
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
	public boolean exists(Game obj) {
		try	{
			cf.open();
			//
			String sql = "SELECT * FROM game WHERE id = ?";
			PreparedStatement ps = cf.getConnection().prepareStatement(sql);
			ps.setLong(1, obj.getId());
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			cf.close();
		}
	}

}
