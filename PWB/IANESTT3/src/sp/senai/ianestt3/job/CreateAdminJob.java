package sp.senai.ianestt3.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import sp.senai.ianestt3.dao.UserDAO;
import sp.senai.ianestt3.model.User;
import sp.senai.ianestt3.model.UserType;

public class CreateAdminJob implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserDAO UserDAO;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent cre) {
		String adminEmail = "admin@admin.com";
		User admin = new User();
		admin.setEmail(adminEmail);
		admin.setFirstName("Administrador");
		admin.setLastName("do Sistema");
		admin.setPassword("admin");
		admin.setType(UserType.ADMINISTRATOR);
		admin.hashPassword();
		UserDAO.persist(admin);
	}

}