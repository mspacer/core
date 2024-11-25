package com.bean;

public class Order {
    public int order;
    public String about;
    public double amount;

    public Order(int order, double amount) {
        this.order = order;
        this.amount = amount;
    }

    public Order(int order, String about) {
        this.order = order;
        this.about = about;
    }

    public Order(int order, double amount, String about) {
        this.order = order;
        this.amount = amount;
        this.about = about;
    }

    public int getOrder() {
        return order;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order=" + order +
                ", about='" + about + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }

}
