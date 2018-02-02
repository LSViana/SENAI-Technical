package br.senai.sp.keep.controller;

import br.senai.sp.keep.helper.*;
import br.senai.sp.keep.model.Task;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertTask
 */
@WebServlet("/MVCIntro/api/tasks/insert_task")
public class InsertTask extends HttpServlet {

	private static final long serialVersionUID = 132899050782033539L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("hi");
		//
		ParameterHandler ph = new ParameterHandler(req);
		// Getting data from Form (HTTP Request)
		String title = ph.getString("title");
		String description = ph.getString("description");
		Boolean highPriority = ph.getBoolean("high_priority");
		LocalDateTime conclusionDate = ph.getLocalDateTime("conclusion_date"); 
		// Creating Task object
		Task task = new Task(-1L, title, description, highPriority, conclusionDate);
		// Attaching @task object as Attribute to the request response
		req.setAttribute("task", task);
		// Redirecting the request to the view path
		getServletContext()
		.getRequestDispatcher("/MVCIntro/tasks/api/view_task.jsp")
		.forward(req, resp);
	}
}
