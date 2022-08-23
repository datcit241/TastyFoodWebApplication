package com.tastyfoodwebapplication.models;

import com.tastyfoodwebapplication.enums.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.*;
import java.util.*;

@Entity
public class Order {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @OneToOne
    @JoinColumn(name = "user_username")
    private User user;
    @OneToMany
    private List<CartItem> cartItems;
    private OrderStatus status;
    private LocalDateTime orderedAt;

    public Order() {}

    public Order(User user, List<CartItem> cartItems, LocalDateTime orderedAt) {
        this.user = user;
        this.cartItems = cartItems;
        this.orderedAt = orderedAt;
        this.status = OrderStatus.Pending;
    }

    public String getId() { return id; }

    public User getUser() { return user; }

    public List<CartItem> getCartItems() { return cartItems; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public LocalDateTime getOrderedAt() { return orderedAt; }

}
