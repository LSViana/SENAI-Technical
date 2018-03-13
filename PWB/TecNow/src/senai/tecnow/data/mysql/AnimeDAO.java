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
import senai.tecnow.model.Anime;
import senai.tecnow.model.Game;
import senai.tecnow.model.User;

@Repository
public class AnimeDAO implements DAO<Anime> {
	
	public ConnectionFactory cf;
	
	@Autowired
	private UserDAO userDAO;
	
	public AnimeDAO () {
		try {
			cf = new ConnectionFactory();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Anime> searchAll() {
		try {
			cf.open();
			//
			List<Anime> result = new ArrayList<>();
			String sql = "SELECT * FROM anime";
			PreparedStatement ps = cf.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Anime anime = new Anime();
				//
				anime.setId(rs.getLong("id"));
				anime.setName(rs.getString("name"));
				anime.setDateRegister(rs.getDate("dateregister"));
				anime.setUser(userDAO.get(rs.getLong("iduser")));
				//
				result.add(anime);
			}
			return result;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			cf.close();
		}
	}

	@Override
	public Anime get(Long id) {
		try {
			cf.open();
			//
			String sql = "SELECT * FROM anime WHERE id = ?";
			PreparedStatement ps = cf.getConnection().prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Anime anime = new Anime();
				//
				anime.setId(rs.getLong("id"));
				anime.setName(rs.getString("name"));
				anime.setDateRegister(rs.getDate("dateregister"));
				anime.setUser(userDAO.get(rs.getLong("iduser")));
				//
				return anime;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			cf.close();
		}
	}
	
	public List<Anime> get(User user) {
		try {
			cf.open();
			//
			List<Anime> result = new ArrayList<>();
			String sql = "SELECT * FROM anime WHERE iduser = ?";
			PreparedStatement ps = cf.getConnection().prepareStatement(sql);
			ps.setLong(1, user.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Anime anime = new Anime();
				//
				anime.setId(rs.getLong("id"));
				anime.setName(rs.getString("name"));
				anime.setDateRegister(rs.getDate("dateregister"));
				anime.setUser(userDAO.get(rs.getLong("iduser")));
				//
				result.add(anime);
			}
			return result;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			cf.close();
		}
	}

	@Override
	public boolean persist(Anime obj) {
		try {
			cf.open();
			//
			if(obj.getId() == null || obj.getId() <= 0) {
				insert(obj);
			}
			else {
				update(obj);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			cf.close();
		}
	}
	
	private void update(Anime obj) {
		try	{
			cf.open();
			//
			String sql = "UPDATE anime SET name = ?, dateregister = ?, iduser = ? WHERE id = ?";
			PreparedStatement ps = cf.getConnection().prepareStatement(sql);
			ps.setLong(4, obj.getId());
			ps.setString(1, obj.getName());
			ps.setDate(2, new Date(obj.getDateRegister().getTime()));
			ps.setLong(3, obj.getUser().getId());
			//
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			cf.close();
		}
	}
	
	private void insert(Anime obj) {
		try	{
			cf.open();
			//
			String sql = "INSERT INTO anime VALUES(?, ?, ?, ?)";
			PreparedStatement ps = cf.getConnection().prepareStatement(sql);
			ps.setLong(1, 0);
			ps.setString(2, obj.getName());
			ps.setDate(3, new Date(obj.getDateRegister().getTime()));
			ps.setLong(4, obj.getUser().getId());
			//
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			cf.close();
		}
	}

	@Override
	public boolean delete(Anime obj) {
		return delete(obj.getId());
	}
	
	public boolean delete(Long id) {
		try	{
			cf.open();
			//
			String sql = "DELETE FROM anime WHERE id = ?";
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
	public boolean exists(Anime obj) {
		try	{
			cf.open();
			//
			String sql = "SELECT * FROM anime WHERE id = ?";
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
