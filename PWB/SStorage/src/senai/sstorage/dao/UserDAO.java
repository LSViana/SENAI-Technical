package senai.sstorage.dao;

import senai.sstorage.models.User;

public interface UserDAO extends DAO<User> {
	
	public User searchByEmail(String email);

}
