package senai.sstorage.dao;

import senai.sstorage.models.Environment;

public interface EnvironmentDAO extends DAO<Environment> {
	
	public Environment searchByName(String name);

}
