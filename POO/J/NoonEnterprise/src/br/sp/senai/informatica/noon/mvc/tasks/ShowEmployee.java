package br.sp.senai.informatica.noon.mvc.tasks;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.sp.senai.informatica.noon.data.EmployeeDAO;
import br.sp.senai.informatica.noon.model.Employee;

public class ShowEmployee implements ExecutableTask {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		EmployeeDAO dao = new EmployeeDAO();
		Employee employee = dao.findById(id);
		req.setAttribute("id", id);
		req.setAttribute("name", employee.getName());
		req.setAttribute("email", employee.getEmail());
		req.setAttribute("cpf", employee.getCPF());
		req.setAttribute("password", employee.getPassword());
		return "WEB-INF/jsp/ShowEmployee.jsp";
	}

}
