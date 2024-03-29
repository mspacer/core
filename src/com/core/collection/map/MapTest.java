package com.core.collection.map;

import java.util.*;

/**
 * <p> Карта отображений — это объект, который хранит пару «ключ–значение».
 * <p> Уникальность объектов-ключей должна обеспечиваться переопределением методов hashCode() и equals()
 * или реализацией интерфейсов Comparable, Comparator пользовательским классом.
 *
 * <p> Интерфейсы карт:
 * <p> Map<K, V> — отображает уникальные ключи и значения;
 * <p> Map.Entry<K, V> — описывает пару «ключ–значение»;
 * <p> SortedMap<K, V> — содержит отсортированные ключи и значения;
 * <p> NavigableMap<K, V> — расширяет SortedMap добавляет новые возможности навигации и поиска по ключу.
 *
 * <p> Классы карт отображений:
 * <p> AbstractMap<K, V> — реализует интерфейс Map<K, V>, является суперклассом для всех перечисленных карт отображений;
 * <p> HashMap<K, V> — использует хэш-таблицу для работы с ключами;
 * <p> TreeMap<K, V> — реализует NavigableMap. Использует дерево, где ключи расположены в виде дерева поиска в определенном порядке;
 * <p> WeakHashMap<K, V> — позволяет механизму сборки мусора удалять из карты значения по ключу, ссылка на который вышла из области видимости приложения;
 * <p> LinkedHashMap<K, V> — образует дважды связанный список ключей. Этот механизм эффективен, только если превышен коэффициент загруженности карты при работе с кэш-памятью и др.
 * <p> IdentityHashMap<K, V> хэш-коды объектов-ключей вычисляются методом System.identityHashCode() по адресу объекта в памяти,
 * в отличие от обычного значения hashCode(), вычисляемого сугубо по содержимому самого объекта.
 * <p> EnumMap<K extends Enum<K>, V> ключи являются типом Enum
 *
 * <p> Интерфейс Map<K, V> содержит следующие методы:
 * <p> V get(Object obj) — возвращает значение, связанное с ключом obj. Если элемент с указанным ключом отсутствует в карте, то возвращается значение null;
 * <p> V put(K key, V value) — помещает ключ key и значение value в вызывающую карту. При добавлении в карту элемента с существующим ключом,
 * произойдет замена текущего элемента новым. При этом метод возвратит заменяемый элемент;
 * <p> V putIfAbsent(K key, V value) — помещает ключ key и значение value в вызывающую карту. При добавлении в карту элемента с
 * существующим ключом, замена не произойдет;
 * <p> V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) — помещает ключ key и вычисляет значение value
 * при добавлении в вызывающую карту;
 * <p> V computeIfAbsent(K key, Function<? super K, ? super V> mappingFunction) — помещает ключ key и значение value в вызывающую
 * карту, если пары с таким ключом не существует, если ключ существует, то замена не производится;
 * <p> V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) — заменяет значение value в вызывающей
 * карте, если ключ с таким значением существует, если же пары с таким ключом не существует, то вставка пары не производится;
 * <p> void putAll(Map <? extends K, ? extends V> m) — помещает карту m в вызывающую карту;
 * <p> V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) - выполяет функцию для найденного значения key.
 * Есле не найдено, добавляет в карту. Возвращает встевляемое значение.
 * <p> boolean replace(K key, V oldValue, V newValue) - заменяет значение по ключу новым значением newValue, если сторое равно oldValue
 * <p> V remove(Object key) — удаляет пару «ключ–значение» по ключу key;
 * <p> void clear() — удаляет все пары из вызываемой карты;
 * <p> boolean containsKey(Object key) — возвращает true, если вызывающая карта содержит key как ключ;
 * <p> boolean containsValue(Object value) — возвращает true, если вызывающая карта содержит value как значение;
 * <p> Set<K> keySet() — возвращает множество ключей;
 * <p> Set<Map.Entry<K, V>> entrySet() — возвращает множество, содержащее значения карты в виде пар «ключ–значение»;
 * <p> Collection<V> values() — возвращает коллекцию, содержащую значения карты;
 * <p> static <K, V> Map<K, V> copyOf(Map <? extends K,? extends V> map)  копирует исходную карту в немодифицируемую новую карту;
 * <p> static <K, V> Map<K, V> of(parameters) — перегруженный метод для создания неизменяемых карт на основе переданных в метод параметров;
 * <p> void forEach(BiConsumer<? super K, ? super V> action) — выполняет действие над каждым элементом Map.
 * <p> В коллекциях, возвращаемых тремя последними методами, можно только удалять элементы, добавлять нельзя.
 * Данное ограничение обуславливается параметризацией возвращаемого методами значения.
 * <p>
 * <p>
 */
public class MapTest {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "One");
        map.put(999, null);
        map.putIfAbsent(999, "-1"); //заменит null, т.к. это отсутствующее значение

        Set<Integer> keys = map.keySet();
        System.out.println("keys: " + keys);

        Collection<String> values = map.values();
        System.out.println("values: " + values);

        Set<Map.Entry<Integer, String>> entries = map.entrySet();
        entries.stream()
                .forEach(value -> System.out.println(value.getKey() + "/" + value.getValue()));

        entries.stream()
                .filter(entry -> "One".equals(entry.getValue()))
                .peek(entry -> entry.setValue("One.One"))
                .forEach(System.out::println);

        map.computeIfAbsent(2, key -> "Two/" + key );
        System.out.println(map);

        String result = map.merge(1, " merge OK", (s, s2) -> s.concat(s2));
        System.out.println("result: " + result);
        System.out.println(map);

        boolean isReplace = map.replace(999, "-1", "-2");
        System.out.println("replaced: " + isReplace);
        System.out.println(map);

        enumMap();
    }

    static void enumMap() {
        System.out.println("----enumMap-----");
        EnumMap<Country, Integer> map = new EnumMap<Country, Integer>(Country.class);
        map.put(Country.POLAND, 8);
        map.put(Country.UKRAINE, 1);
        map.put(Country.BELARUS, 0);
        map.forEach((k, v) -> System.out.println(k + " " + v));
    }

    enum Country {
        ARMENIA, BELARUS, INDIA, KAZAKHSTAN, POLAND, UKRAINE
    }

}
