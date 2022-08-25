package com.tastyfoodwebapplication.models;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.time.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @NotEmpty
    private String name;
    @Email
    private String email;
    @Pattern(regexp="(^$|[0-9]{10})", message = "Should be a 10 digit number")
    private String phoneNumber;
    @Size(min = 30, message = "Should be at least 30 characters in length")
    private String content;
    private LocalDateTime sentAt;

    public Message() {}

    public Message(String name, String email, String phoneNumber, String content, LocalDateTime sentAt) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.content = content;
        this.sentAt = sentAt;
    }

    public String getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public LocalDateTime getSentAt() { return sentAt; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }
}
