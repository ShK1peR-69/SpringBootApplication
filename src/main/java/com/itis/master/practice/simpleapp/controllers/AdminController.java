package com.itis.master.practice.simpleapp.controllers;

import com.itis.master.practice.simpleapp.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*****
 * @author Igor Astafyev
 * June 2019
 * Admin page
 *****/

@Controller
public class AdminController {

	private final UserService userService;

	@Autowired
	public AdminController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String returnAdminPage(ModelMap modelMap) {
		modelMap.put("users", userService.getAllUsers());
		return "adminpage";
	}

	@RequestMapping(value = "/admin/users/{userId}", method = RequestMethod.GET)
	public String gtUser(@PathVariable("userId") Long userId, ModelMap model) {
		model.addAttribute("userInfo", userService.getUserById(userId));
		return "adminpage";
	}
}
