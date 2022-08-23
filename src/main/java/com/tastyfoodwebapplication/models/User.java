package com.tastyfoodwebapplication.models;

import com.tastyfoodwebapplication.enums.*;
import org.hibernate.annotations.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.*;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(nullable = false, unique = true)
    private String username;
    private String name;
    private String hashedPassword;
    private String address;
    private String phoneNumber;
    private UserRole role;
    private UserStatus status;
//    @OneToOne
//    @JoinColumn(name = "cart_id")
//    private Cart cart;

    public User() {}

    public User(String username, String name, String hashedPassword, String address, String phoneNumber, UserRole role) {
        this.username = username;
        this.name = name;
        this.hashedPassword = hashedPassword;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.status = UserStatus.Active;
//        this.cart = new Cart();
    }

    public String getId() { return id; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return hashedPassword;
    }

    public String getUsername() { return username; }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

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
