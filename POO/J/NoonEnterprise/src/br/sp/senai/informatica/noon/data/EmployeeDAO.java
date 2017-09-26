package br.sp.senai.informatica.noon.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import br.sp.senai.informatica.noon.model.Employee;

public class EmployeeDAO {
	private static Connection CONNECTION;
	private static String INSERTINTO = "INSERT INTO Funcionario VALUES(0, ?, ?, ?);";
	public static Employee insert(Employee employee) {
		try {
			CONNECTION = ConnectionFactory.openConnection();
			PreparedStatement stmt = CONNECTION.prepareStatement(INSERTINTO);
			stmt.setString(1, employee.getName());
			stmt.setString(2, employee.getEmail());
			stmt.setString(3, employee.getPassword());
			stmt.executeUpdate();
			ConnectionFactory.closeConnection(CONNECTION);
			return employee;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
