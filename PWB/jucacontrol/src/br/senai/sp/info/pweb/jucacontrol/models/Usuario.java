package br.senai.sp.info.pweb.jucacontrol.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.DigestUtils;

@Entity
@Table(name = "usuario")
public class Usuario {
	
	@Id
	//Determina que o campo id é AUTO_INCREMENT
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private TiposUsuario tipo = TiposUsuario.COMUM;
	
	@Column(length = 30, nullable = false, unique = false)
	@NotNull
	@Size(min = 1, max = 30)
	private String nome;
	
	@Column(length = 50, nullable = false, unique = false)
	@NotNull
	@Size(min = 1, max = 50)
	private String sobrenome;
	
	@Column(length = 120, nullable = false, unique = true)
	@Email //DEve ser um email valido
	@NotNull
	@Size(min = 1, max = 120)
	private String email;
	
	@Column(length = 64, nullable = false, unique = false)
	@NotNull
	@Size(min = 5, max = 64)
	private String senha;
	
	@Transient
	private String caminhoFoto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TiposUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TiposUsuario tipo) {
		this.tipo = tipo;
	}
	
	public void hashearSenha() {
		this.senha = DigestUtils.md5DigestAsHex(this.senha.getBytes());
	}
	
	public String getCaminhoFoto() {
		return caminhoFoto;
	}
	
	public void setCaminhoFoto(String caminhoFoto) {
		this.caminhoFoto = caminhoFoto;
	}
	
	public boolean getAdministrador() {
		return this.tipo.equals(TiposUsuario.ADMINISTRADOR);
	}
}
