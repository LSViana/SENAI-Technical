package com.senai.sp.colliboration.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.senai.sp.colliboration.data.mysql.UserDAO;
import com.senai.sp.colliboration.model.User;
import com.senai.sp.colliboration.utils.Interceptor;
import com.senai.sp.colliboration.utils.SessionManager;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final String WRONG_LOGIN = "wrong_login";

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
		user.hashPassword();
		User authenticated = userDAO.authenticate(user);
		if(authenticated != null) {
			sessionManager.setLoggedIn(session, user);
			return "redirect:" + Interceptor.SHOPPING_PREFIX;
		}
		return wrongLogin(session);
	}
	
	@GetMapping("/logout")
	private String removeWrongLogin(HttpSession session) {
		sessionManager.setLoggedOff(session);
		return "redirect:/";
	}

	private String wrongLogin(HttpSession session) {
		session.setAttribute(WRONG_LOGIN, new Boolean(true));
		return "/sign-in";
	}

	@PostMapping("/register")
	public String register(User user, HttpSession session) {
		userDAO.insert(user);
		sessionManager.setLoggedIn(session, user);
		return "redirect:" + Interceptor.SHOPPING_PREFIX;
	}
	
	@GetMapping("/profile")
	public String profile(HttpSession session) {
		Object obj = session.getAttribute(SessionManager.USER_KEY);
		if(sessionManager.isLoggedIn(session) && obj instanceof User) {
			User user = (User)obj;
			sessionManager.setLoggedIn(session, userDAO.get(user.getId()));
			return "user/profile";
		}
		return "redirect:/";
	}
	
}
