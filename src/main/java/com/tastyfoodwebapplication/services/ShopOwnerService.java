package com.tastyfoodwebapplication.services;

import com.tastyfoodwebapplication.enums.*;
import com.tastyfoodwebapplication.models.*;
import com.tastyfoodwebapplication.models.products.*;
import com.tastyfoodwebapplication.repositories.*;
import com.tastyfoodwebapplication.utilities.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class ShopOwnerService {
    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    @Autowired
    public ShopOwnerService(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public ShopOwnerService() {}

    public void addProduct(String name, String description, String imageURL, double price) {
        Product product = new Product(name, description, imageURL, price);
        productRepository.save(product);
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
