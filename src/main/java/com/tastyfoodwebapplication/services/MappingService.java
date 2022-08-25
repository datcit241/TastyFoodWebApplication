package com.tastyfoodwebapplication.services;

import com.tastyfoodwebapplication.enums.UserRole;
import com.tastyfoodwebapplication.models.User;
import com.tastyfoodwebapplication.models.bindings.UserBinding;
import com.tastyfoodwebapplication.utilities.PasswordAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MappingService {
    private PasswordAuthentication passwordAuthentication;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public MappingService(PasswordAuthentication passwordAuthentication, PasswordEncoder passwordEncoder) {
        this.passwordAuthentication = passwordAuthentication;
        this.passwordEncoder = passwordEncoder;
    }

    public MappingService() {}

    public User bindUser(UserBinding userBinding) {
        String hashedPassword = passwordEncoder.encode(userBinding.getPassword());
        User user = new User(userBinding.getUsername(), userBinding.getName(), hashedPassword, userBinding.getAddress(), userBinding.getEmail(), userBinding.getPhoneNumber(), UserRole.User);
        return user;
    }

    public PasswordAuthentication getPasswordAuthentication() { return passwordAuthentication; }
    public void setPasswordAuthentication(PasswordAuthentication passwordAuthentication) { this.passwordAuthentication = passwordAuthentication; }

}
