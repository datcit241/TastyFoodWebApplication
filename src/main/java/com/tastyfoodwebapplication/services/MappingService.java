package com.tastyfoodwebapplication.services;

import com.tastyfoodwebapplication.enums.UserRole;
import com.tastyfoodwebapplication.models.User;
import com.tastyfoodwebapplication.models.bindings.UserBinding;
import com.tastyfoodwebapplication.utilities.PasswordAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

@Component
public class MappingService {
    private PasswordAuthentication passwordAuthentication;

    @Autowired
    public MappingService(PasswordAuthentication passwordAuthentication) { this.passwordAuthentication = passwordAuthentication; }

    public MappingService() {}

    public User bindUser(UserBinding userBinding) {
        String salt;
        try {
            salt = passwordAuthentication.getSalt();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
        String hashedPassword = passwordAuthentication.getSecurePassword(userBinding.getPassword(), salt);
        User user = new User(userBinding.getUsername(), userBinding.getName(), salt, hashedPassword, userBinding.getAddress(), userBinding.getPhoneNumber(), UserRole.NormalUser);

        return user;
    }

    public PasswordAuthentication getPasswordAuthentication() { return passwordAuthentication; }
    public void setPasswordAuthentication(PasswordAuthentication passwordAuthentication) { this.passwordAuthentication = passwordAuthentication; }

}
