package br.senai.info.pweb.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class FormServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Map<String, String[]> parameters = req.getParameterMap();
		//
		String firstName = null, lastName = null, gender = null;
		Boolean smoker = false;
		if(parameters.containsKey("firstname"))
			firstName = parameters.get("firstname")[0];
		if(parameters.containsKey("lastname"))
			lastName = parameters.get("lastname")[0];
		if(parameters.containsKey("gender"))
			gender = parameters.get("gender")[0];
		if(parameters.containsKey("smoker"))
			smoker = true;
		//
		req.setAttribute("firstname", firstName);
		req.setAttribute("lastname", lastName);
		req.setAttribute("gender", gender);
		req.setAttribute("smoker", smoker);
		//
		RequestDispatcher rd = req.getRequestDispatcher("/PersonProfileResponse.jsp");
		rd.forward(req, res);
	}
}
