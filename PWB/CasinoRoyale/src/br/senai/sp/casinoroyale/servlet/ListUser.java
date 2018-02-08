package br.senai.sp.casinoroyale.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.senai.sp.casinoroyale.dao.UserDAO;

@WebServlet("/users/list_users")
public class ListUser extends ServletMaster {
	
	private static final long serialVersionUID = -991097744276815955L;
	private UserDAO userDAO;
	public static String viewListUser = "list_users";

	public ListUser() {
		userDAO = new UserDAO();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setAttribute("users", userDAO.getAll());
		forward(getCompleteViewAddress(viewListUser), req, res);
	}
}