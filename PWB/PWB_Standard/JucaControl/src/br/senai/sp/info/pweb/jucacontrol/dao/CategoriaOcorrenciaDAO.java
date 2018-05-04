package br.senai.sp.info.pweb.jucacontrol.dao;

import java.util.List;

import br.senai.sp.info.pweb.jucacontrol.models.CategoriaOcorrencia;
import br.senai.sp.info.pweb.jucacontrol.models.Usuario;

public interface CategoriaOcorrenciaDAO extends DAO<CategoriaOcorrencia> {
	
	public CategoriaOcorrencia buscarPorNome(String nome);

}
