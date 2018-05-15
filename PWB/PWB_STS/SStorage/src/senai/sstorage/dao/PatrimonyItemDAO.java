package senai.sstorage.dao;

import java.util.List;

import senai.sstorage.models.PatrimonyItem;

public interface PatrimonyItemDAO extends DAO<PatrimonyItem> {
	
	void save(PatrimonyItem obj);
	
	List<PatrimonyItem> searchByPatrimony(Long id);

	List<PatrimonyItem> searchRemoveRequested();

	List<PatrimonyItem> searchRemoved();

	List<PatrimonyItem> searchActive();

}
