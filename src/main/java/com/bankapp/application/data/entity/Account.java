package com.bankapp.application.data.entity;

//import jakarta.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
    public Account(String name, Double balance, User user) {
        this.name = name;
        this.balance = balance;
        this.user = user;
    }

    @Id
    @GeneratedValue(generator = "random4DigitIdGenerator")
    @GenericGenerator(name = "random4DigitIdGenerator", strategy = "com.bankapp.application.util.Random4DigitIdGenerator")
    @Column(name = "account_id", insertable = false, updatable = false)
    private Long accountId;

    @Column(name = "account_name")
    private String name;

    @Column(name = "balance")
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return accountId;
    }

    public void setId(Long id) {
        this.accountId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

