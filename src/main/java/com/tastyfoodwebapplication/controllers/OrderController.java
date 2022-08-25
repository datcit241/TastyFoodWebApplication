package com.tastyfoodwebapplication.controllers;

import com.tastyfoodwebapplication.models.*;
import com.tastyfoodwebapplication.repositories.CartItemRepository;
import com.tastyfoodwebapplication.repositories.OrderHistoryRepository;
import com.tastyfoodwebapplication.services.*;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;


@Controller
public class OrderController {
    private UserService userService;
    private CartItemRepository cartItemRepository;
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    public OrderController(UserService userService, CartItemRepository cartItemRepository, OrderHistoryRepository orderHistoryRepository) {
        this.userService = userService;
        this.cartItemRepository = cartItemRepository;
        this.orderHistoryRepository = orderHistoryRepository;
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

        return "redirect:/order/order-history";
    }

    @RequestMapping(value = "/order/order-history")
    public String getOrderHistory(Authentication authentication, Model model) {
        User user = userService.getLoggedInUser(authentication);
        OrderHistory orderHistory = orderHistoryRepository.findById(user.getId()).get();

        model.addAttribute("orderHistory", orderHistory);

        return "/order-history";
    }

    @RequestMapping(value = "/order/cancel/{orderId}")
    public String cancelOrder(@PathVariable String orderId, Authentication authentication) {
        User user = userService.getLoggedInUser(authentication);
        userService.cancelOrder(user, orderId);
        return "redirect:/order/order-history";
    }
}
