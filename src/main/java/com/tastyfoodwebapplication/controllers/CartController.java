package com.tastyfoodwebapplication.controllers;

import com.tastyfoodwebapplication.models.User;
import com.tastyfoodwebapplication.models.products.*;
import com.tastyfoodwebapplication.repositories.*;
import com.tastyfoodwebapplication.services.UserService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {
    private ProductRepository productRepository;
    private UserService userService;

    @Autowired
    public CartController(ProductRepository productRepository, UserService userService) {
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @RequestMapping(value="/cart/add-product/{productId}")
    public String addToCart(@PathVariable String productId, Authentication authentication) {
        Product product = productRepository.findById(productId).get();
        if (product != null) {
            User user = (User) authentication.getPrincipal();
            userService.addToCart(user, product, null);
        }
        return "redirect:/";
    }
}
