package com.bankapp.application.data.entity;

//import jakarta.persistence.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Currency;

@Entity
@Table(name = "transaction")
public class Transaction {
    public Transaction(Account account, double amount, int status) {
        this.account = account;
        this.amount = amount;
        this.status = status;
    }

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @ManyToOne()
    @JoinColumn(name = "account_id")
    private Account account;

    @NotNull
    @Column(name = "transaction_type")
    private String transactionType; //Pix, Ted, Doc

    @NotNull
    @Column(name = "amount")
    private Double amount;

    @Column(name = "status")
    private Integer status; //1 - válida, 0 - inválida

    public Long getId() {
        return transactionId;
    }

    public void setId(Long id) {
        this.transactionId = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getType() {
        return transactionType;
    }

    public void setType(String type) {
        this.transactionType = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
