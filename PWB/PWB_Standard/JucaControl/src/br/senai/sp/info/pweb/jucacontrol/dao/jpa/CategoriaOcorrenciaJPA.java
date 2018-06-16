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
	private SessionFactory session;
	

	@Override
	public void alterar(CategoriaOcorrencia obj) {
		session.getCurrentSession().update(obj);
	}

	@Override
	public CategoriaOcorrencia buscar(Long id) {
		String hql = "FROM CategoriaOcorrencia co WHERE co.id = :id";
		Query query = session.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		
		List<CategoriaOcorrencia> resultados = query.list();
		
		if(!resultados.isEmpty()) {
			return resultados.get(0);
		}else {
			return null;
		}
	}

	@Override
	public List<CategoriaOcorrencia> buscarTodos() {
		String hql = "FROM CategoriaOcorrencia";
		Query query = session.getCurrentSession().createQuery(hql);
		
		return query.list();
	}

	@Override
	public void deletar(CategoriaOcorrencia obj) {
		session.getCurrentSession().delete(obj);
	}

	@Override
	public void persistir(CategoriaOcorrencia obj) {
		session.getCurrentSession().persist(obj);
	}

	@Override
	public CategoriaOcorrencia buscarPorNome(String nome) {
		String hql = "FROM CategoriaOcorrencia co WHERE co.nome = :nome";
		Query query = session.getCurrentSession().createQuery(hql);
		query.setParameter("nome", nome);
		
		List<CategoriaOcorrencia> resultados = query.list();
		
		if(!resultados.isEmpty()) {
			return resultados.get(0);
		}else {
			return null;
		}
	}

}
