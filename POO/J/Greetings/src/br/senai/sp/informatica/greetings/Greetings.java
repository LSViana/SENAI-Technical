package br.senai.sp.informatica.greetings;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="Greetings", urlPatterns= {"/Greetings", "/greetings"})
public class Greetings extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		/**
		 * It is not allowed to be here because it throws 'HTTP 405 - Method Not Allowed'
		 */
		//super.service(req, res);
		
		PrintWriter out = res.getWriter();
		String html = "<html>"
				+ "<head>"
				+ "<title>Greetings, Universe!</title>"
				+ "</head>"
				+ "<body>"
				+ "<h2>First Servlet!</h2>"
				+ "</body>"
				+ "</html>"; 
		out.print(html);
	}
}