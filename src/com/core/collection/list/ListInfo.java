package com.core.collection.list;

/** List - базовый интерфейс для списков. Расширяет Collection
 * <p> Дополнительные методы:
 * <p> void add(int index, E element), addAll(int index, Collection<? extends E> c)  — вставляет второй параметр в позицию, указанную в index;
 * <p> E get(int index) — возвращает элемент в виде объекта из позиции index, представляет собой одно из главных достоинств класса из-за скорости выполнения;
 * <p> int indexOf(Object ob), int lastIndexOf(Object o) — возвращает индекс первого|последнего вхождения указанного объекта;
 * <p> ListIterator listIterator( ) Возвращает итератор в начало вызывающего списка.
 * <p> ListIterator listIterator(int index) Возвращает итератор в вызывающий список, который начинается с указанного индекса.
 * <p> E remove(int index) — удаляет объект из позиции index;
 * <p> replaceAll(UnaryOperator<E> operator) - заменяет каждый элемент списка результатом operator над элементом
 * <p> E set(int index, E element) — заменяет объект в позиции index, возвращает при этом удаляемый элемент;
 * <p> default void sort(Comparator<? super E> c) — сортирует список на основе компаратора;
 * <p> List<E> subList(int fromIndex, int toIndex) — извлекает часть коллекции в указанных границах;
 * <p> static <E> List<E>copyOf(Collection <? extentds E> coll) — создает не модифицируемый список на основе передаваемой коллекции.
 * Java 10. Возвращает ImmutableCollection
 * <p> replaceAll(UnaryOperator<E> operator) - заменяет каждый элемент результатом, возвращаемым operator.
 * <p>
 * <p> ListIterator расширяет Iterator
 * <p> Методы:
 * <p> void add(Object obj) Вставляет obj в список перед элементом, который будет возвращен следующим вызовом next().
 * <p> int nextIndex() Возвращает индекс следующего элемента. Если нет следующего элемента, возвращается размер списка.
 * <p> boolean hasPrevious() Возвращает true, если есть предыдущий элемент. В противном случае возвращает false.
 * <p> Object previous() Возвращает предыдущий элемент. A NoSuchElementException вызывается, если не существует следующего элемента.
 * <p> int previousIndex() Возвращает индекс предыдущего элемента. Если нет предыдущего элемента, возвращается -1.
 * <p> void set(Object obj) Назначает obj текущему элементу. Это последний элемент, возвращаемый вызовом либо next(), либо previous().
 *
 */
public class ListInfo {
}
