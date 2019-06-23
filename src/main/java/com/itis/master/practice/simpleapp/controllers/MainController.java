package com.itis.master.practice.simpleapp.controllers;

import com.itis.master.practice.simpleapp.entitites.User;
import com.itis.master.practice.simpleapp.services.interfaces.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*****
 * @author Igor Astafyev
 * June, 2019
 *****/

@Controller
public class MainController {

    private final HttpServletRequest request;
    private final UserService userService;

    public MainController(HttpServletRequest request, UserService userService) {
        this.request = request;
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String returnMainPage() {
        return "mainpage";
    }

    @RequestMapping(value = "/good", method = RequestMethod.GET)
    public String returnGoodRegistrationPage() {
        return "good";
    }

    @RequestMapping(value = "/great-registration", method = RequestMethod.GET)
    public String returnCongratulations() {
        return "great";
    }

    @ResponseBody
    @RequestMapping(value = "/check-mail", method = RequestMethod.POST)
    public String checkNewUserEmail() {
        String email = request.getParameter("email");
        List<User> users = userService.getAllUsers();
        for (User u : users) {
            if (u.getEmail().equals(email)) {
                return "error";
            }
        }
        return "ok";
    }

}
