package br.sp.senai.informatica.noon.mvc.tasks;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.sp.senai.informatica.noon.data.ContactDAO;

public class UpdateContact implements ExecutableTask {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String dateOfBirthday = req.getParameter("dateOfBirthday");

		ContactDAO dao = new ContactDAO();
		try {
			dao.update(id, name, email, address, LocalDate.parse(dateOfBirthday));
		} catch (Exception exc) {
			req.setAttribute("errorMessage", exc.getMessage());
			return null;
		}
		
		return "mvc?task=ListContact";
	}

}
