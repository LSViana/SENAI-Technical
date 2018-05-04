package br.senai.sp.casinoroyale.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletMaster extends HttpServlet {

	public static String projectName = "CasinoRoyale";
	public static String baseViewAddress = "WEB-INF/views";	
	public static String standardExtension = ".jsp";
	private static final long serialVersionUID = 5965356867867714500L;

	public static String getCompleteViewAddress(String view) {
		return "/" + baseViewAddress + "/" + view + standardExtension;
	}
	
	public void forward(String view, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		getServletContext()
			.getRequestDispatcher(view)
			.forward(req, res);
	}

}
