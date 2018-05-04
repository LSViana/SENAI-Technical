package senai.sstorage.service;

import java.util.Collection;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import senai.sstorage.dao.PatrimonyDAO;
import senai.sstorage.exceptions.EntityNotFoundException;
import senai.sstorage.exceptions.ValidationException;
import senai.sstorage.models.Patrimony;

@Service
public class PatrimonyService {

	@Autowired
	private PatrimonyDAO patrimonyDAO;
	
	public Patrimony create(Patrimony obj, BindingResult br) throws ValidationException {
		if(br.hasErrors()) {
			throw new ValidationException("Validation Exception");
		}
		patrimonyDAO.persist(obj);
		return obj;
	}
	
	public void delete(Long id) throws EntityNotFoundException {
		Patrimony obj = patrimonyDAO.search(id);
		if(obj == null)
			throw new EntityNotFoundException("Patrimony ID Not Found");
		patrimonyDAO.delete(obj);
	}
	
	public Collection<Patrimony> read() {
		return patrimonyDAO.searchAll();
	}
	
	public Patrimony read(Long id) throws EntityNotFoundException {
		Patrimony obj = patrimonyDAO.search(id);
		if(obj == null)
			throw new EntityNotFoundException("Patrimony ID Not Found");
		return obj;
	}
	
	public Patrimony update(Long id, Patrimony obj, BindingResult br) throws EntityNotFoundException, ValidationException {
		Patrimony fromDb = patrimonyDAO.search(id);
		if(fromDb == null)
			throw new EntityNotFoundException("Patrimony ID Not Found");
		if(br.hasErrors())
			throw new ValidationException("Validation Exception");
		BeanUtils.copyProperties(obj, fromDb, "id");
		patrimonyDAO.update(fromDb);
		return fromDb;
	}
	
}
