package br.com.senai.sp.mercadoaberto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/sign-in")
	public String signIn() {
		return "sign-in";
	}
	
	@GetMapping("/sign-up")
	public String signOut() {
		return "sign-up";
	}
	
}
