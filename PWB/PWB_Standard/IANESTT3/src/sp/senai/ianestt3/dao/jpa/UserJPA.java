package sp.senai.ianestt3.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sp.senai.ianestt3.dao.UserDAO;
import sp.senai.ianestt3.model.User;

@Repository("UserDAO")
public class UserJPA extends AbstractJPA<User> implements UserDAO {

	@Override
	public List<User> getAll() {
		Query query = getSession().createQuery("SELECT u.name FROM User u");
		getSession().createSQLQuery("").list();
		return query.list();
	}

	@Override
	public User searchById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
