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
		
		List<Usuario> resultados = query.list();
		
		//Pega o primeiro ou nulo
		if(!resultados.isEmpty()) {
			return resultados.get(0);
		}else {
			return null;
		}
	}

	@Override
	public List<Usuario> buscarTodos() {
		String hql = "FROM Usuario u";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		return query.list();
	}

	@Override
	public void deletar(Usuario obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public void persistir(Usuario obj) {
		sessionFactory.getCurrentSession().persist(obj);
	}

	@Override
	public Usuario buscarPorEmail(String email) {
		//HQL - Hibernate Query Language
		//Mistura elementos de orientação a objetos com SQL
		String hql = "FROM Usuario u WHERE u.email = :email";
		
		//Cria objeto que realiza buscas
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("email", email);
		
		//Executa e armazena o resultado
		List<Usuario> resultados = query.list();
		
		//Já que queremos um resultado, devemos realizar a seguinte tratativa...
		if(!resultados.isEmpty()) {
			return resultados.get(0);
		}else {
			return null;
		}
	}
	
	@Override
	public Usuario buscarPorEmailESenha(String email, String senha) {
		//HQL - Hibernate Query Language
		//Mistura elementos de orientação a objetos com SQL
		String hql = "FROM Usuario u WHERE u.email = :email AND u.senha = :senha";
		
		//Cria objeto que realiza buscas
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("email", email);
		query.setParameter("senha", senha);
		
		//Executa e armazena o resultado
		List<Usuario> resultados = query.list();
		
		//Já que queremos um resultado, devemos realizar a seguinte tratativa...
		if(!resultados.isEmpty()) {
			return resultados.get(0);
		}else {
			return null;
		}	
		
	}

}