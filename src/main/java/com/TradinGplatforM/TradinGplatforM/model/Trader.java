package com.TradinGplatforM.TradinGplatforM.model;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Entity
@DynamicUpdate
@Table(name="Trader")
public class Trader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="name")
    private String name;
    @Email
    @Column(name="email", unique = true)
    private String email;
    @Column(name="balance")
    private Float balance;
    @CreationTimestamp()
    @Column(name="createdAt",nullable=false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp()
    @Column(name="updatedAt")

    private LocalDateTime updatedAt;

    public Trader(long id, String name, String email, Float balance, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
     public Trader()
     {

     }
    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                ", createdAt=" + createdAt +
                ", updatedAt=" +  updatedAt +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }



}
