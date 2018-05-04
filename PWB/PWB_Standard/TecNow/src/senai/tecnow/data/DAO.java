package senai.tecnow.data;

import java.util.List;

public interface DAO<T> {
	
	public List<T> searchAll();
	public T get(Long id);
	public boolean persist(T obj);
	public boolean delete(T obj);
	public boolean exists(T obj);
	
}
