package br.senai.sp.casinoroyale.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.senai.sp.casinoroyale.dao.UserDAO;
import br.senai.sp.casinoroyale.model.Gender;
import br.senai.sp.casinoroyale.model.User;

@WebServlet("/users/insert_user")
public class InsertUser extends ServletMaster {
	
	public InsertUser() {
		userDAO = new UserDAO();
	}
	
	private UserDAO userDAO;
	private static final long serialVersionUID = -743958076382290706L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// Getting Parameters' Values
		String firstName = req.getParameter("first_name");
		String lastName = req.getParameter("last_name");
		Gender gender = Gender.valueOf(req.getParameter("gender"));
		Boolean smoker = Boolean.parseBoolean(req.getParameter("smoker"));
		//
		User user = new User(-1L, firstName, lastName, gender, smoker);
		// Adding to DAO
		userDAO.addUser(user);
		// Returning to Insert User Screen
		forward(getCompleteViewAddress("/users/list_users"), req, res);
	}
}
