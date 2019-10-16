package com.parse.email.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	//This method is used to display the email page in the program
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showMainPage(ModelMap model) {
		return "email";
	}
	
	@RequestMapping(value = "dashboard", method = RequestMethod.GET)
	public String showDashboardPage(ModelMap model) {
		return "dashboard";
	}
	
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String showHomePage(ModelMap model) {
		return "home";
	}
	
	@RequestMapping(value = "email", method = RequestMethod.GET)
	public String showEmailPage(ModelMap model) {
		return "email";
	}
}
