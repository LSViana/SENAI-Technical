package br.senai.sp.info.pweb.jucacontrol.dao;

import java.util.List;

import br.senai.sp.info.pweb.jucacontrol.models.CategoriaOcorrencia;
import br.senai.sp.info.pweb.jucacontrol.models.Usuario;

public interface CategoriaOcorrenciaDAO {
	
	public void alterar(CategoriaOcorrencia obj);
	
	public CategoriaOcorrencia buscar(Long id);
	
	public CategoriaOcorrencia buscarPorNome(String nome);
	
	public List<CategoriaOcorrencia> buscarTodos();
	
	public void deletar(CategoriaOcorrencia obj);
	
	public void persistir(CategoriaOcorrencia obj);
}
