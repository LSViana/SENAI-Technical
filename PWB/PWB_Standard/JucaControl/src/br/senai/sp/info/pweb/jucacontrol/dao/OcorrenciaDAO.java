package br.senai.sp.info.pweb.jucacontrol.dao;

import java.util.List;

import br.senai.sp.info.pweb.jucacontrol.models.BuscarPorSituacaoOcorrencia;
import br.senai.sp.info.pweb.jucacontrol.models.Ocorrencia;

public interface OcorrenciaDAO extends DAO<Ocorrencia> {
	
	public List<Ocorrencia> buscarPorSituacao(BuscarPorSituacaoOcorrencia bpso);
	
}
