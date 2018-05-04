package br.senai.sp.casinoroyale.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.senai.sp.casinoroyale.dao.UserDAO;
import br.senai.sp.casinoroyale.model.User;

@WebServlet("/users/edit_user")
public class UpdateUser extends ServletMaster {
	
	private UserDAO userDAO;

	public UpdateUser() {
		userDAO = new UserDAO();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String idString = req.getParameter("id");
		User user;
		// Inserting the new User
		if (idString.isEmpty()) {
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
				// Sending @user object as Attribute to JSP
				req.setAttribute("user", user);
				// Sending back to Edit User Screens
				forward(getCompleteViewAddress("insert_user"), req, res);
			}
		}
	}
}
