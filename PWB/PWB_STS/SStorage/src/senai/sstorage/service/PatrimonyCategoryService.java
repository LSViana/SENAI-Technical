package senai.sstorage.service;

import java.util.Collection;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import senai.sstorage.dao.PatrimonyCategoryDAO;
import senai.sstorage.exceptions.EntityNotFoundException;
import senai.sstorage.exceptions.ValidationException;
import senai.sstorage.models.PatrimonyCategory;

@Service
public class PatrimonyCategoryService {
	
	@Autowired
	private PatrimonyCategoryDAO patCatDAO;
	
	public PatrimonyCategory create(PatrimonyCategory patCat, BindingResult br) throws ValidationException {
		if(br.hasErrors())
			throw new ValidationException("Validation Exception");
		PatrimonyCategory fromDb = patCatDAO.searchByName(patCat.getName());
		if(fromDb != null)
		{
			br.addError(new FieldError("patrimonyCategory", "name", "Name already in use"));
			throw new ValidationException("Name already in use");
		}
		patCatDAO.persist(patCat);
		return patCat;
	}
	
	public Collection<PatrimonyCategory> read() {
		return patCatDAO.searchAll();
	}
	
	public PatrimonyCategory read(Long id) throws EntityNotFoundException {
		PatrimonyCategory fromDb = patCatDAO.search(id);
		if(fromDb == null)
			throw new EntityNotFoundException("ID not found");
		return fromDb;
	}
	
	public PatrimonyCategory update(Long id, PatrimonyCategory patCat, BindingResult br) throws ValidationException, EntityNotFoundException {
		if(br.hasErrors())
			throw new ValidationException("Validation Exception");
		PatrimonyCategory fromDb = patCatDAO.searchByName(patCat.getName());
		if(fromDb != null)
		{
			br.addError(new FieldError("patrimonyCategory", "name", "Name already in use"));
			throw new ValidationException("Name already in use");
		}
		fromDb = patCatDAO.search(id);
		if(fromDb == null) {
			throw new EntityNotFoundException("ID not found");
		}
		BeanUtils.copyProperties(patCat, fromDb, "id");
		patCatDAO.update(fromDb);
		return fromDb;
	}
	
	public void delete(Long id) throws EntityNotFoundException {
		PatrimonyCategory fromDb = patCatDAO.search(id);
		if(fromDb == null)
			throw new EntityNotFoundException("ID not found");
		patCatDAO.delete(fromDb);
	}

}
