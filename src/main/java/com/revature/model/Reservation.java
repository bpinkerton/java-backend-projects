package com.revature.model;

import java.sql.Timestamp;
import java.util.List;

public class Reservation {

    private int id;
    private Timestamp time;

    private User user;
    private List<Product> products;

    public Reservation() {
    }

    public Reservation(int id, Timestamp time, User user, List<Product> products) {
        this.id = id;
        this.time = time;
        this.user = user;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public Reservation setId(int id) {
        this.id = id;
        return this;
    }

    public Timestamp getTime() {
        return time;
    }

    public Reservation setTime(Timestamp time) {
        this.time = time;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Reservation setUser(User user) {
        this.user = user;
        return this;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Reservation setProducts(List<Product> products) {
        this.products = products;
        return this;
    }
}
