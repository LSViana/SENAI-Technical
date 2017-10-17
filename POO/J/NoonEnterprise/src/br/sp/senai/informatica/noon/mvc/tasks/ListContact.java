package br.sp.senai.informatica.noon.mvc.tasks;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.sp.senai.informatica.noon.data.ContactDAO;
import br.sp.senai.informatica.noon.model.Contact;

public class ListContact implements ExecutableTask {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<Contact> contacts = new ContactDAO().getAll();
		req.setAttribute("contacts", contacts);
		return "WEB-INF/jsp/ListContacts.jsp";
	}

}
