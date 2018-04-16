package br.senai.sp.info.pweb.jucacontrol.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.senai.sp.info.pweb.jucacontrol.models.Usuario;

public interface UsuarioDAO extends DAO<Usuario> {
	
	public Usuario buscarPorEmail(String email);
	
	public Usuario buscarPorEmailESenha(String email, String senha);
	
	
}
