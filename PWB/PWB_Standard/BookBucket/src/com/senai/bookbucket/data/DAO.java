package com.senai.bookbucket.data;

import java.util.List;

public interface DAO<T> {
	public T search(Long id);
	public List<T> searchAll();
	public void delete(T obj);
	public void persist(T obj);
}
