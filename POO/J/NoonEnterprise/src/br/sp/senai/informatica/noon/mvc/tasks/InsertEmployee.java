package br.sp.senai.informatica.noon.mvc.tasks;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertEmployee implements ExecutableTask {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		return "WEB-INF/jsp/InsertEmployee.jsp";
	}

}
