package com.tastyfoodwebapplication.models;

import com.tastyfoodwebapplication.utilities.DefaultOrderComparator;
import org.springframework.core.OrderComparator;

import javax.persistence.*;
import java.util.*;

@Entity
public class OrderHistory {
    @Id
    private String id;
    @OneToMany(cascade = CascadeType.ALL)
//    @OneToMany
    private List<CustomerOrder> orders;

    public OrderHistory(String id) {
        this.id = id;
        this.orders = new ArrayList<>();
    }

    public OrderHistory() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public List<CustomerOrder> getOrders() { return orders; }
    public void setOrders(List<CustomerOrder> orders) {
        orders.sort(new DefaultOrderComparator());
        this.orders = orders;
    }
    public void addOrder(CustomerOrder order) { this.orders.add(order); }
}
