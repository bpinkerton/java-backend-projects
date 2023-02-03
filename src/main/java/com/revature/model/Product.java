package com.revature.model;

public class Product {

    private int id;

    private Department department;

    private String name;
    private String description;
    private double price;
    private int quantity;

    public Product() {
    }

    public Product(int id, Department department, String name, String description, double price, int quantity) {
        this.id = id;
        this.department = department;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public Product setId(int id) {
        this.id = id;
        return this;
    }

    public Department getDepartment() {
        return department;
    }

    public Product setDepartment(Department department) {
        this.department = department;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Product setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public enum Department{
        CLOTHING,
        BOOKS,
        ELECTRONICS,
        HOME,
        PET_SUPPLIES,
        HEALTH,
        TOYS,
        SPORTS,
        OUTDOORS,
        AUTOMOTIVE
    }
}
