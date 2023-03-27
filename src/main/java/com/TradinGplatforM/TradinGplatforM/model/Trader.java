package com.TradinGplatforM.TradinGplatforM.model;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @Column(name="initial_balance")
    private Float initialBalance;

    @Column(name="total_balance")
    private Float totalBalance;
    @CreationTimestamp()
    @Column(name="createdAt",nullable=false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp()
    @Column(name="updatedAt")

    private LocalDateTime updatedAt;


    @OneToMany(cascade =CascadeType.ALL,orphanRemoval=true)
    @JoinColumn(name="traderId", referencedColumnName = "id")
    List<History> history=new ArrayList<>();

    public Trader(String name, String email, Float initialBalance,Float totalBalance, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.name = name;
        this.email = email;
        this.initialBalance = initialBalance;
        this.totalBalance=totalBalance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Trader(String name, String email, Float initialBalance) {
        this.name = name;
        this.email = email;
        this.initialBalance = initialBalance;
    }
     public Trader()
     {

     }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", initialBalance=" + initialBalance +
                ", createdAt=" + createdAt +
                ", updatedAt=" +  updatedAt +
                ", totalBalance"+ totalBalance+
                '}';
    }

    //public void setId(long id) {
     //   this.id = id;
   // }

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

    public Float getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Float initialBalance) {
        this.initialBalance = initialBalance;
        this.totalBalance=initialBalance;
    }

    public Float getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(Float totalBalance) {
        this.totalBalance = totalBalance;
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
    public void setHistory(List<History> history) {
        this.history = history;
    }

    public List<History> getHistory(){
        return this.history;
    }




}
