package br.senai.sp.info.pweb.jucacontrol.models;

public enum BuscaPorSituacaoDeOcorrencia {
	
	TODOS("Todos"),
	
	/**
	 * Busca ocorrências que não possuem um técnico
	 */
	ABERTO("Aberto"),
	
	/**
	 * Busca ocorrências que não possuem data de conclusão do emissor
	 */
	EM_ATENDIMENTO("Em atendimento"),
	
	/**
	 * Busca ocorrências que possuem data de conclusão de emissor
	 */
	ENCERRADAS ("Encerrados");
	
	String descricao;
	
	private BuscaPorSituacaoDeOcorrencia(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
