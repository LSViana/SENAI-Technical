package br.sp.senai.informatica.noon.mvc.tasks;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ExecutableTask {
	String service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;
}
