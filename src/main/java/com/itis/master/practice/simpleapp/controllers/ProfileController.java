package com.itis.master.practice.simpleapp.controllers;

import com.itis.master.practice.simpleapp.entitites.User;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class ProfileController {

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String returnLoginPage(ModelMap modelMap) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modelMap.put("currentUser", currentUser);
		return "profile";
	}
}
