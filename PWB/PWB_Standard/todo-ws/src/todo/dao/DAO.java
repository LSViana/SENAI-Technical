package todo.dao;

import java.util.List;

public interface DAO<T> {
	
	public T buscar(Long id);
	
	public List<T> buscar();
	
	public void alterar(T obj);
	
	public void deletar(T obj);
	
	public void inserit(T obj);

}
