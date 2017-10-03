package br.sp.senai.informatica.noon.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.sp.senai.informatica.noon.model.Employee;

public class EmployeeDAO {
	private static Connection CONNECTION;
	private static String INSERTINTO = "INSERT INTO Funcionario VALUES(0, ?, ?, ?, ?);";
	private static String GETALL = "SELECT * FROM Funcionario";
	public Employee insert(Employee employee) {
		try {
			CONNECTION = ConnectionFactory.openConnection();
			PreparedStatement stmt = CONNECTION.prepareStatement(INSERTINTO);
			stmt.setString(1, employee.getName());
			stmt.setString(2, employee.getEmail());
			stmt.setString(3, employee.getCPF());
			stmt.setString(4, employee.getPassword());
			stmt.executeUpdate();
			ConnectionFactory.closeConnection(CONNECTION);
			return employee;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<Employee> getAll() {
		try {
			CONNECTION = ConnectionFactory.openConnection();
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
}
