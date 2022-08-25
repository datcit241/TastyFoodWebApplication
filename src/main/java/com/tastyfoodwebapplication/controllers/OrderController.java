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
    public String order(@ModelAttribute Cart cart, BindingResult bindingResult, Authentication authentication, Model model) {
        User user = userService.getLoggedInUser(authentication);

        List<CartItem> cartItems = cart.getCartItems().stream().map(cartItem -> cartItemRepository.findById(cartItem.getId()).get()).toList();

        if (cart.getCartItems() != null) {
            userService.takeOrder(user, cartItems);
        } else {
            model.addAttribute("error", "Nothing to order");
            return "/cart";
        }

        return "redirect:/order/order-history";
    }

    @RequestMapping(value = "/order/order-history")
    public String getOrderHistory(Authentication authentication) {
        return "/index";
    }

}
