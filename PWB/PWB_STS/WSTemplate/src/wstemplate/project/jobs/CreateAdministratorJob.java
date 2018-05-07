package wstemplate.project.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import wstemplate.project.dao.UserDAO;
import wstemplate.project.models.User;
import wstemplate.project.models.UserType;

@Component
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
		if(userDAO.searchByEmail(admin.getEmail()) == null) {
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
