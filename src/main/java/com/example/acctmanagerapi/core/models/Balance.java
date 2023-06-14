package com.example.acctmanagerapi.core.models;

import lombok.EqualsAndHashCode;

import java.util.Objects;

@EqualsAndHashCode
public class Balance {
    private String id;
    private int balance;

    public Balance(String id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Insufficient balance");
        }
    }
}
