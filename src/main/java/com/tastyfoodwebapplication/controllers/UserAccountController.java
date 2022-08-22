package com.tastyfoodwebapplication.controllers;

import com.tastyfoodwebapplication.models.User;
import com.tastyfoodwebapplication.models.bindings.UserBinding;
import com.tastyfoodwebapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserAccountController {
    @Autowired
    UserService userService;

    public String control() {
        userService.addUser(new UserBinding("username", "password", "Dat", "A33C", "113"));
        User user = new UserService().find(anyUser -> anyUser.getUsername().equals("username"));
        return user.getName();
    }

//    @RequestMapping(value="/loggin", method= RequestMethod.POST)
//    public String login(@RequestParam String username, @RequestParam String password) {
//    }

    @RequestMapping(value="/login")
    public String login() {
        return "/index";
    }

}
