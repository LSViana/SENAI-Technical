package com.senai.sp.sstorage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.DispatcherServlet;

@Controller
public class UsuarioController {

	@GetMapping({ "/login", "/" })
	public String loginView() {
		
		return "main";
	}
	
}
