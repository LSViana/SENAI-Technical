package br.sp.senai.informatica.noon.mvc.tasks;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.sp.senai.informatica.noon.data.EmployeeDAO;

public class UpdateEmployee implements ExecutableTask {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String cpf = req.getParameter("cpf");
		String password = req.getParameter("password");
		
		EmployeeDAO dao = new EmployeeDAO();
		try {
			dao.update(id, name, email, cpf, password);
		} catch (Exception exc) {
			req.setAttribute("errorMessage", exc.getMessage());
			return null;
		}
		
		return "mvc?task=ListEmployee";
	}

}
