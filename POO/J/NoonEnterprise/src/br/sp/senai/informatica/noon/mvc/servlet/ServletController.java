package br.sp.senai.informatica.noon.mvc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.sp.senai.informatica.noon.mvc.tasks.ExecutableTask;

@WebServlet("/mvc")
public class ServletController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			String className = "br.sp.senai.informatica.noon.mvc.tasks." + req.getParameter("task");
			Class classExecute = Class.forName(className);
			ExecutableTask task = (ExecutableTask) classExecute.newInstance();
			req.getRequestDispatcher(task.service(req, res)).forward(req, res);			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
