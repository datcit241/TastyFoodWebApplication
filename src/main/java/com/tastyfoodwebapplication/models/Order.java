package com.tastyfoodwebapplication.models;

import com.tastyfoodwebapplication.enums.OrderStatus;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    @Column(insertable = false, updatable = false, nullable = false)
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
