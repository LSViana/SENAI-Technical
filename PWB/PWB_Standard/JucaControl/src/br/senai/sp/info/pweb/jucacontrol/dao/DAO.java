package br.senai.sp.info.pweb.jucacontrol.dao;

import java.util.List;

public interface DAO<T> {
	
	public void persistir(T obj);
	
	public void alterar(T obj);
	
	public void deletar(T obj);
	
	public T buscar(Long id);
	
	public List<T> buscarTodos();

}
