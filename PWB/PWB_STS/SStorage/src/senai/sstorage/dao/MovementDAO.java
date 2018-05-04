package senai.sstorage.dao;

import java.util.List;

import senai.sstorage.models.Movement;
import senai.sstorage.models.PatrimonyItem;

public interface MovementDAO extends DAO<Movement> {
	
	List<Movement> searchByPatrimonyItem(PatrimonyItem patrimonyItem);

}
