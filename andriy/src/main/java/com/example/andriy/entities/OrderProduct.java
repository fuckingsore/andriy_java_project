package com.example.andriy.entities;

public class OrderProduct {
    private int id;
    private int product_id;
    private int order_id;

    public OrderProduct(int id, int product_id, int order_id) {
        this.id = id;
        this.product_id = product_id;
        this.order_id = order_id;
    }

    public OrderProduct(int product_id, int order_id) {
        this.product_id = product_id;
        this.order_id = order_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
}
