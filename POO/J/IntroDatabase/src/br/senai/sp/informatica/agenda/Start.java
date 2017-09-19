package br.senai.sp.informatica.agenda;

import java.sql.Connection;
import br.senai.sp.informatica.agenda.data.ConnectionFactory;

public class Start {
	private static Connection connection;
	public static void main(String[] args) {
		testingConnection();
	}
	
	private static void testingConnection() {
		connection = ConnectionFactory.openConnection();
	}
}