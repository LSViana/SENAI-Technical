package com.senai.sp.shopplace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This annotation defines this class is a Spring Framework's controller
 * @author TI-09
 */
@Controller
public class IndexController {
	
	/**
	 * Every method on a Controller class that returns @String is used as EndPoint according to @RequestMapping
	 * @return View's file
	 */
	@RequestMapping("/")
	public String openIndex() {
		return "main/index";
	}
	
	@RequestMapping("/about-us")
	public String openAboutUs() {
		return "main/about-us";
	}
	
}