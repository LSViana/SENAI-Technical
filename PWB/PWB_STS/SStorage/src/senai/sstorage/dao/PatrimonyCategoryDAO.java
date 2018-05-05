package senai.sstorage.dao;

import senai.sstorage.models.PatrimonyCategory;

public interface PatrimonyCategoryDAO extends DAO<PatrimonyCategory> {
	
	public PatrimonyCategory searchByName(String name);

}
