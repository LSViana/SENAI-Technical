package senai.sstorage.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import senai.sstorage.dao.UserDAO;
import senai.sstorage.models.User;

@Repository
@Transactional
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

	@Override
	public User searchByEmailAndPassword(String email, String password) {
		String hql = "FROM User u WHERE u.email = :email AND u.password = :password";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter("email", email);
		query.setParameter("password", password);
		List<User> result = query.list();
		if(result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}

}
