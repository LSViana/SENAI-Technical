package senai.tecnow.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

	@RequestMapping("/errors")
	public ModelAndView errors(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView errorPage = new ModelAndView("errors/errorPage");
		String errorMessage = "";
		//Integer errorCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
		Integer errorCode = response.getStatus();
		switch (errorCode) {
		case 400:
			errorMessage = "The data you sent us doesn't seem to be right. :/";
			break;
		case 401:
			errorMessage = "You're not authorized to access this page";
			break;
		case 404:
			errorMessage = "Resource not found";
			break;
		case 500:
			errorMessage = "Internal server error";
			break;
		case 1000:
			errorMessage = "We made some magic and it went out wrong, can you try again?";
			break;
		default:
			errorMessage = "Unknown error!";
			break;
		}
		errorPage.addObject("errorCode", errorCode);
		errorPage.addObject("errorMessage", errorMessage);
		return errorPage;
	}
	
}
