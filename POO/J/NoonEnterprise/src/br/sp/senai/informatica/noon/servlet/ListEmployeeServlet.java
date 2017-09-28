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

@WebServlet("/listEmployee")
public class ListEmployeeServlet extends HttpServlet {
	private String htmlBase = "<!DOCTYPE html>\r\n" + 
			"<html lang=\"en\">\r\n" + 
			"\r\n" + 
			"<head>\r\n" + 
			"    <meta charset=\"UTF-8\">\r\n" + 
			"    <title>Listing Employees</title>\r\n" + 
			"</head>\r\n" + 
			"<style>\r\n" + 
			"    * {\r\n" + 
			"        box-sizing: border-box;\r\n" + 
			"    }\r\n" + 
			"    body {\r\n" + 
			"        margin: 10px;\r\n" + 
			"    }\r\n" + 
			"    h3 {\r\n" + 
			"        margin-left: 20px;\r\n" + 
			"    }\r\n"
			+ "	 table, td {"
			+ "		margin: 0px;"
			+ "		border: solid gray;"
			+ "  }" +
			"</style>\r\n" + 
			"\r\n" + 
			"<body>\r\n" + 
			"    <h1>All Employees:</h1>\r\n" + 
			"    <h3>Results found:</h3>\r\n" + 
			"    <table>\r\n"
			//+ "	 	<th>"
			+ "			<td>Nome</td>"
			+ "			<td>Email</td>"
			+ "			<td>CPF</td>" +
			//+ "		</th>" + 
			"        *\r\n" + 
			"    </table>\r\n" + 
			"</body>\r\n" + 
			"\r\n" + 
			"</html>\r\n";
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		StringBuilder list = new StringBuilder();
		for (Employee employee : EmployeeDAO.getAll()) {
			list.append(String.format("<tr><td>%s</td><td>%s</td><td>%s</td></tr>\r\n", employee.getName(), employee.getEmail(), employee.getCPF()));
		}
		StringBuilder builder = new StringBuilder(htmlBase.replace("*", list.toString()));
		out.write(builder.toString());
	}
}
