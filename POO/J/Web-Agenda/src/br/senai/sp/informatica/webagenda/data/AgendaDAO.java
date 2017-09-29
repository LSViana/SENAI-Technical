package br.senai.sp.informatica.webagenda.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.senai.sp.informatica.webagenda.model.Agenda;

public class AgendaDAO {
	private static Connection connection;
	private static String insertInto = "INSERT INTO Agenda VALUES(0, ?, ?, ?, ?);";
	private static String getAll = "SELECT * FROM Agenda";
	private static String filterName = "SELECT * FROM Agenda WHERE Name LIKE ?;";
	private static String filterId= "SEELCT * FROM Agenda WHERE Id LIKE ?;";
	
	public AgendaDAO() {
		// Standard constructor
	}
	public static Agenda insert(Agenda agenda) {
		try {
			connection = ConnectionFactory.openConnection();
			PreparedStatement stmt = connection.prepareStatement(insertInto);
			stmt.setString(1, agenda.getName());
			stmt.setString(2, agenda.getEmail());
			stmt.setString(3, agenda.getAddress());
			stmt.setObject(4, agenda.getDateOfBirth());
			stmt.executeUpdate();
			stmt.close();
			ConnectionFactory.closeConnection(connection);
			return agenda;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static List<Agenda> getAll() {
		try {
			connection = ConnectionFactory.openConnection();		
			List<Agenda> result = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement(getAll);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				result.add(
						new Agenda(
							rs.getInt(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getObject(5, LocalDate.class)
						)
				);
			}
			connection.close();
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static List<Agenda> getFilterName(String name) {
		try {
			connection = ConnectionFactory.openConnection();
			List<Agenda> result = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement(filterName);
			stmt.setString(1, "'" + name + "'");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				result.add(
						new Agenda(
							rs.getInt(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getObject(5, LocalDate.class)
						)
				);
			}
			stmt.close();
			ConnectionFactory.closeConnection(connection);
			connection.close();
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
