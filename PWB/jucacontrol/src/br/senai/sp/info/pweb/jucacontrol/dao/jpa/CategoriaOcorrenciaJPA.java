package br.senai.sp.info.pweb.jucacontrol.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.info.pweb.jucacontrol.dao.CategoriaOcorrenciaDAO;
import br.senai.sp.info.pweb.jucacontrol.models.CategoriaOcorrencia;

@Repository
@Transactional
public class CategoriaOcorrenciaJPA implements CategoriaOcorrenciaDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void alterar(CategoriaOcorrencia obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public CategoriaOcorrencia buscar(Long id) {
		String hql = "FROM CategoriaOcorrencia c WHERE c.id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		
		//Executa e armazena o resultado
		List<CategoriaOcorrencia> resultado = query.list();
		
		if(!resultado.isEmpty()) {
			return resultado.get(0);
		}else {
			return null;
		}
	}

	@Override
	public CategoriaOcorrencia buscarPorNome(String nome) {
		String hql = "FROM CategoriaOcorrencia c WHERE c.nome = :nome";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("nome", nome);
		
		//Executa e armazena o resultado
		List<CategoriaOcorrencia> resultado = query.list();
		
		if(!resultado.isEmpty()) {
			return resultado.get(0);
		}else {
			return null;
		}
	}

	@Override
	public List<CategoriaOcorrencia> buscarTodos() {
		String hql = "FROM CategoriaOcorrencia";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void deletar(CategoriaOcorrencia obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public void persistir(CategoriaOcorrencia obj) {
		sessionFactory.getCurrentSession().persist(obj);
	}

}
