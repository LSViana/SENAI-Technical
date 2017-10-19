package br.sp.senai.informatica.noon.mvc.tasks;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.sp.senai.informatica.noon.data.EmployeeDAO;
import br.sp.senai.informatica.noon.model.Employee;

public class SaveEmployee implements ExecutableTask {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String cpf = req.getParameter("cpf");
		String password = req.getParameter("password");

		EmployeeDAO dao = new EmployeeDAO();
		try {
			dao.insert(new Employee(0, name, email, cpf, password));
		} catch (Exception exc) {
			req.setAttribute("errorMessage", "We couldn't insert this employee...\n\n\tA technical message:" + exc.getMessage());
			return null;
		}

		req.setAttribute("employeeName", name);
		return "WEB-INF/jsp/InsertedEmployee.jsp";
	}

}
