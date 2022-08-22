package com.tastyfoodwebapplication.models;

import com.tastyfoodwebapplication.enums.UserRole;
import com.tastyfoodwebapplication.enums.UserStatus;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class User {
    @Id
    @Column(insertable = false, updatable = false, nullable = false)
    private String username;
    private String name;
    private String salt;
    private String hashedPassword;
    private String address;
    private String phoneNumber;
    private UserRole role;
    private UserStatus status;
//    @OneToOne
//    @JoinColumn(name = "cart_id")
//    private Cart cart;

    public User() {}

    public User(String username, String name, String salt, String hashedPassword, String address, String phoneNumber, UserRole role) {
        this.username = username;
        this.name = name;
        this.salt = salt;
        this.hashedPassword = hashedPassword;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.status = UserStatus.Active;
//        this.cart = new Cart();
    }

    public String getUsername() { return username; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSalt() { return salt; }
    public void setSalt(String salt) { this.salt = salt; }

    public String getHashedPassword() { return hashedPassword; }
    public void setHashedPassword(String hashedPassword) { this.hashedPassword = hashedPassword; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }

    public UserStatus getStatus() { return status; }
    public void setStatus(UserStatus status) { this.status = status; }

//    public Cart getCart() { return cart; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
