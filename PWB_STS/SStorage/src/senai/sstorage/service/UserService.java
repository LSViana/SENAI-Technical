package senai.sstorage.service;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import senai.sstorage.dao.UserDAO;
import senai.sstorage.exceptions.BadRequestException;
import senai.sstorage.exceptions.EntityNotFoundException;
import senai.sstorage.exceptions.ValidationException;
import senai.sstorage.models.User;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;

	public User create(User obj, BindingResult br) throws ValidationException {
		if(br.hasErrors())
			throw new ValidationException();
		if(userDAO.searchByEmail(obj.getEmail()) != null) {
			br.addError(new FieldError("user", "email", "This e-mail is already in use"));
			throw new ValidationException();
		}
		userDAO.persist(obj);
		return obj;
	}

	public Collection<User> read() {
		return userDAO.searchAll();
	}

	public User read(Long id) throws EntityNotFoundException {
		User obj = userDAO.search(id);
		if(obj == null)
			throw new EntityNotFoundException("User not found");
		else
			return obj;
	}

	public User update(Long id, User obj, BindingResult br) throws ValidationException, EntityNotFoundException {
		if(br.hasErrors())
			throw new ValidationException();
		User fromDb = userDAO.search(id);
		if(fromDb == null)
			throw new EntityNotFoundException();
		BeanUtils.copyProperties(obj, fromDb, "id");
		userDAO.update(fromDb);
		return fromDb;
	}

	public void delete(Long id) throws EntityNotFoundException {
		User fromDb = userDAO.search(id);
		if(fromDb == null) {
			throw new EntityNotFoundException();
		}
		userDAO.delete(fromDb);
	}

	public User authenticate(@Valid User user) throws BadRequestException, EntityNotFoundException {
		if(user.getEmail() == null || user.getPassword() == null) {
			throw new BadRequestException();
		}
		User fromDb = userDAO.searchByEmail(user.getEmail());
		user.hashPassword();
		if(fromDb == null || !fromDb.getPassword().equals(user.getPassword())) {
			throw new EntityNotFoundException();
		}
		return fromDb;
	}

}
