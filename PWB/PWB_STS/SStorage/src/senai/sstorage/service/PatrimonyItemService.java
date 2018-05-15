package senai.sstorage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import senai.sstorage.dao.EnvironmentDAO;
import senai.sstorage.dao.PatrimonyDAO;
import senai.sstorage.dao.PatrimonyItemDAO;
import senai.sstorage.exceptions.EntityNotFoundException;
import senai.sstorage.exceptions.NotAllowedException;
import senai.sstorage.exceptions.ValidationException;
import senai.sstorage.models.ItemState;
import senai.sstorage.models.PatrimonyItem;

@Service
public class PatrimonyItemService {
	
	@Autowired
	private PatrimonyItemDAO piDAO;
	
	public PatrimonyItem create(PatrimonyItem obj, BindingResult br) throws ValidationException {
		if(br.hasErrors())
			throw new ValidationException("Validation Exception");
		// Verifying if the supplied ID already exists
		PatrimonyItem fromDb = piDAO.search(obj.getId());
		if(fromDb != null) {
			br.addError(new FieldError("environment", "id", "ID already in use"));
			throw new ValidationException("ID already in use");
		}
		// Updating Last Movement Date
		obj.setLastMovement(new Date());
		//
		piDAO.save(obj);
		return obj;
	}
	
	public List<PatrimonyItem> read() {
		return piDAO.searchActive();
	}
	
	public PatrimonyItem read(Long id) throws EntityNotFoundException {
		PatrimonyItem fromDb = piDAO.search(id);
		if(fromDb == null)
			throw new EntityNotFoundException("ID not found");
		return fromDb;
	}
	
	public List<PatrimonyItem> searchByPatrimony(Long patrimonyId) {
		return piDAO.searchByPatrimony(patrimonyId);
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

	public List<PatrimonyItem> readRemoveRequested() {
		return piDAO.searchRemoveRequested();
	}
	
	public List<PatrimonyItem> readRemoved() {
		return piDAO.searchRemoved();
	}

	public PatrimonyItem requestRemoval(Long id) throws EntityNotFoundException {
		PatrimonyItem fromDb = piDAO.search(id);
		if(fromDb == null)
			throw new EntityNotFoundException("ID not found");
		fromDb.setState(ItemState.REMOVE_REQUESTED);
		piDAO.update(fromDb);
		return fromDb;
	}
	
	public PatrimonyItem remove(Long id) throws EntityNotFoundException {
		PatrimonyItem fromDb = piDAO.search(id);
		if(fromDb == null)
			throw new EntityNotFoundException("ID not found");
		fromDb.setState(ItemState.REMOVED);
		piDAO.update(fromDb);
		return fromDb;
	}

	public Object activate(Long id) throws EntityNotFoundException {
		PatrimonyItem fromDb = piDAO.search(id);
		if(fromDb == null)
			throw new EntityNotFoundException("ID not found");
		fromDb.setState(ItemState.ACTIVE);
		piDAO.update(fromDb);
		return fromDb;
	}

}
