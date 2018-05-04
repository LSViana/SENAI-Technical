package senai.sstorage.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorHandlerController {
	
	@RequestMapping("/error")
	public String handleErrors(HttpServletResponse response, Model model) {
		model.addAttribute("error", response.getStatus());
		return "error";
	}

}
