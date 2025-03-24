package com.jhops10.desafio_picpay.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private BigDecimal balance;

    @JoinColumn(name = "user_id")
    @OneToOne
    private User user;

    public Wallet() {
    }

    public Wallet(String id, BigDecimal balance, User user) {
        this.id = id;
        this.balance = balance;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
