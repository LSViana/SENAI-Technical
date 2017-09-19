package br.senai.sp.informatica.enterprise;

import java.sql.Connection;
import java.sql.SQLException;

import br.senai.sp.informatica.agenda.data.ConnectionFactory;

public class Start {
	private static Connection connection;
	public static void main(String[] args) {
		createEnterpriseSchema();
	}
	private static void createEnterpriseSchema() {
		connection = ConnectionFactory.openConnection();
		String sqlSchemaStat = "CREATE SCHEMA Enterprise DEFAULT CHARACTER SET utf8; "; // /*USE Enterprise;*
		String sqlUseStat = "USE Enterprise";
		String sqlTableStat = "CREATE TABLE Employee (Id INT PRIMARY KEY AUTO_INCREMENT, FullName VARCHAR(255), UserName VARCHAR(255), UserPassword VARCHAR(255), CONSTRAINT UNIQUE(UserName, UserPassword));";
		try {
			connection.createStatement().execute(sqlSchemaStat);
			connection.createStatement().execute(sqlUseStat);
			connection.createStatement().execute(sqlTableStat);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
