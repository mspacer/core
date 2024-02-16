package com.core.functional;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Random;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ComparatorTest {
    /**
- начиная с версии Java 8, приобрел свойства функционального интерфейса
- thenComparing сортирует объекты, равные между собой по первой сортировки. Таким образом результат перой сортировки не пропадает.
- Интерфейс Comparator не рекомендуется имплементировать самому классу-сущности, для сортировки экземпляров которого он предназначается
    Либо внутренние статические классы, либо перечисления
- Блок кода, представляющий собой лямбда-выражение вместе со значениями локальных переменных и параметров метода,
    в котором он объявлен, называется замыканием, или closure.
    Объект-функция создается во время исполнения, и применен может быть уже после того как объект, его создавший,
    прекратит существование. Такая ситуация требует, чтобы переменные, которые использует лямбда-выражение, не могли быть изменены.
    Это не касается объектов и массивов(их внутренне состояние можно изменить) либо статических параметров

     */
    public static void main(String[] args) {
        Comparator<String> comparator = (s1, s2) -> s2.length() - s1.length();
        String str = "and java course epam the rose lion wolf hero green white red white";
        Arrays.stream(str.split("\\s"))
                .sorted(comparator)
                .forEach(s -> System.out.printf("%s ", s));
        System.out.println("");
        Arrays.stream(str.split("\\s"))
                .sorted(comparator.thenComparing(String::compareTo))
                .forEach(s -> System.out.printf("%s ", s));
        System.out.println("");
        Arrays.stream(str.split("\\s"))
                .sorted(comparator.reversed())
                .forEach(s -> System.out.printf("%s ", s));

        System.out.println("__________________________________");

        Order order1 = new Order(100L, 454.6565);
        Order order2 = new Order(200L, 54.43);

        System.out.println(Objects.compare(order1, order2, new Order.IdComparator()));
        System.out.println(Objects.compare(order1, order2, new Order.AmountComparator()));

        System.out.println(Objects.compare(order1, order2, OrderComparatorClassic.ID));
        System.out.println(Objects.compare(order1, order2, OrderComparatorClassic.AMOUNT));

        System.out.println("_____Closure_____________________");

        Function<String, Integer> function = buildClosure("100");
        int res = function.apply("77");
        System.out.println(res);

        //ссылки на методы
        Consumer<String> consumer = s -> System.out.println(s);
        //что равнозначно записи:
        consumer = System.out::println;
        // Статический метод
        BiFunction<Double, Double, Double> biFunction = Math::hypot;
        //что эквивалентно:
        biFunction = (x, y) -> Math.hypot(x, y);
        //Ссылка на конструктор
        Supplier<StringBuilder> supplier1 = StringBuilder::new;

        System.out.println("_____Тесты_____________________");
        long result = Arrays.stream(new String[]{"JSE", "JDK", "J"}) // line 1
                .filter(s -> s.length() > 1)
                .filter(s -> s.contains("J"))
                .count();
        System.out.println(result);

        Predicate<String> predicate = s -> {
            int i = 0;
            boolean contains = s.contains("JDK");
            System.out.print(i++ + " ");
            return contains;
        };
        Arrays.stream(new String[]{"JRE", "JDK", "JVM", "JSE", "JDK"})
                .filter(predicate)
                .findFirst()
                //.collect(Collectors.joining());
                .ifPresent(System.out::println);

        IntStream numbers = new Random().ints(10, 0, 20);
        numbers = numbers.map(value -> { System.out.print(value + " "); return value; });
        System.out.println(
                //"max: " + numbers.max().getAsInt()
                "max: " + numbers.boxed().max(Comparator.comparingInt(value -> value)).get()
        );

        System.out.println(" ---- 2----- ");
        Stream.of(1).peek(
                        ((Consumer<Integer>) (i -> i += 1))
                                .andThen(i -> i += 2))
                .forEach(System.out::print);
        Stream.of(1).map(
                        ((Function<Integer, Integer>) (i -> i += 1))
                                .andThen(i -> i += 2))
                .forEach(System.out::print);
        System.out.println("");

        System.out.println(" ---- 3 ----- ");
        Stream<String> strings = Arrays.stream(
                new String[]{"Java", "Standard", "Edition", "version", "14"});
        System.out.println(
                strings.filter(s -> s.length() <= 4)
                        .count()
                 );
    }

    public static Function<String, Integer> buildClosure(final String strNum) {
        //При формировании объекта функции Function, как возвращаемого значения метода build(), значения параметров сохраняются,
        // и функция будет использовать эти зафиксированные значения strNum и count в тот момент, когда произойдет ее вызов
        // strNum и count могут быть помечены как final
        int count = 1;
        return t -> {
            int res = Integer.valueOf(strNum + t) + count;
            return res;
        };
    }

}

class Order {
    private final long orderId;
    private final double amount;

    public Order(long orderId, double amount) {
        this.orderId = orderId;
        this.amount = amount;
    }

    public long getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }

    // other code
    public static class IdComparator implements Comparator<Order> {
        @Override
        public int compare(Order o1, Order o2) {
            return Long.compare(o1.orderId, o2.orderId);
        }
    }
    public static class AmountComparator implements Comparator<Order> {
        @Override
        public int compare(Order o1, Order o2) {
            return Double.compare(o1.getAmount(), o2.getAmount());
        }
    }
}

enum OrderComparatorClassic implements Comparator<Order> {
    ID {
        @Override
        public int compare(Order o1, Order o2) {
            return Long.compare(o1.getOrderId(), o2.getOrderId());
        }
    },
    AMOUNT {
        @Override
        public int compare(Order o1, Order o2) {
            return Double.compare(o1.getAmount(), o2.getAmount());
        }
    }
}