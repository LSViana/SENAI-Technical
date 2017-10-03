package br.sp.senai.informatica.noon.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.sp.senai.informatica.noon.data.ContactDAO;
import br.sp.senai.informatica.noon.model.Contact;

@WebServlet("/insertContact")
public class InsertContactServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		String resHTML = null;
		ContactDAO contactDAO = new ContactDAO();
		try {
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String address = req.getParameter("address");
			String dateOfBirthday = req.getParameter("dateOfBirthday");
			contactDAO.insert(new Contact(0, name, email, address, LocalDate.parse(dateOfBirthday)));
			resHTML = "<html>" + "<body>" + "<h3>We did it!</h3>"
					+ "<p>Your new contact is registered, you can come back to the Form page with the following button:</br>"
					+ "<a href=\"InsertContact.html\">" + "<button>Back to Form</button>" + "</a>" + "</body>"
					+ "</html>";
		} catch (Exception e) {
			resHTML = "<html>" + "<body>" + "<h3>Something went out wrong, try again or call our employees!</h3>"
					+ "<h4>Technical Message:</h4>" + "<p>" + e.getMessage() + "</p></br>"
					+ "<a href=\"InsertContact.html\">" + "<button>Back to Form</button>" + "</a>" + "</body>"
					+ "</html>";
		} finally {
			out.write(resHTML);
		}
	}
}
