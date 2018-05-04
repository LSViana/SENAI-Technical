package senai.sstorage.dao.jpa;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import senai.sstorage.dao.PatrimonyCategoryDAO;
import senai.sstorage.models.PatrimonyCategory;

@Repository
@Transactional
public class PatrimonyCategoryJPA implements PatrimonyCategoryDAO {
	
	@Autowired
	private SessionFactory factory;

	@Override
	public void update(PatrimonyCategory obj) {
		factory.getCurrentSession().update(obj);
	}

	@Override
	public PatrimonyCategory search(Long id) {
		List<PatrimonyCategory> cats = factory.getCurrentSession().createQuery("FROM PatrimonyCategory pc WHERE pc.id = :id").setParameter("id", id).list();
		if(cats.size() > 0)
			return cats.get(0);
		return null;
	}

	@Override
	public List<PatrimonyCategory> searchAll() {
		return factory.getCurrentSession().createQuery("FROM PatrimonyCategory").list();
	}

	@Override
	public void delete(PatrimonyCategory obj) {
		factory.getCurrentSession().delete(obj);
	}

	@Override
	public void delete(Long id) {
		factory.getCurrentSession().delete(search(id));
	}

	@Override
	public void persist(PatrimonyCategory obj) {
		factory.getCurrentSession().persist(obj);
	}

}
