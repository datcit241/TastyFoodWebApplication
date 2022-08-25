package com.tastyfoodwebapplication.controllers;

import com.tastyfoodwebapplication.models.*;
import com.tastyfoodwebapplication.repositories.CartItemRepository;
import com.tastyfoodwebapplication.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class OrderController {
    private UserService userService;
    private CartItemRepository cartItemRepository;

    @Autowired
    public OrderController(UserService userService, CartItemRepository cartItemRepository) {
        this.userService = userService;
        this.cartItemRepository = cartItemRepository;
    }

    @RequestMapping(value = "/order/take-order", method = RequestMethod.GET)
    public String order(Authentication authentication, Model model) {
        User user = userService.getLoggedInUser(authentication);
        Cart cart = userService.getCart(user);
        if (cart.getCartItems().size() != 0) {
            userService.takeOrder(user, cart.getCartItems());
        } else {
            model.addAttribute("error", "Nothing to order");
            return "redirect:/cart";
        }

        return "redirect:/";
//        return "redirect:/order/order-history";
    }

    @RequestMapping(value = "/order/order-history")
    public String getOrderHistory(Authentication authentication) {
        return "/index";
    }

}
