package br.sp.senai.informatica.noon.mvc.tasks;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertContact implements ExecutableTask {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		return "WEB-INF/jsp/InsertContact.jsp";
	}

}
