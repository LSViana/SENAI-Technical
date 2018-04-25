package senai.sstorage.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import senai.sstorage.dao.UserDAO;
import senai.sstorage.models.User;
import senai.sstorage.models.UserType;

public class CreateAdministratorJob implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent e) {
		logJob("Creating administrator.");
		//
		User admin = new User();
		admin.setEmail("admin@email.com");
		admin.setPassword("admin");
		admin.setFirstName("Administrator");
		admin.setLastName("System");
		admin.setType(UserType.ADMINISTRATOR);
		admin.hashPassword();
		//
		logJob("Verifying Administrator existence...");
		if(userDAO.searchByEmail(admin.getEmail()) != null) {
			logJob("Creating Administrator...");
			userDAO.persist(admin);
		} else {
			logJob("Administrator already exists.");
		}
		logJob("Administrator ready to use.");
	}
	
	private void logJob(String message) {
		message = message.trim();
		System.out.printf("[JOB]: %s\n", message);
	}

}
