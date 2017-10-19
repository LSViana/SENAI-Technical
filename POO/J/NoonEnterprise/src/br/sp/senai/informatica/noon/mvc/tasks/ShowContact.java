package br.sp.senai.informatica.noon.mvc.tasks;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.sp.senai.informatica.noon.data.ContactDAO;
import br.sp.senai.informatica.noon.model.Contact;

public class ShowContact implements ExecutableTask {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		ContactDAO dao = new ContactDAO();
		Contact employee = dao.findById(id);
		req.setAttribute("id", id);
		req.setAttribute("name", employee.getName());
		req.setAttribute("email", employee.getEmail());
		req.setAttribute("address", employee.getAddress());
		req.setAttribute("dateOfBirthday", employee.getDateOfBirthday());
		return "WEB-INF/jsp/ShowContact.jsp";
	}

}
