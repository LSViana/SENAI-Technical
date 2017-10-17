package br.sp.senai.informatica.noon.mvc.tasks;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.sp.senai.informatica.noon.data.EmployeeDAO;
import br.sp.senai.informatica.noon.model.Employee;

public class ListEmployee implements ExecutableTask {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<Employee> employees = new EmployeeDAO().getAll();
		req.setAttribute("employees", employees);
		return "WEB-INF/jsp/ListEmployee.jsp";
	}

}
