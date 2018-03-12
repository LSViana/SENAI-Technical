package senai.tecnow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import senai.tecnow.data.mysql.UserDAO;

@Controller
public class MainController {
	
	UserDAO dao;
	
	public MainController() {
		dao = new UserDAO();
	} 
	
	@GetMapping({ "/" })
	public String index() {
		return "index";
	}
	
	@GetMapping({ "/head" })
	public String head() {
		return "common/head";
	}
	
	@GetMapping({ "/bottom" })
	public String bottom() {
		return "common/head";
	}
	
}
