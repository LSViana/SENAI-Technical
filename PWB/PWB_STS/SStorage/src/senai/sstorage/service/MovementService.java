package senai.sstorage.service;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import senai.sstorage.dao.MovementDAO;
import senai.sstorage.dao.PatrimonyItemDAO;
import senai.sstorage.models.Environment;
import senai.sstorage.models.Movement;
import senai.sstorage.models.PatrimonyItem;
import senai.sstorage.models.User;

@Service
public class MovementService {
	
	@Autowired
	private MovementDAO movDAO;
	
	@Autowired
	private PatrimonyItemDAO patDAO;
	
	public Movement movement(PatrimonyItem patrimonyItem, Environment origin, Environment destiny, User user) {
		Movement mov = new Movement();
		// Attributes
		mov.setDateTime(new Date());
		mov.setDestinyEnvironment(destiny);
		mov.setOriginEnvironment(origin);
		mov.setPatrimonyItem(patrimonyItem);
		mov.setUser(user);
		// Saving to database
		movDAO.persist(mov);
		// Updating last movement time at PatrimonyItem
		patrimonyItem.setLastMovement(new Date());
		patDAO.update(patrimonyItem);
		// Returning the movement object
		return mov;
	}
	
	public Collection<Movement> searchByPatrimonyItem(PatrimonyItem patrimonyItem) {
		return movDAO.searchByPatrimonyItem(patrimonyItem);
	}
	
	public Movement read(Long id) {
		return movDAO.search(id);
	}

}
