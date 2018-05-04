package sp.senai.ianestt3.dao.jpa;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import sp.senai.ianestt3.dao.DAO;

@Transactional
public abstract class AbstractJPA<T> implements DAO<T> {
	
	private SessionFactory factory;
	
	public Session getSession() {
		return this.factory.getCurrentSession();
	}

	@Override
	public void delete(T obj) {
		getSession().delete(obj);
	}

	@Override
	public void persist(T obj) {
		getSession().persist(obj);
	}

	@Override
	public void update(T obj) {
		getSession().update(obj);
	}

}
