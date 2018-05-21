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
import senai.sstorage.service.models.ChangeNames;
import senai.sstorage.service.models.ChangePassword;
import senai.sstorage.utils.PasswordUtils;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	public User changeNames(User obj, ChangeNames changeNames, BindingResult br) throws ValidationException, EntityNotFoundException {
		Integer freshFirstNameLength = changeNames.getFirstName().length(), freshLastNameLength = changeNames.getLastName().length();
		if(freshFirstNameLength < 1 || freshFirstNameLength > 20) {
			br.addError(new FieldError("changeNames", "firstName", "size must be between 1 and 20"));
			throw new ValidationException("size must be between 1 and 20");
		}
		if(freshLastNameLength < 1 || freshLastNameLength > 40) {
			br.addError(new FieldError("changeNames", "lastName", "size must be between 1 and 40"));
			throw new ValidationException("size must be between 1 and 40");
		}
		//
		String hashPassword = PasswordUtils.hashString(changeNames.getPassword());
		if(obj.getPassword().equals(hashPassword)) {
			obj.setFirstName(changeNames.getFirstName());
			obj.setLastName(changeNames.getLastName());
			return update(obj.getId(), obj, br);
		}  
		br.addError(new FieldError("changeNames", "password", "Invalid password verification"));
		throw new ValidationException("Invalid password verification");
	}
	
	public User changePassword(User obj, ChangePassword changePassword, BindingResult br) throws ValidationException, EntityNotFoundException {
		Integer freshPasswordLength = changePassword.getPassword().length();
		if(freshPasswordLength < 6 || freshPasswordLength > 20) {
			br.addError(new FieldError("changePassword", "password", "size must be between 6 and 20"));
			throw new ValidationException("size must be between 6 and 20");
		}
		//
		String hashPassword = PasswordUtils.hashString(changePassword.getCurrentPassword());
		if(obj.getPassword().equals(hashPassword)) {
			obj.setPassword(changePassword.getPassword());
			return updateWithHash(obj.getId(), obj, br);
		}
		br.addError(new FieldError("changePassword", "currentPassword", "Invalid password verification"));
		throw new ValidationException("Invalid password verification");
	}

	public User create(User obj, BindingResult br) throws ValidationException {
		if(br.hasErrors())
			throw new ValidationException();
		if(obj.getPassword().length() > 20) {
			br.addError(new FieldError("user", "password", "size must be between 6 and 20"));
			throw new ValidationException("size must be between 6 and 20");
		}
		obj.hashPassword();
		if(userDAO.searchByEmail(obj.getEmail()) != null) {
			br.addError(new FieldError("user", "email", "E-mail already in use"));
			throw new ValidationException("E-mail already in use");
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
			throw new ValidationException("Validation exception");
		if(obj.getPassword().length() > 20) {
			br.addError(new FieldError("user", "password", "size must be between 6 and 20"));
			throw new ValidationException("size must be between 6 and 20");
		}
		User fromDb = userDAO.search(id);
		if(fromDb == null)
			throw new EntityNotFoundException();
		fromDb = userDAO.searchByEmail(obj.getEmail());
		if(fromDb != null && fromDb.getId() != id) {
			br.addError(new FieldError("user", "email", "E-mail already in use"));
			throw new ValidationException("E-mail already in use");
		}
		fromDb = userDAO.search(id);
		BeanUtils.copyProperties(obj, fromDb, "id");
		userDAO.update(fromDb);
		return fromDb;
	}
	
	public User updateWithHash(Long id, User obj, BindingResult br) throws ValidationException, EntityNotFoundException {
		if(br.hasErrors())
			throw new ValidationException("Validation exception");
		if(obj.getPassword().length() > 20) {
			br.addError(new FieldError("user", "password", "size must be between 6 and 20"));
			throw new ValidationException("size must be between 6 and 20");
		}
		obj.hashPassword();
		User fromDb = userDAO.search(id);
		if(fromDb == null)
			throw new EntityNotFoundException();
		fromDb = userDAO.searchByEmail(obj.getEmail());
		if(fromDb != null && fromDb.getId() != id) {
			br.addError(new FieldError("user", "email", "E-mail already in use"));
			throw new ValidationException("E-mail already in use");
		}
		fromDb = userDAO.search(id);
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
