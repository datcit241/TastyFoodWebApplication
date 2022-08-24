package com.tastyfoodwebapplication.controllers;

import com.tastyfoodwebapplication.enums.UserRole;
import com.tastyfoodwebapplication.models.User;
import com.tastyfoodwebapplication.models.bindings.UserBinding;
import com.tastyfoodwebapplication.repositories.ProductRepository;
import com.tastyfoodwebapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class UserAccountController {
    private UserService userService;
    private ProductRepository productRepository;

    @Autowired
    public UserAccountController(UserService userService, ProductRepository productRepository) {
        this.userService = userService;
        this.productRepository = productRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String login(Principal principal, Model model) {
        return "redirect:/";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Principal principal, Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "/index";
    }


//    @RequestMapping(value = "/logout")
//    public String logout() {
//
//    }

}
