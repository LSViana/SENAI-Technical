package todo.dao;

import todo.models.Nota;

public interface NotaDAO extends DAO<Nota> {
	public Nota buscarPorTitulo(String titulo);
}
