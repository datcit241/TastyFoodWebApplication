package com.tastyfoodwebapplication.models;

import com.tastyfoodwebapplication.enums.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.*;
import java.util.*;

@Entity
public class CustomerOrder {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;
    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> cartItems;
    private OrderStatus status;
    private LocalDateTime orderedAt;
    private double totalPrice;

    public CustomerOrder() {}

    public CustomerOrder(User user, List<CartItem> cartItems, double totalPrice, LocalDateTime orderedAt) {
        this.user = user;
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
        this.orderedAt = orderedAt;
        this.status = OrderStatus.Pending;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<CartItem> getCartItems() { return cartItems; }
    public void setCartItems(List<CartItem> cartItems) { this.cartItems = cartItems; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public LocalDateTime getOrderedAt() { return orderedAt; }
    public void setOrderedAt(LocalDateTime orderedAt) { this.orderedAt = orderedAt; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
}
