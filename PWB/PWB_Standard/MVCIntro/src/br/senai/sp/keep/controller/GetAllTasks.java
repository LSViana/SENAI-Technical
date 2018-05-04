package br.senai.sp.keep.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.senai.sp.keep.dao.TaskDAO;
import br.senai.sp.keep.model.Task;

/**
 * Servlet implementation class GetAllTasks
 */
@WebServlet("/api/tasks/list_tasks")
public class GetAllTasks extends HttpServlet {
	
	private TaskDAO taskDAO;

	public GetAllTasks() {
		taskDAO = new TaskDAO();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Task> tasks = taskDAO.getAll();
		// Setting tasks as attribute to be accessed
		req.setAttribute("tasks", tasks);
		// Redirecting to the Task List page
		getServletContext()
			.getRequestDispatcher("/tasks/list_tasks")
			.forward(req, resp);
	}
	
}
