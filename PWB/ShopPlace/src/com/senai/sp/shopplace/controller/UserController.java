package com.senai.sp.shopplace.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.senai.sp.shopplace.data.mysql.UserDAO;
import com.senai.sp.shopplace.model.User;
import com.senai.sp.shopplace.utils.SessionManager;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private SessionManager sessionManager;	

	@GetMapping("/sign-in")
	public String signIn() {
		return "user/sign-in";
	}
	
	@GetMapping("/sign-up")
	public String signUp() {
		return "user/sign-up";
	}
	
	@PostMapping("/login")
	public String login(User user, HttpSession session) {
		if(userDAO.authenticate(user)) {
			sessionManager.setLoggedIn(session, user);	
		}
		return "redirect:/app";
	}
	
	@PostMapping("/register")
	public String register(User user, HttpSession session) {
		userDAO.insert(user);
		sessionManager.setLoggedIn(session, user);
		return "redirect:/app";
	}
	
}
