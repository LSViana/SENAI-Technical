package br.senai.sp.keep.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.senai.sp.keep.dao.TaskDAO;

/**
 * Servlet implementation class DeleteTask
 */
@WebServlet("/api/tasks/delete_task")
public class DeleteTask extends HttpServlet {
	
	private TaskDAO taskDAO;

	public DeleteTask() {
		taskDAO = new TaskDAO();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String taskIdString = req.getParameter("id");
		Long taskId = Long.parseLong(taskIdString);
		// Removing Task using its ID supplied by View [JSP]
		taskDAO.deleteTask(taskId);
		// Redirect user to List View page
		getServletContext()
			.getRequestDispatcher("/api/tasks/list_tasks")
			.forward(req, res);
	}

}
