package com.itis.master.practice.simpleapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*****
 * @author Igor Astafyev
 * January 2022
 * Login page controller
 *****/

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String returnLoginPage(ModelMap modelMap) {
		return "loginpage";
	}
}
