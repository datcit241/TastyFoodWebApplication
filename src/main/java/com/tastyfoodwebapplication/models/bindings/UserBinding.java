package com.tastyfoodwebapplication.models.bindings;

import javax.validation.constraints.*;

public class UserBinding {
    @NotEmpty
    private String name;
    @Size(min = 6, max = 20, message = "Username should have 6-20 characters in length")
    private String username;
    @Size(min = 8, max = 20, message = "Password should have 8-20 characters in length")
    private String password;
    private String retypedPassword;
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private String address;

    @Pattern(regexp="(^$|[0-9]{10})", message = "Should be a 10 digit number")
    private String phoneNumber;

    public UserBinding() {}

    public UserBinding(String name, String username, String password, String retypedPassword, String email, String address, String phoneNumber) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.retypedPassword = retypedPassword;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRetypedPassword() { return retypedPassword; }
    public void setRetypedPassword(String rePassword) { this.retypedPassword = rePassword; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
