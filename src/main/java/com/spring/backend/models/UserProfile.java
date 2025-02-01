package com.spring.backend.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.annotations.UuidGenerator;

@Entity
public class UserProfile {


    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    @NotNull(message = "Name can not be null")
    private String name;


    @PositiveOrZero(message = "Balance can not be less than zero")
    private double balance;


    @NotNull(message = "Description can not be null")
    private String description;


    @NotNull(message = "Type can not be null")
    private String type;


    @NotNull(message = "Email is a required field")
    @Column(unique = true)
    private String email;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
