package br.senai.sp.info.pweb.jucacontrol.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ocorrencia")
public class Ocorrencia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "categoria_id")
	@NotNull
	private CategoriaOcorrencia categoria;
	
	@ManyToOne
	@JoinColumn(name = "emissor_id", nullable = false)
	private Usuario emissor;
	
	@ManyToOne
	@JoinColumn(name = "tecnico_id", nullable = true)
	private Usuario tecnico;
	
	@Column(length = 30, nullable = false, unique = false)
	@Size(min=1, max=30)
	@NotNull
	private String titulo;
	
	@Column(nullable = false, unique = false)
	@Lob // Long Object, changes from VARCHAR(n) to TEXT at database
	@NotNull
	private String descricao;
	
	@Column(nullable = false)
	private Date dataCadastro;
	
	@Column(nullable = false)
	private Date dataModificacao;

	@Column(nullable = true)
	private Date dataConclusao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getEmissor() {
		return emissor;
	}

	public void setEmissor(Usuario emissor) {
		this.emissor = emissor;
	}

	public Usuario getTecnico() {
		return tecnico;
	}

	public void setTecnico(Usuario tecnico) {
		this.tecnico = tecnico;
	}

	public CategoriaOcorrencia getCategoria() {
		return categoria;
	}
	
	public void setCategoria(CategoriaOcorrencia categoria) {
		this.categoria = categoria;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public Date getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(Date dataModificacao) {
		this.dataModificacao = dataModificacao;
	}

	@Override
	public String toString() {
		return "Ocorrencia [id=" + id + ", categoria=" + categoria + ", emissor=" + emissor + ", tecnico=" + tecnico
				+ ", titulo=" + titulo + ", descricao=" + descricao + ", dataCadastro=" + dataCadastro
				+ ", dataModificacao=" + dataModificacao + ", dataConclusao=" + dataConclusao + "]";
	}
	
}
