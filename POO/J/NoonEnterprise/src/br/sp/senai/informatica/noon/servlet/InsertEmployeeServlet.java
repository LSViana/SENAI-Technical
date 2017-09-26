package br.sp.senai.informatica.noon.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.sp.senai.informatica.noon.data.EmployeeDAO;
import br.sp.senai.informatica.noon.model.Employee;

@WebServlet("/insertEmployee")
public class InsertEmployeeServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		String resHTML = null;
		try {
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			EmployeeDAO.insert(new Employee(0, name, email, password));
			resHTML = "<html>" + "<body>" + "<h3>We did it!</h3>"
					+ "<p>Your new employee is registered, you can come back to the Form page with the following button:</br>"
					+ "<a href=\"InsertEmployee.html\">" + "<button>Back to Form</button>" + "</a>" + "</body>"
					+ "</html>";
		} catch (Exception e) {
			resHTML = "<html>" + "<body>" + "<h3>Something went out wrong, try again or call our employees!</h3>"
					+ "<h4>Technical Message:</h4>" + "<p>" + e.getMessage() + "</p></br>"
					+ "<a href=\"InsertEmployee.html\">" + "<button>Back to Form</button>" + "</a>" + "</body>"
					+ "</html>";
		} finally {
			out.write(resHTML);
		}
	}
}
