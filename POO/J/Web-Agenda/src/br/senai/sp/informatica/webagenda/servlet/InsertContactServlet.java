package br.senai.sp.informatica.webagenda.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.senai.sp.informatica.webagenda.data.AgendaDAO;
import br.senai.sp.informatica.webagenda.model.Agenda;

@WebServlet("/insertContact")
public class InsertContactServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		String resHtml = null;
		try {
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String address = req.getParameter("address");
			LocalDate dateOfBirth = LocalDate.parse(req.getParameter("dateOfBirth"),
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			AgendaDAO.insert(new Agenda(-1, name, email, address, dateOfBirth));
			resHtml = "<html>" + "<body>" + "<h3>We did it!</h3>" + "<p>Your new contact was successfully!</p>"
					+ "<a href=\"FormView.html\"><button>Back to Form</button></a>" + "</body>" + "</html>";
		} catch (Exception e) {
			String cause = "Verify the inserted data and try again!";
			if (e.getCause() instanceof SQLException)
				cause = "It seems this e-mail is already being used, try again with another one!";
			resHtml = "<html>" + "<body>" + "<h3>Something went out wrong!</h3>" + "<p>" + cause + "</p>"
					+ "<a href=\"FormView.html\"><button>Back to Form</button></a>" + "</body>" + "</html>";
		} finally {
			out.write(resHtml);
		}
	}
}

/**
 * HTTP Methods:
 * GET
 * POST
 * PUT
 * DELETE
 * HEAD
 * TRACE
 * CONNECT
 * OPTIONS
 * PATCH
 */
/** HTTP Returns:
 * 1xx – Informative
 * 2xx – Success
 * 3xx – Redirected
 * 4xx – Client-side error
 * 5xx – Server-side error 
 */
