package com.example.andriy.entities;

public class Product {
    private int id;
    private String name;
    private float price;
    private String date_added;

    public Product(int id, String name, float price, String date_added) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date_added = date_added;
    }

    public Product(String name, float price, String date_added) {
        this.name = name;
        this.price = price;
        this.date_added = date_added;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }
}
