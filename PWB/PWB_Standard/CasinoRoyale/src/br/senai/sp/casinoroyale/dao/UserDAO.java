package br.senai.sp.casinoroyale.dao;

import java.util.ArrayList;
import java.util.List;

import br.senai.sp.casinoroyale.model.User;

public class UserDAO {
	
	static {
		users = new ArrayList<User>();
		currentId = 0l;
	}
	
	public static Long currentId;
	public static List<User> users;
	
	public void addUser(User user) {
		user.setId(++currentId);
		users.add(user);
	}
	
	public User search(Long id) {
		//
		for(User user : users) {
			if(user.getId() == id)
				return user;
		}
		//
		return null;
	}
	
	public void delete(Long id) {
		users.remove(search(id));
	}

	public List<User> getAll() {
		return users;
	}
	
}