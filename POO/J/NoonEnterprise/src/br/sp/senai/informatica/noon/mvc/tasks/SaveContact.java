package br.sp.senai.informatica.noon.mvc.tasks;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.sp.senai.informatica.noon.data.ContactDAO;
import br.sp.senai.informatica.noon.model.Contact;

public class SaveContact implements ExecutableTask {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String dateOfBirthday = req.getParameter("dateOfBirthday");

		ContactDAO dao = new ContactDAO();
		try {
			System.out.println(dateOfBirthday);
			dao.insert(new Contact(0, name, email, address, LocalDate.parse(dateOfBirthday)));
		} catch (Exception exc) {
			req.setAttribute("errorMessage", exc.getMessage());
			return null;
		}

		req.setAttribute("contactName", name);
		return "WEB-INF/jsp/InsertedContact.jsp";
	}

}
