package senai.sstorage.dao.jpa;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import senai.sstorage.dao.PatrimonyDAO;
import senai.sstorage.models.Patrimony;

@Repository
@Transactional
public class PatrimonyJPA implements PatrimonyDAO {
	
	@Autowired
	private SessionFactory factory;

	@Override
	public void update(Patrimony obj) {
		factory.getCurrentSession().update(obj);
	}

	@Override
	public Patrimony search(Long id) {
		List<Patrimony> pats = factory.getCurrentSession().createQuery("FROM Patrimony p WHERE p.id = :id").setParameter("id", id).list();
		if(pats.size() > 0)
			return pats.get(0);
		return null;
	}

	@Override
	public List<Patrimony> searchAll() {
		return factory.getCurrentSession().createQuery("FROM Patrimony").list();
	}

	@Override
	public void delete(Patrimony obj) {
		factory.getCurrentSession().delete(obj);
	}

	@Override
	public void delete(Long id) {
		factory.getCurrentSession().delete(search(id));
	}

	@Override
	public void persist(Patrimony obj) {
		factory.getCurrentSession().persist(obj);
	}

}
