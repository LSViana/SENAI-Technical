package br.sp.senai.informatica.noon.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.sp.senai.informatica.noon.model.Contact;

public class ContactDAO {
	private static Connection CONNECTION;
	private static String INSERTINTO = "INSERT INTO Contato VALUES(0, ?, ?, ?, ?)";
	private static String GETALL = "SELECT * FROM Contato";
	private static String DELETE = "DELETE FROM Contato WHERE Id = ?";
	
	public ContactDAO() {
		// Standard Constructor
	}
	
	public Contact insert(Contact contact) throws ClassNotFoundException, SQLException {
		CONNECTION = ConnectionFactory_Agenda.openConnection();
		PreparedStatement stmt = CONNECTION.prepareStatement(INSERTINTO);
		stmt.setString(1, contact.getName());
		stmt.setString(2, contact.getEmail());
		stmt.setString(3, contact.getAddress());
		stmt.setObject(4, contact.getDateOfBirthday());
		stmt.executeUpdate();
		ConnectionFactory_Agenda.closeConnection(CONNECTION);
		return contact;
	}
	public List<Contact> getAll() {
		try {
			CONNECTION = ConnectionFactory_Agenda.openConnection();
			PreparedStatement stmt = CONNECTION.prepareStatement(GETALL);
			ResultSet resultSet = stmt.executeQuery();
			List<Contact> result = new ArrayList<Contact>();
			while(resultSet.next()) {
				result.add(new Contact(resultSet.getInt(1),
						resultSet.getString(2),
						resultSet.getString(3),
						resultSet.getString(4),
						resultSet.getObject(5, LocalDate.class)));
			}
			ConnectionFactory_Agenda.closeConnection(CONNECTION);
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void delete(Contact contact) {
		delete(contact.getId());
	}
	
	public void delete(int id) {
		try {
			CONNECTION = ConnectionFactory_Agenda.openConnection();
			PreparedStatement stmt = CONNECTION.prepareStatement(DELETE);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			ConnectionFactory_Agenda.closeConnection(CONNECTION);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}