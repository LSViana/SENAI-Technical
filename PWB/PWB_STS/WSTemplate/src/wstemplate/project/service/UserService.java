package wstemplate.project.service;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import wstemplate.project.dao.UserDAO;
import wstemplate.project.exceptions.BadRequestException;
import wstemplate.project.exceptions.EntityNotFoundException;
import wstemplate.project.exceptions.ValidationException;
import wstemplate.project.models.User;
import wstemplate.project.service.models.ChangeNames;
import wstemplate.project.service.models.ChangePassword;
import wstemplate.project.utils.PasswordUtils;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	public User changeNames(User obj, ChangeNames changeNames) throws ValidationException {
		String hashPassword = PasswordUtils.hashString(changeNames.getCurrentPassword());
		if(obj.getPassword().equals(hashPassword)) {
			obj.setFirstName(changeNames.getFreshFirstName());
			obj.setLastName(changeNames.getFreshLastName());
			userDAO.update(obj);
			return obj;
		}
		throw new ValidationException("Invalid password verification");
	}
	
	public User changePassword(User obj, ChangePassword ChangePassword) throws ValidationException {
		String hashPassword = PasswordUtils.hashString(ChangePassword.getCurrentPassword());
		if(obj.getPassword().equals(hashPassword)) {
			obj.setPassword(ChangePassword.getFreshPassword());
			obj.hashPassword();
			userDAO.update(obj);
			return obj;
		}
		throw new ValidationException("Invalid password verification");
	}

	public User create(User obj, BindingResult br) throws ValidationException {
		if(br.hasErrors())
			throw new ValidationException();
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
