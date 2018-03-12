package senai.tecnow.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import senai.tecnow.data.mysql.UserDAO;
import senai.tecnow.model.User;
import senai.tecnow.utils.SessionManager;

@Controller
@RequestMapping("/profile")
public class ProfileController {
	
	public ProfileController() {
		sdf = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	SimpleDateFormat sdf;

	@Autowired
	UserDAO userDAO;
	@Autowired
	SessionManager sessionManager;

	@GetMapping({ "/", "" })
	public String profile(HttpSession session) {
		if(sessionManager.isLoggedIn(session))
			return "user/profile";
		else
			return "user/not-logged";
	}

	@PostMapping("/cnd")
	public String ChangeNameDateBirth(
			HttpSession session,
			@RequestParam("dateOfBirth") String dateOfBirth,
			@RequestParam("name") String name) {
		Date dob = new Date();
		try {
			dob = sdf.parse(dateOfBirth);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		User u = sessionManager.getLoggedIn(session);
		u.setName(name);
		u.setDateOfBirth(dob);
		//
		sessionManager.setLoggedIn(session, u);
		userDAO.persist(u);
		//
		return "redirect:/profile";
	}

}