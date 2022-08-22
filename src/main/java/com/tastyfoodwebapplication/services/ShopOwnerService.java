package com.tastyfoodwebapplication.services;

import com.tastyfoodwebapplication.enums.OrderStatus;
import com.tastyfoodwebapplication.models.*;
import com.tastyfoodwebapplication.repositories.OrderRepository;
import com.tastyfoodwebapplication.utilities.SearchHelper;
import com.tastyfoodwebapplication.utilities.OrderByRecentnessComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class ShopOwnerService {
    private OrderRepository orderRepository;

    @Autowired
    public ShopOwnerService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void moveToNextStage(String orderId) {
        Order order = orderRepository.findById(orderId).get();
        order.setStatus(OrderStatus.next(order.getStatus()));

        orderRepository.save(order);
    }

    public void cancelOrder(String orderId) {
        Order order = orderRepository.findById(orderId).get();
        order.setStatus(OrderStatus.Cancelled);

        orderRepository.save(order);
    }

    public List<Order> getOrders() {
        List<Order> orders = orderRepository.findAll();
        orders.sort(new OrderByRecentnessComparator());

        return orders;
    }

    public List<Order> getOrdersInProgress() {
        List<Order> orders = new SearchHelper<Order>(orderRepository.findAll()).get(order -> order.getStatus().isInProgress(), new OrderByRecentnessComparator());
        return orders;
    }

}
