package senai.tecnow.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import senai.tecnow.data.mysql.UserDAO;
import senai.tecnow.model.Gender;
import senai.tecnow.model.User;
import senai.tecnow.utils.SessionManager;

@Controller
public class UserController {
	
	public UserController() {
		sdf = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	SimpleDateFormat sdf;
	@Autowired
	UserDAO userDAO;
	@Autowired
	SessionManager sessionManager;
	
	@GetMapping("/sign-up")
	public String signUp() {
		return "user/sign-up";
	}
	
	@GetMapping("/sign-in")
	public String signIn() {
		return "user/sign-in";
	}
	
	@PostMapping("/register")
	public String register(
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("dateOfBirth") String dateOfBirth,
			@RequestParam("password") String password,
			@RequestParam("gender") String gender) {
		// Validating name
		if(name == null || name.length() < 2 || name.length() > 60) {
			return "redirect:/sign-up?message=name";
		}
		if(password == null || password.length() < 2 || password.length() > 20) {
			return "redirect:/sign-up?message=password";
		}
		//
		Date dob = new Date();
		try {
			dob = sdf.parse(dateOfBirth);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		//
		if(dob.after(new Date())) {
			return "redirect:/sign-up?message=date";
		}
		else if(userDAO.existsEmail(email)) {
			return "redirect:/sign-up?message=email";
		}
		else {
			User user = new User(-1L, name, email, dob, password, Gender.valueOf(gender));
			user.setId(0L);
			userDAO.persist(user);
			// Logged-in
			sessionManager.setLoggedIn(session, user);
		}
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String login(
			HttpSession session,
			@RequestParam("email") String email,
			@RequestParam("password") String password) {
		User user = userDAO.get(email);
		if(user != null && user.getPassword().equals(password)) {
			sessionManager.setLoggedIn(session, user);
			return "redirect:/";
		}
		else {
			return "redirect:/sign-in?message=auth";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		sessionManager.setLoggedOff(session);
		return "redirect:/";
	}
	
}