package com.jhops10.desafio_picpay.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal value;

    @JoinColumn(name = "payee_id")
    @ManyToOne
    private User payee;

    @JoinColumn(name = "payer_id")
    @ManyToOne
    private User payer;

    private LocalDateTime transactionDate;

    @PrePersist
    void prePersist() {
        transactionDate = LocalDateTime.now();
    }

    public Transaction() {
    }

    public Transaction(Long id, BigDecimal value, User payee, User payer, LocalDateTime transactionDate) {
        this.id = id;
        this.value = value;
        this.payee = payee;
        this.payer = payer;
        this.transactionDate = transactionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public User getPayee() {
        return payee;
    }

    public void setPayee(User payee) {
        this.payee = payee;
    }

    public User getPayer() {
        return payer;
    }

    public void setPayer(User payer) {
        this.payer = payer;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}
