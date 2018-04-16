package br.senai.sp.info.pweb.jucacontrol.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.info.pweb.jucacontrol.dao.OcorrenciaDAO;
import br.senai.sp.info.pweb.jucacontrol.models.Ocorrencia;

@Repository
@Transactional
public class OcorrenciaJPA implements OcorrenciaDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void alterar(Ocorrencia obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public Ocorrencia buscar(Long id) {
		String hql = "FROM Ocorrencia o WHERE o.id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		
		// Executa e armazena o resultado
		List<Ocorrencia> resultado = query.list();
		
		if(!resultado.isEmpty()) {
			return resultado.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Ocorrencia> buscarTodos() {
		String hql = "FROM Ocorrencia";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void deletar(Ocorrencia obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public void persistir(Ocorrencia obj) {
		sessionFactory.getCurrentSession().persist(obj);
	}

}
