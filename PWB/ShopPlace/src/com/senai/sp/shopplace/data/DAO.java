package com.senai.sp.shopplace.data;

import java.sql.Connection;
import java.util.List;

import com.senai.sp.shopplace.data.mysql.ConnectionFactory;

public abstract class DAO<T> {
	
	protected Connection connection;
	public abstract void insert(T obj);
	public abstract void delete(T obj);
	public abstract void update(T obj);
	public abstract T get(Long id);
	public abstract List<T> getAll();
	protected void openConnection() {
		connection = ConnectionFactory.openConnnection();
	}
	protected void closeConnection() {
		ConnectionFactory.closeConnection(connection);
	}

}
