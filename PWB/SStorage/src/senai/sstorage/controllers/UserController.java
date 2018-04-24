package senai.sstorage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/login")
	public String openLogin() {
		return "user/login";
	}

	@Controller
	@RequestMapping("/user/api")
	public class UserAPIController {
		
		
		
	}

}
