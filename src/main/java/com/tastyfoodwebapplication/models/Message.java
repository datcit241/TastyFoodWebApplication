package com.tastyfoodwebapplication.models;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    @Column(insertable = false, updatable = false, nullable = false)
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String content;
    private LocalDateTime sentAt;

    public Message() {}

    public Message(String id, String name, String email, String phoneNumber, String content, LocalDateTime sentAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.content = content;
        this.sentAt = sentAt;
    }

    public String getId() { return id; }

    public String getName() { return name; }

    public String getEmail() { return email; }

    public String getPhoneNumber() { return phoneNumber; }

    public String getContent() { return content; }

    public LocalDateTime getSentAt() { return sentAt; }

}
