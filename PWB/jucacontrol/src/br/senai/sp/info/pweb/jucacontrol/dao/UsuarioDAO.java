package br.senai.sp.info.pweb.jucacontrol.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.senai.sp.info.pweb.jucacontrol.models.Usuario;

public interface UsuarioDAO {
	
	public void alterar(Usuario obj);
	
	public Usuario buscar(Long id);
	
	public Usuario buscarPorEmail(String email);
	
	public Usuario buscarPorEmailESenha(String email, String senha);
	
	public List<Usuario> buscarTodos();
	
	public void deletar(Usuario obj);
	
	public void persistir(Usuario obj);
	
}
