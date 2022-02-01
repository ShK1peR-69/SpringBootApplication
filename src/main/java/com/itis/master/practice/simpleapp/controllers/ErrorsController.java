package com.itis.master.practice.simpleapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*****
 * @author Igor Astafyev
 * January 2022
 * Web security settings
 *****/

@Controller
public class ErrorsController {

	@RequestMapping(value = "/access-denied", method = RequestMethod.GET)
	public String returnLoginPage() {
		return "accessdenied";
	}
}
