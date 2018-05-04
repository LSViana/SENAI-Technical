package senai.sstorage.dao.jpa;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import senai.sstorage.dao.MovementDAO;
import senai.sstorage.models.Movement;
import senai.sstorage.models.PatrimonyItem;

@Repository
@Transactional
public class MovementJPA implements MovementDAO {
	
	@Autowired
	private SessionFactory factory;

	@Override
	public void update(Movement obj) {
		factory.getCurrentSession().update(obj);
	}

	@Override
	public Movement search(Long id) {
		List<Movement> movs = factory.getCurrentSession().createQuery("FROM Movement m WHERE m.id = :id").setParameter("id", id).list();
		if(movs.size() > 0)
			return movs.get(0);
		return null;
	}

	@Override
	public List<Movement> searchAll() {
		return factory.getCurrentSession().createQuery("FROM Movement").list();
	}

	@Override
	public void delete(Movement obj) {
		factory.getCurrentSession().delete(obj);
	}

	@Override
	public void delete(Long id) {
		factory.getCurrentSession().delete(search(id));
	}

	@Override
	public void persist(Movement obj) {
		factory.getCurrentSession().persist(obj);
	}

	@Override
	public List<Movement> searchByPatrimonyItem(PatrimonyItem patrimonyItem) {
		return factory.getCurrentSession().createQuery("FROM Movement m WHERE m.patrimonyItem.id = :id").setParameter("id", patrimonyItem.getId()).list();
	}

}
