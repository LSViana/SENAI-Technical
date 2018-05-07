package wstemplate.project.dao;

import org.springframework.stereotype.Repository;

import wstemplate.project.models.User;

@Repository
public interface UserDAO extends DAO<User> {
	
	public User searchByEmail(String email);
	
	public User searchByEmailAndPassword(String email, String password);

}
