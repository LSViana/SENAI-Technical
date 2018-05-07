package senai.sstorage.dao.jpa;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import senai.sstorage.dao.EnvironmentDAO;
import senai.sstorage.models.Environment;

@Repository
@Transactional
public class EnvironmentJPA implements EnvironmentDAO {
	
	@Autowired
	private SessionFactory factory;
	
	@Override
	public void update(Environment obj) {
		factory.getCurrentSession().update(obj);
	}

	@Override
	public Environment search(Long id) {
		List<Environment> envs = factory.getCurrentSession().createQuery("FROM Environment e WHERE e.id = :id").setParameter("id", id).list();
		if(envs.size() > 0)
			return envs.get(0);
		return null;
	}

	@Override
	public List<Environment> searchAll() {
		return factory.getCurrentSession().createQuery("FROM Environment").list();
	}

	@Override
	public void delete(Environment obj) {
		factory.getCurrentSession().delete(obj);
	}

	@Override
	public void delete(Long id) {
		factory.getCurrentSession().delete(search(id));
	}

	@Override
	public void persist(Environment obj) {
		factory.getCurrentSession().persist(obj);
	}

	@Override
	public Environment searchByName(String name) {
		List<Environment> envs = factory.getCurrentSession().createQuery("FROM Environment e WHERE e.name = :name").setParameter("name", name).list();
		if(envs.size() > 0)
			return envs.get(0);
		return null;
	}

}
