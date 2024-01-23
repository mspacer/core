package com.bean;

public class Order {
    private int order;
    private String about;

    public Order(int order, String about) {
        this.order = order;
        this.about = about;
    }

    public int getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order=" + order +
                ", about='" + about + '\'' +
                '}';
    }

}
