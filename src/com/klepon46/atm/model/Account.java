package com.klepon46.atm.model;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Account {

    private String name;
    private Integer balance;
    private Map<String, Integer> owedTo;
    private Map<String, Integer> owedFrom;

    public Account(String name) {
        this.name = name;
        balance = 0;
        owedTo = new TreeMap<>();
        owedFrom = new TreeMap<>();
    }

    public void deposit(Integer amount) {
        balance += amount;
    }

    public void withdraw(Integer amount) {
        balance -= amount;
    }

    public String getName() {
        return name;
    }

    public Integer getBalance() {
        return balance;
    }

    public Map<String, Integer> getOwedTo() {
        return owedTo;
    }

    public void setOwedTo(Map<String, Integer> owedTo) {
        this.owedTo = owedTo;
    }

    public Map<String, Integer> getOwedFrom() {
        return owedFrom;
    }

    public void setOwedFrom(Map<String, Integer> owedFrom) {
        this.owedFrom = owedFrom;
    }
}
