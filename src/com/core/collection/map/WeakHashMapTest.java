package com.core.collection.map;

import com.bean.Order;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

/**
 * <p> хорошо работает в ситуациях, когда в процессе работы с коллекцией некоторые объекты должны из нее гарантированно
 * удаляться, но моменты необходимости удаления и само удаление пары из коллекции могут отстоять друг от друга по времени.
 */
public class WeakHashMapTest {
    public static void main(String[] args) throws InterruptedException {
        List<Key> keys = new ArrayList<>();
        keys.add(new Key(100));
        keys.add(new Key(220));
        keys.add(new Key(770));

        CurrentOrders orders = new CurrentOrders();
        orders.put(keys.get(0), new Order(77, "10d"));
        orders.put(keys.get(1), new Order(65, "54d"));
        orders.put(keys.get(2), new Order(41, "93d"));
        keys.get(1).setProcessed(true);

        System.out.println("keys.size() before: " + keys.size());
        // удаление из списка и, как следствие, ключ из WeakHashMap утратит ссылку и станет целью для удаления «сборщиком мусора».
        keys.removeIf(Key::isProcessed);

        /*
        Iterator<Key> iterator = keys.iterator();
        while (iterator.hasNext()) {
            Key ordersKey = iterator.next();
            if (ordersKey.isProcessed()) {
                iterator.remove();
            }
        }
*/
        System.out.println("keys.size() after: " + keys.size());

        System.out.println("orders before: " + orders.size());
        System.gc();
        Thread.sleep(1_000);
        System.out.println("orders after: " + orders.size());
    }

    private static class CurrentOrders {
        private WeakHashMap<Key, Order> orders = new WeakHashMap<>();

        public Order put(Key key, Order value) {
            return orders.put(key, value);
        }

        public Order get(Object key) {
            return orders.get(key);
        }

        public int size() {
            return orders.size();
        }
    }

    private static class Key {
        private int keyUnique;
        private boolean isProcessed;

        public Key(int keyUnique) {
            this.keyUnique = keyUnique;
        }

        public boolean isProcessed() {
            return isProcessed;
        }

        public void setProcessed(boolean processed) {
            isProcessed = processed;
        }
    }
}
