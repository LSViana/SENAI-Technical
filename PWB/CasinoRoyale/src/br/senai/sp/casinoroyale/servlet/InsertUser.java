package br.senai.sp.casinoroyale.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.senai.sp.casinoroyale.dao.UserDAO;
import br.senai.sp.casinoroyale.model.GenderClass;
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
		String idString = req.getParameter("id");
		String firstName = req.getParameter("first_name");
		String lastName = req.getParameter("last_name");
		GenderClass.Gender gender = GenderClass.parse(req.getParameter("gender"));
		String smokerParam = req.getParameter("smoker");
		Boolean smoker = smokerParam != null && smokerParam.equals("on");
		//
		User user;
		// Inserting the new User
		if (idString.isEmpty()) {
			// Inserting a new User
			user = new User(-1L, firstName, lastName, gender, smoker);
			// Adding to DAO
			userDAO.addUser(user);
			// Returning to List User Screen
			forward("/users/list_users", req, res);
		}
		// Filling InsertUser form with supplied data
		else {
			Long id = Long.parseLong(idString);
			user = userDAO.search(id);
			if (user == null) {
				// User is null, returning to Insert User Screen
				forward("/users/list_users", req, res);
			} else {
				// Updating user object
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setGender(gender);
				user.setSmoker(smoker);
				// Sending back to List User Screens
				forward("/users/list_users", req, res);
			}
		}
	}
}
