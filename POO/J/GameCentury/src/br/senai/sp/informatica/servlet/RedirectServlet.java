package br.senai.sp.informatica.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RedirectServlet
 */
@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		String forwardPage = "WEB-INF/jsp/index.jsp";
		// Login cookie
		Cookie[] cookies = request.getCookies();
		Boolean logged = false;
		HttpSession session = request.getSession();
		if(session.getAttribute("permission-1") != null && (Boolean)session.getAttribute("permission-1") == true)
			logged = true;
		request.setAttribute("permission-1", logged);
		//
		switch(page.toLowerCase()) {
			case "games": forwardPage = "WEB-INF/jsp/games.jsp";
				break;
			case "about-us": forwardPage = "WEB-INF/jsp/about-us.jsp";
				break;
			case "find-us": forwardPage = "WEB-INF/jsp/find-us.jsp";
				break;
			case "login":
				if(logged) {
					session.setAttribute("permission-1", false);
				}
				else {
					forwardPage = "WEB-INF/jsp/login.jsp";
				}
				break;
		}
		//
		request.getRequestDispatcher(forwardPage).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
