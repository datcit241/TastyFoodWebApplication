package com.tastyfoodwebapplication.controllers;

import com.tastyfoodwebapplication.models.*;
import com.tastyfoodwebapplication.models.products.*;
import com.tastyfoodwebapplication.repositories.*;
import com.tastyfoodwebapplication.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;

@Controller
public class CartController {
    private ProductRepository productRepository;
    private UserService userService;

    @Autowired
    public CartController(ProductRepository productRepository, UserService userService) {
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @RequestMapping(value="/cart/add-item/{productId}")
    public String addToCart(@PathVariable String productId, Authentication authentication) {
        Product product = productRepository.findById(productId).get();
        if (product != null) {
            User user = (User) authentication.getPrincipal();
            userService.addToCart(user, product, new HashSet<>());
        }
        return "redirect:/";
    }

    @RequestMapping(value="/cart/delete-item/{cartItemId}")
    public String removeFromCart(@PathVariable String cartItemId, Authentication authentication) {
        User user = userService.getLoggedInUser(authentication);
        userService.removeFromCart(user, cartItemId);
        return "redirect:/cart";
    }

    @RequestMapping(value =  "/cart")
    public String showCart(Model model, Authentication authentication) {
        User user = userService.getLoggedInUser(authentication);
        Cart cart = userService.getCart(user);

        model.addAttribute("cartItems", cart.getCartItems());
        return "/cart";
    }

}
