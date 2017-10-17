package br.sp.senai.informatica.noon.mvc.tasks;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.sp.senai.informatica.noon.data.ContactDAO;

public class RemoveContact implements ExecutableTask {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// Getting the Employee Id
		String strId = req.getParameter("id");
		int id = Integer.parseInt(strId);
		// Deleting the user from database
		ContactDAO c = new ContactDAO();
		c.delete(id);
		// Returning the updated page
		return "WEB-INF/jsp/ListContact.jsp";
	}

}
