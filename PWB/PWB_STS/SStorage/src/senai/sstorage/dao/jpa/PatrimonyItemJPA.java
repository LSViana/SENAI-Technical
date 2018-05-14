package senai.sstorage.dao.jpa;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import senai.sstorage.dao.PatrimonyItemDAO;
import senai.sstorage.models.PatrimonyItem;

@Repository
@Transactional
public class PatrimonyItemJPA implements PatrimonyItemDAO {

	@Autowired
	private SessionFactory factory;

	@Override
	public void update(PatrimonyItem obj) {
		throw new IllegalAccessError();
	}

	@Override
	public PatrimonyItem search(Long id) {
		List<PatrimonyItem> patItems = factory.getCurrentSession()
				.createQuery("FROM PatrimonyItem pi WHERE pi.id = :id").setParameter("id", id).list();
		if (patItems.size() > 0)
			return patItems.get(0);
		return null;
	}

	@Override
	public List<PatrimonyItem> searchAll() {
		return factory.getCurrentSession().createQuery("FROM PatrimonyItem").list();
	}

	@Override
	public void delete(PatrimonyItem obj) {
		factory.getCurrentSession().delete(obj);
	}

	@Override
	public void delete(Long id) {
		factory.getCurrentSession().delete(search(id));
	}

	@Override
	public void persist(PatrimonyItem obj) {
		factory.getCurrentSession().persist(obj);
	}

	@Override
	public void save(PatrimonyItem obj) {
		factory.getCurrentSession().save(obj);
	}

	@Override
	public List<PatrimonyItem> searchByPatrimony(Long id) {
		return factory.getCurrentSession().createQuery("FROM PatrimonyItem pi WHERE pi.patrimony.id = :id")
				.setParameter("id", id).list();
	}

}
