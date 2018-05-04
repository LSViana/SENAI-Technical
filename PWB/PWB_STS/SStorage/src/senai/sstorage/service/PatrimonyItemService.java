package senai.sstorage.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import senai.sstorage.dao.PatrimonyItemDAO;
import senai.sstorage.exceptions.EntityNotFoundException;
import senai.sstorage.exceptions.NotAllowedException;
import senai.sstorage.exceptions.ValidationException;
import senai.sstorage.models.PatrimonyItem;

@Service
public class PatrimonyItemService {
	
	@Autowired
	private PatrimonyItemDAO piDAO;
	
	public PatrimonyItem create(PatrimonyItem obj, BindingResult br) throws ValidationException {
		if(br.hasErrors())
			throw new ValidationException("Validation Exception");
		piDAO.persist(obj);
		return obj;
	}
	
	public Collection<PatrimonyItem> read() {
		return piDAO.searchAll();
	}
	
	public PatrimonyItem read(Long id) throws EntityNotFoundException {
		PatrimonyItem fromDb = piDAO.search(id);
		if(fromDb == null)
			throw new EntityNotFoundException("ID not found");
		return fromDb;
	}
	
	public PatrimonyItem update(Long id, PatrimonyItem obj, BindingResult br) throws NotAllowedException {
		throw new NotAllowedException("This operation is not allowed");
//		if(br.hasErrors())
//			throw new ValidationException("Validation Exception");
//		PatrimonyItem fromDb = piDAO.search(id);
//		if(fromDb == null)
//			throw new EntityNotFoundException("ID not found");
//		BeanUtils.copyProperties(obj, fromDb, "id");
//		piDAO.update(fromDb);
//		return fromDb;
	}
	
	public void delete(Long id) throws EntityNotFoundException {
		PatrimonyItem fromDb = piDAO.search(id);
		if(fromDb == null)
			throw new EntityNotFoundException("ID not found");
		piDAO.delete(fromDb);
	}

}
