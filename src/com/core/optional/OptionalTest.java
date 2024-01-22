package com.core.optional;

import com.bean.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OptionalTest {
    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1, "a"));
        orders.add(new Order(2, "b"));
        orders.add(new Order(3, "c"));
        orders.add(new Order(4, "d"));
        orders.add(new Order(2, "e"));

        Optional<Order> optionalOrder = findById(orders, 2);
        if (optionalOrder.isPresent()) {
            System.out.println(optionalOrder.get());
        }

        Optional<Order> optionalOrder2 = findById2(orders, 22);
        System.out.println(optionalOrder2.isPresent());
        //since Java 9
        //optionalOrder2.ifPresentOrElse(System.out::println, () -> System.out.println("Not found"));

        Optional<Order> optionalOrder3 = findById2(orders, 2);
        optionalOrder3.ifPresent(System.out::println);

       // OptionalInt
    }

    public static Optional<Order> findById(List<Order> orders, int id) {
        List<Order> result = orders.stream()
                .filter(order -> order.getOrder() == id)
                .collect(Collectors.toList());
        if (!result.isEmpty() && result.size() > 1) {
            System.out.println("found more then one");
        }
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    public static Optional<Order> findById2(List<Order> orders, int id) {
        return orders.stream()
                .filter(order -> order.getOrder() == id)
                //.findFirst();
                .findAny();
    }

/*
    public static OptionalInt findById3(List<Order> orders, int id) {
        return orders.stream()
                .filter(order -> order.getOrder() == id)
                .map(Order::getOrder)
                .
                //.findFirst();
                .findAny();
    }
*/

}