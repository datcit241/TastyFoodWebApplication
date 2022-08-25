package com.tastyfoodwebapplication.models;

import javax.persistence.*;
import java.util.*;

@Entity
public class OrderHistory {
    @Id
    private String id;
//    @OneToMany(cascade = CascadeType.ALL)
    @OneToMany
    private List<Order> orders;

    public OrderHistory(String id) {
        this.id = id;
        this.orders = new ArrayList<>();
    }

    public OrderHistory() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public List<Order> getOrders() { return orders; }
    public void setOrders(List<Order> orders) { this.orders = orders; }
    public void addOrder(Order order) { this.orders.add(order); }
}
