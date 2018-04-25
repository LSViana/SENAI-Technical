package senai.sstorage.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import senai.sstorage.dao.UserDAO;
import senai.sstorage.models.User;

public class UserJPA implements UserDAO {
	
	@Autowired
	private SessionFactory factory;

	@Override
	public void update(User obj) {
		factory.getCurrentSession().update(obj);
	}

	@Override
	public User search(Long id) {
		String hql = "FROM User u WHERE u.id = :id";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		List<User> result = query.list();
		if(result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<User> searchAll() {
		String hql = "FROM User";
		return factory.getCurrentSession().createQuery(hql).list();
	}

	@Override
	public void delete(User obj) {
		factory.getCurrentSession().delete(obj);
	}

	@Override
	public void persist(User obj) {
		factory.getCurrentSession().persist(obj);
	}

	@Override
	public User searchByEmail(String email) {
		String hql = "FROM User u WHERE u.email = :email";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter("email", email);
		List<User> result = query.list();
		if(result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}

}
