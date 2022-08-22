package com.tastyfoodwebapplication.services;

import com.tastyfoodwebapplication.models.*;
import com.tastyfoodwebapplication.repositories.CartRepository;
import com.tastyfoodwebapplication.repositories.OrderRepository;
import com.tastyfoodwebapplication.repositories.UserRepository;
import com.tastyfoodwebapplication.utilities.OrderByRecentnessComparator;
import com.tastyfoodwebapplication.utilities.SearchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {
    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private CartRepository cartRepository;

    public OrderService() {}

    @Autowired
    public OrderService(UserRepository userRepository, OrderRepository orderRepository, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    public void addOrder(User user, List<CartItem> cartItems) {
        Order order = new Order(user, cartItems, LocalDateTime.now());

        Cart cart = cartRepository.findById(user.getUsername()).get();
        cart.getCartItems().removeAll(cartItems);

        cartRepository.save(cart);
        orderRepository.save(order);
    }

    public List<Order> getOrders(User user) {
        return new SearchHelper<Order>(orderRepository.findAll()).get(order -> order.getUser().equals(user), new OrderByRecentnessComparator());
    }

    public List<Order> getOrdersInProgress(User user) {
        return new SearchHelper<Order>(orderRepository.findAll()).get(order -> order.getUser().equals(user) && order.getStatus().isInProgress(), new OrderByRecentnessComparator());
    }

}
