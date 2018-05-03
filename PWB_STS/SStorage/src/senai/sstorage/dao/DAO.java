package senai.sstorage.dao;

import java.util.List;

public interface DAO<T> {
	
	public void update(T obj);
	
	public T search(Long id);
	
	public List<T> searchAll();
	
	public void delete(T obj);
	
	public void delete(Long id);
	
	public void persist(T obj);

}
