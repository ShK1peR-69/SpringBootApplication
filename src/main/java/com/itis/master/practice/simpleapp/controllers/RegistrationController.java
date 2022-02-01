package com.itis.master.practice.simpleapp.controllers;

import com.itis.master.practice.simpleapp.entitites.User;
import com.itis.master.practice.simpleapp.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/*****
 * @author Igor Astafyev
 * June 2019
 * Registration controller
 *****/

@Controller
public class RegistrationController {

	private final UserService userService;

	@Autowired
	public RegistrationController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registrationOfNewUser(ModelMap model,
	                                    @RequestParam("fio") String fio,
	                                    @RequestParam("email") String email,
	                                    @RequestParam("age") int age,
	                                    @RequestParam("password_one") String password,
	                                    @RequestParam("password_two") String repeatedPassword) {
		if (!password.equals(repeatedPassword)) {
			model.put("msg", "error");
			return "/";
		}
		userService.saveNewUser(new User(fio, age, email, password));
		return "redirect:/good";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String changeUserApproval(@RequestParam("mucho") String firstKey,
	                                 @RequestParam("rzd") String secondKey) {
		userService.updateUserApproval(secondKey + firstKey);
		return "redirect:/great-registration";
	}
}