package sp.senai.ianestt3.dao;

import java.util.List;

public interface DAO<T> {
	
	public void delete(T obj);
	
	public List<T> getAll();
	
	public void persist(T obj);

	public T searchById(Long id);
	
	public void update(T obj);

}
