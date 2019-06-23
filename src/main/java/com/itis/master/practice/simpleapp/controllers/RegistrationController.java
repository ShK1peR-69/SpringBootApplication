package com.itis.master.practice.simpleapp.controllers;

import com.itis.master.practice.simpleapp.entitites.User;
import com.itis.master.practice.simpleapp.services.interfaces.UserService;
import com.itis.master.practice.simpleapp.util.Mailing;
import com.itis.master.practice.simpleapp.util.Methods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/*****
 * @author Igor Astafyev
 * June, 2019
 * Registration controller
 *****/

@Controller
public class RegistrationController {

    private final HttpServletRequest request;
    private final UserService userService;

    @Autowired
    public RegistrationController(HttpServletRequest request, UserService userService) {
        this.request = request;
        this.userService = userService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationOfNewUser(ModelMap model) {
        String fio = request.getParameter("fio");
        String email = request.getParameter("email");
        int age = Integer.parseInt(request.getParameter("age"));
        String password_one = request.getParameter("password_one");
        String password_two = request.getParameter("password_two");

        if (!password_one.equals(password_two)) {
            model.put("msg", "error");
            return "/";
        }
        password_one = Methods.hashPass(password_one);
        userService.saveNewUser(new User(fio, age, email,
                password_one, "ROLE_USER", false, Methods.createKey(email)));
        Mailing.sendMail(email, fio);
        return "redirect:/good";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String changeUserApproval() {
        String firstKey = request.getParameter("mucho");
        String secondKey = request.getParameter("rzd");
        userService.updateUserApproval(secondKey + firstKey);
        return "redirect:/great-registration";
    }
}
