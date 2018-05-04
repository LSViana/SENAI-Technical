package br.senai.sp.casinoroyale.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class Index extends ServletMaster {
	private static final long serialVersionUID = -3675187323397750412L;
	// Controllers Declarations
	public static String insertUser = "users/insert_user";
	public static String listUsers = "users/list_users";
	// Views Declarations
	public static String viewInsertUser = "insert_user";
	public static String viewListUser = "list_users";
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String uri = req.getRequestURI().substring(1);
		uri = uri.substring(uri.indexOf("/") + 1);
		String[] uriLevels = uri.split("/");
		System.out.println(getCompleteViewAddress(uri) + " ////// " + uri);
		// Verifying Controllers already declared
		if(uri.equals(insertUser)) {
			forward(uri, req, res);
			return;
		}
		else if(uri.equals(listUsers)) {
			forward(uri, req, res);
			return;
		}
		else if(uri.equals(viewInsertUser)) {
			forward(getCompleteViewAddress(uri), req, res);
		}
		else if(uri.equals(viewListUser)) {
			forward(getCompleteViewAddress(uri), req, res);
		}
		// Verifying levels of access, it doesn't consider the project name at beginning
		else if(uriLevels.length == 1) {
			// Index page requested
			forward(getCompleteViewAddress("index"), req, res);
			return;
		}
	}
	
}
