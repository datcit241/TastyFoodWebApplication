package com.tastyfoodwebapplication.controllers;

import com.tastyfoodwebapplication.models.*;
import com.tastyfoodwebapplication.models.bindings.*;
import com.tastyfoodwebapplication.repositories.*;
import com.tastyfoodwebapplication.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.security.*;
import java.time.*;

@Controller
public class UserAccountController {
    private UserService userService;
    private UserRepository userRepository;
    private ProductRepository productRepository;

    @Autowired
    public UserAccountController(UserService userService, UserRepository userRepository, ProductRepository productRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String login(Principal principal, Model model) {
        return "redirect:/";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Principal principal, Model model) {
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("message", new Message("", "", "", "", LocalDateTime.now()));
        return "/index";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String displayRegistrationForm(Model model) {
        UserBinding userBinding = new UserBinding("", "", "", "", "", "", "");
        model.addAttribute("userBinding", userBinding);
        return "/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String register(@ModelAttribute @Valid UserBinding userBinding, BindingResult bindingResult, Model model) {
        if (!userBinding.getPassword().equals(userBinding.getRetypedPassword())) {
            bindingResult .rejectValue("retypedPassword", "error.registration", "Password is not match");
        }

        if (!bindingResult.hasErrors()) {
            boolean successful = userService.addUser(userBinding);
            if (!successful) {
                bindingResult.rejectValue("username", "error.registration", "Username already exists");
//                return "/registration";
            } else {
                model.addAttribute("successful", "true");
            }

//            return "redirect:/";
        }

//            model.addAttribute("userBinding", userBinding);
        return "/registration";
    }
}
