package br.senai.sp.info.pweb.jucacontrol.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.info.pweb.jucacontrol.dao.UsuarioDAO;
import br.senai.sp.info.pweb.jucacontrol.models.Usuario;

@Repository
@Transactional
public class UsuarioJPA implements UsuarioDAO{
	
	//Objeto que utilizamos para manipular o banco de dados atraves do HIbernate
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void alterar(Usuario obj) {
		sessionFactory.getCurrentSession().update(obj);		
	}

	@Override
	public Usuario buscar(Long id) {
		String hql = "FROM Usuario u WHERE u.id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		
		//Pegando o primeiro resultado
		List<Usuario> resultado = query.list();
		
		if(!resultado.isEmpty()) {
			return resultado.get(0);
		}else {
			return null;
		}
		
	}

	@Override
	public List<Usuario> buscarTodos() {
		String hql = "FROM Usuario";
		Query query  = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void deletar(Usuario usuario) {
		sessionFactory.getCurrentSession().delete(usuario);		
	}

	@Override
	 //Determina que este método precisa ter uma transação aberta antes de ser chamado
	public void persistir(Usuario obj) {
		sessionFactory.getCurrentSession().persist(obj);
	}

	@Override
	public Usuario buscarPorEmail(String email) {
		//HQL - Hibernate Query Language
		//Linguagem que mistura elementos orientado a objetos e SQL
		String hql = "FROM Usuario u WHERE u.email = :email";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		//Trocando os valores dos coringas
		query.setParameter("email", email);
		
		//Disparada a busca e guarda o resultado em lista
		List<Usuario> resultado =  query.list();
		
		
		//Ja que queremos apenas um objeto, devemos verificar se a lista NÃO é vazia
		if(!resultado.isEmpty()) {
			return resultado.get(0);
		}else {
			return null;
		}
		
	}

	@Override
	public Usuario buscarPorEmailESenha(String email, String senha) {
		//HQL - Hibernate Query Language
		//Linguagem que mistura elementos orientado a objetos e SQL
		String hql = "FROM Usuario u WHERE u.email = :email AND u.senha = :senha";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		//Trocando os valores dos coringas
		query.setParameter("email", email);
		query.setParameter("senha", senha);
		
		//Disparada a busca e guarda o resultado em lista
		List<Usuario> resultado =  query.list();
		
		
		//Ja que queremos apenas um objeto, devemos verificar se a lista NÃO é vazia
		if(!resultado.isEmpty()) {
			return resultado.get(0);
		}else {
			return null;
		}
	}

}
