package senai.sstorage.service;

import java.util.Collection;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import senai.sstorage.dao.EnvironmentDAO;
import senai.sstorage.exceptions.EntityNotFoundException;
import senai.sstorage.exceptions.ValidationException;
import senai.sstorage.models.Environment;

@Service
public class EnvironmentService {
	
	@Autowired
	private EnvironmentDAO envDAO;
	
	public Environment create(Environment env, BindingResult br) throws ValidationException {
		if(br.hasErrors())
			throw new ValidationException("Validation Exception");
		if(envDAO.searchByName(env.getName()) != null) 
			throw new ValidationException("Duplicated Name");
		envDAO.persist(env);
		return env;
	}
	
	public Collection<Environment> read() {
		return envDAO.searchAll();
	}
	
	public Environment read(Long id) throws EntityNotFoundException {
		Environment obj = envDAO.search(id);
		if(obj == null)
			throw new EntityNotFoundException("ID not found");
		return obj;
	}
	
	public Environment update(Long id, Environment env, BindingResult br) throws ValidationException, EntityNotFoundException {
		if(br.hasErrors())
			throw new ValidationException("Validation Exception");
		Environment fromDb = envDAO.search(id);
		if(envDAO.searchByName(env.getName()) != null) 
			throw new ValidationException("Duplicated Name");
		if(fromDb == null)
			throw new EntityNotFoundException("ID not found");
		BeanUtils.copyProperties(env, fromDb, "id");
		envDAO.update(fromDb);
		return fromDb;
	}
	
	public void delete(Long id) throws EntityNotFoundException {
		Environment env = envDAO.search(id);
		if(env == null)
			throw new EntityNotFoundException("ID not found");
		envDAO.delete(env);
	}

}
