package senai.sstorage.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartResolver;

import senai.sstorage.SessionUtils;
import senai.sstorage.dao.UserDAO;
import senai.sstorage.models.User;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private MultipartResolver multiPartResolver;
	
	@GetMapping("/login")
	public String openLogin(Model model) {
		model.addAttribute(User.class.getSimpleName().toLowerCase(), new User());
		return "user/login";
	}	

	@Controller
	@RequestMapping("/user/api")
	public class UserPublicAPIController {
		
		@PostMapping("/login")
		public String login(@Valid User user, BindingResult br, HttpSession session) {
			if (user.getPassword().length() > 20) {
				br.addError(new FieldError("user", "password", "The password must contains between 6 and 20 characters"));
				return "redirect:/user/login";
			}
			//
			user.hashPassword();
			User auth = userDAO.searchByEmailAndPassword(user.getEmail(), user.getPassword());
			// Verifying authentication
			if(auth != null) {
				// Successfully authenticated
				SessionUtils.setLoggedIn(session, auth);
				return "redirect:/";
			} else {
				// Authentication error
				br.addError(new FieldError("user", "password", "Invalid e-mail or password!"));
				return "user/login";
			}
		}
		
		@GetMapping("/logout")
		public String logout(HttpSession session) {
			SessionUtils.setLoggedOut(session);
			return "redirect:/";
		}
		
	}
	
	@Controller
	@RequestMapping("/app/user/api")
	public class UserAPIController {
		
		@PostMapping("/new")
		public String create(@Valid User user, BindingResult br) {
			return null;
		}
		
	}

}
