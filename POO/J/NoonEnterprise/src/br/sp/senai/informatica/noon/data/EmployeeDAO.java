package br.sp.senai.informatica.noon.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.sp.senai.informatica.noon.model.Contact;
import br.sp.senai.informatica.noon.model.Employee;

public class EmployeeDAO {
	private static Connection CONNECTION;
	private static String INSERTINTO = "INSERT INTO Funcionario VALUES(0, ?, ?, ?, ?)";
	private static String GETALL = "SELECT * FROM Funcionario";
	private static String DELETEBYID = "DELETE FROM Funcionario WHERE Id = ?";
	private static String GETBYID = "SELECT * FROM Funcionario WHERE Id = ?";
	private static String UPDATEBYID = "UPDATE Funcionario SET Nome = ?, Email = ?, CPF = ?, Senha = ? WHERE Id = ?";
	
	public Employee insert(Employee employee) {
		try {
			CONNECTION = ConnectionFactory_Enterprise.openConnection();
			PreparedStatement stmt = CONNECTION.prepareStatement(INSERTINTO);
			stmt.setString(1, employee.getName());
			stmt.setString(2, employee.getEmail());
			stmt.setString(3, employee.getCPF());
			stmt.setString(4, employee.getPassword());
			stmt.executeUpdate();
			ConnectionFactory_Enterprise.closeConnection(CONNECTION);
			return employee;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<Employee> getAll() {
		try {
			CONNECTION = ConnectionFactory_Enterprise.openConnection();
			PreparedStatement stmt = CONNECTION.prepareStatement(GETALL);
			ResultSet resultSet = stmt.executeQuery();
			List<Employee> result = new ArrayList<Employee>();
			while(resultSet.next()) {
				result.add(new Employee(resultSet.getInt(1),
						resultSet.getString(2),
						resultSet.getString(3),
						resultSet.getString(4),
						resultSet.getString(5)));
			}
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
			CONNECTION = ConnectionFactory_Enterprise.openConnection();
			PreparedStatement stmt = CONNECTION.prepareStatement(DELETEBYID);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			ConnectionFactory_Enterprise.closeConnection(CONNECTION);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public Employee findById(int id) {
		try {
			CONNECTION = ConnectionFactory_Enterprise.openConnection();
			PreparedStatement stmt = CONNECTION.prepareStatement(GETBYID);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			Employee result = null;
			if(rs.next())
				result = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			ConnectionFactory_Enterprise.closeConnection(CONNECTION);
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void update(int id, String name, String email, String cpf, String password) {
		try {
			CONNECTION = ConnectionFactory_Agenda.openConnection();
			PreparedStatement stmt = CONNECTION.prepareStatement(UPDATEBYID);
			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.setString(3, cpf);
			stmt.setString(4, password);
			stmt.setInt(5, id);
			ConnectionFactory_Agenda.closeConnection(CONNECTION);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
