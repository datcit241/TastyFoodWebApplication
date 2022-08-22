package com.tastyfoodwebapplication.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Type(type="uuid-char")
//    @Column(insertable = false, updatable = false, nullable = false)
    private String id;
    @OneToMany
    private List<CartItem> cartItems;
//    @OneToOne
//    @MapsId
//    private User user;

    public Cart() {}

    public Cart(String id) {
        this.id = id;
        this.cartItems = new ArrayList<>();
    }

    public String getId() { return id; }

    public List<CartItem> getCartItems() { return this.cartItems; }
    public void addCartItem(CartItem cartItem) { this.cartItems.add(cartItem); }
    public void removeCartItem(CartItem cartItem) { this.cartItems.remove(cartItem); }

}
