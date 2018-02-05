package br.senai.sp.casinoroyale.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class IndexServlet extends ServletMaster {
	
	// Controllers Declarations
	public static String insertUser = "users/insert_user";
	// Views Declarations
	public static String viewInsertUser = "/insert_user";
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String uri = req.getRequestURI().substring(1);
		uri = uri.substring(uri.indexOf("/") + 1);
		String[] uriLevels = uri.split("/");
		// Verifying Controllers already declared
		if(uri == insertUser) {
			forward(uri, req, res);
			return;
		}
		else if(uri == viewInsertUser) {
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
